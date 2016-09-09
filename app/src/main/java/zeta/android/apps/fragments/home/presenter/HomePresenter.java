package zeta.android.apps.fragments.home.presenter;

import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;

import com.google.auto.value.AutoValue;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

import zeta.android.apps.R;
import zeta.android.apps.fragments.common.presenter.RxFragmentLifeCyclePresenter;
import zeta.android.apps.fragments.home.presentation.HomePresentation;
import zeta.android.apps.rx.ZetaSubscriber;
import zeta.android.apps.rx.interfaces.RxSchedulerProvider;
import zeta.apps.flickr.managers.params.flickr.FlickrImageManager;
import zeta.apps.flickr.models.flickr.FlickrImageModel;
import zeta.apps.flickr.models.flickr.FlickrImages;
import zeta.apps.flickr.models.flickr.errors.FlickrException;

@ParametersAreNonnullByDefault
public class HomePresenter extends RxFragmentLifeCyclePresenter<HomePresentation> {

    private HomePresentation mPresentation;

    private FlickrImageManager mFlickrManager;
    private HomePresenterParam mPresenterParam;

    private boolean mIsFlickrRequestLoading;

    //Saved data
    private int mSavedPreviousSize;
    private List<FlickrImages> mSavedFlickrImages = new ArrayList<>(0);

    public HomePresenter(RxSchedulerProvider schedulerProvider, FlickrImageManager flickrManager) {
        super(schedulerProvider);
        mFlickrManager = flickrManager;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //no op
    }

    public void onCreate(HomePresenterParam presenterParam) {
        mPresenterParam = presenterParam;
        final Parcelable savedState = mPresenterParam.getSavedState();
        if (savedState != null) {
            setSavedState(savedState);
        }
    }

    @Override
    public void onCreateView(HomePresentation homePresentation) {
        mPresentation = homePresentation;
    }

    @Override
    public void onViewCreated() {
        if (hasFlickrImageData()) {
            showFlickerImages(mSavedFlickrImages, mSavedPreviousSize);
        } else {
            requestFlickrResponse();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresentation = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenterParam = null;
        mFlickrManager = null;
    }

    public void onPageScrolled(int firstVisibleItemPosition, int visibleItemCount, int totalItemCount) {
        if (shouldLoadNextPage(firstVisibleItemPosition, visibleItemCount, totalItemCount)) {
            mPresentation.showListViewFooter(true);
            //Request next page here
        }
    }

    public void onFooterRetryClicked() {
        mPresentation.showListViewFooterRetry(false);
        //Request next page here current page + 1
    }

    //region saved data
    public Parcelable getSavedState() {
        return SavedState.create()
                .setPreviousPageSize(mSavedPreviousSize)
                .setFlickrImages(mSavedFlickrImages)
                .build();
    }

    public void setSavedState(Parcelable savedState) {
        SavedState state = (SavedState) savedState;
        mSavedPreviousSize = state.getPreviousPageSize();
        mSavedFlickrImages = state.getFlickrImages();
    }
    //endregion

    private void requestFlickrResponse() {
        mPresentation.showProgress(true);
        mIsFlickrRequestLoading = true;
        subscribe(mFlickrManager.getFlickrImages(), new ZetaSubscriber<FlickrImageModel, FlickrException>() {
            @Override
            protected void onSuccess(FlickrImageModel success) {
                synchronized (HomePresenter.this) {
                    //We have data show
                    mIsFlickrRequestLoading = false;
                    mSavedPreviousSize = mSavedFlickrImages.size();
                    mSavedFlickrImages.addAll(success.getFlickrImages());
                }
                showFlickerImages(mSavedFlickrImages, mSavedPreviousSize);
            }

            @Override
            protected void onFailure(@Nullable FlickrException failure) {
                mIsFlickrRequestLoading = false;
                mPresentation.showMessage(R.string.zeta_error_loading);
                mPresentation.showProgress(false);
            }

            @Override
            protected void onNoNetworkFailure() {
                mIsFlickrRequestLoading = false;
                mPresentation.showMessage(R.string.zeta_no_network);
                mPresentation.showProgress(false);
            }
        });
    }

    private boolean shouldLoadNextPage(final int firstVisibleItem,
                                       final int visibleItemCount,
                                       final int totalItemCount) {
        boolean hasMoreRecords = (totalItemCount < getTotalRecordCount());
        if (visibleItemCount > 0 && hasMoreRecords) {
            boolean metTriggerPoint = (firstVisibleItem + visibleItemCount) >= (totalItemCount - (getPageSize() / 2));
            return (metTriggerPoint && !mIsFlickrRequestLoading);
        }
        return false;
    }

    private boolean hasFlickrImageData() {
        return mSavedFlickrImages != null && !mSavedFlickrImages.isEmpty();
    }

    private int getTotalRecordCount() {
        return mSavedFlickrImages != null ? mSavedFlickrImages.size() : 0;
    }

    private int getPageSize() {
        //return your page size here. This is flickr default so hard coded
        return 20;
    }

    private void showFlickerImages(List<FlickrImages> flickrImages, int previousSize) {
        mPresentation.updateImageAdapters(flickrImages, previousSize);
        mPresentation.showProgress(false);
        mPresentation.showListView(true);
        mPresentation.showListViewFooter(false);
    }

    //region saved instance
    @AutoValue
    public static abstract class SavedState implements Parcelable {

        public static Builder create() {
            return new AutoValue_HomePresenter_SavedState.Builder()
                    .setPreviousPageSize(0);
        }

        public abstract int getPreviousPageSize();

        @Nullable
        public abstract List<FlickrImages> getFlickrImages();

        @AutoValue.Builder
        public static abstract class Builder {

            public abstract Builder setPreviousPageSize(int previousPageSize);

            public abstract Builder setFlickrImages(@Nullable List<FlickrImages> flickrImages);

            public abstract SavedState build();
        }
    }
    //end region

}

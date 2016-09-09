package zeta.android.apps.fragments.home.presentation;

import android.support.annotation.StringRes;

import java.util.List;

import zeta.apps.flickr.models.flickr.FlickrImages;

public interface HomePresentation {

    void showProgress(boolean show);

    void showMessage(@StringRes int message);

    void showListView(boolean show);

    void showListViewFooter(boolean show);

    void showListViewFooterRetry(boolean show);

    void updateImageAdapters(List<FlickrImages> flickrImages, int previousSize);

}

package zeta.android.apps.fragments.home.modules;

import dagger.Module;
import dagger.Provides;
import zeta.android.apps.dagger.FragmentScope;
import zeta.android.apps.fragments.home.presenter.HomePresenter;
import zeta.android.apps.rx.interfaces.RxSchedulerProvider;
import zeta.apps.flickr.managers.params.flickr.FlickrImageManager;

@Module
@FragmentScope
public class HomePresenterModule {

    @Provides
    HomePresenter providesHomePresenter(RxSchedulerProvider schedulerProvider, FlickrImageManager flickrManager) {
        return new HomePresenter(schedulerProvider, flickrManager);
    }

}

package zeta.android.apps.fragments.subcomponents;

import dagger.Subcomponent;
import zeta.android.apps.dagger.FragmentScope;
import zeta.android.apps.fragments.home.HomeFragment;
import zeta.android.apps.fragments.home.modules.HomePresenterModule;
import zeta.apps.flickr.modules.FlickrImageModule;

@FragmentScope
@Subcomponent(modules = {HomePresenterModule.class, FlickrImageModule.class})
public interface ZetaComponent {
    void inject(HomeFragment fragment);
}

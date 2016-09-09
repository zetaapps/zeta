package zeta.android.apps.developer.debug.modules;

import com.github.pedrovgs.lynx.LynxConfig;

import dagger.Module;
import dagger.Provides;
import zeta.android.apps.dagger.FragmentScope;
import zeta.android.apps.developer.debug.presenter.DebugPresenter;
import zeta.android.apps.rx.interfaces.RxSchedulerProvider;
import zeta.android.apps.sharedPref.DebugSharedPreferences;

@Module
@FragmentScope
public class DebugPresenterModule {

    @Provides
    DebugPresenter providesHomePresenter(RxSchedulerProvider schedulerProvider,
                                         DebugSharedPreferences sharedPreferences,
                                         LynxConfig lynxConfig) {
        return new DebugPresenter(schedulerProvider, sharedPreferences, lynxConfig);
    }

}

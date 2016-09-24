package zeta.android.apps.di.module;

import com.github.pedrovgs.lynx.LynxConfig;

import dagger.Module;
import dagger.Provides;
import zeta.android.apps.presenter.DebugPresenter;
import zeta.android.apps.di.scope.FragmentScope;
import zeta.android.apps.rx.providers.RxSchedulerProvider;
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

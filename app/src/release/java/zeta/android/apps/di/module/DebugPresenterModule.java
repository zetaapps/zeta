package zeta.android.apps.di.module;

import dagger.Module;
import dagger.Provides;
import zeta.android.apps.presenter.DebugPresenter;
import zeta.android.apps.di.scope.FragmentScope;
import zeta.android.apps.rx.providers.RxSchedulerProvider;

@Module
@FragmentScope
public class DebugPresenterModule {

    @Provides
    DebugPresenter providesHomePresenter(RxSchedulerProvider schedulerProvider) {
        return new DebugPresenter(schedulerProvider);
    }

}

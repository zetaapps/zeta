package zeta.android.apps.developer.debug.modules;

import dagger.Module;
import dagger.Provides;
import zeta.android.apps.dagger.FragmentScope;
import zeta.android.apps.developer.debug.presenter.DebugPresenter;
import zeta.android.apps.rx.interfaces.RxSchedulerProvider;

@Module
@FragmentScope
public class DebugPresenterModule {

    @Provides
    DebugPresenter providesHomePresenter(RxSchedulerProvider schedulerProvider) {
        return new DebugPresenter(schedulerProvider);
    }

}

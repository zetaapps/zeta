package zeta.andriod.apps.presenter;

import android.view.Menu;
import android.view.MenuInflater;

import javax.annotation.ParametersAreNonnullByDefault;

import zeta.andriod.apps.ui.presentation.DebugPresentation;
import zeta.android.apps.presenter.ZetaRxFragmentLifeCyclePresenter;
import zeta.android.apps.rx.providers.RxSchedulerProvider;

@ParametersAreNonnullByDefault
public class DebugPresenter extends ZetaRxFragmentLifeCyclePresenter<DebugPresentation> {

    public DebugPresenter(RxSchedulerProvider schedulerProvider) {
        super(schedulerProvider);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

    }

    @Override
    public void onCreateView(DebugPresentation homePresentation) {

    }

    @Override
    public void onViewCreated() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

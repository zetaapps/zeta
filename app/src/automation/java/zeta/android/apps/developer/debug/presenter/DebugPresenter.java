package zeta.android.apps.developer.debug.presenter;

import android.view.Menu;
import android.view.MenuInflater;

import javax.annotation.ParametersAreNonnullByDefault;

import zeta.android.apps.developer.debug.presentation.DebugPresentation;
import zeta.android.apps.fragments.common.presenter.RxFragmentLifeCyclePresenter;
import zeta.android.apps.rx.interfaces.RxSchedulerProvider;

@ParametersAreNonnullByDefault
public class DebugPresenter extends RxFragmentLifeCyclePresenter<DebugPresentation> {

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

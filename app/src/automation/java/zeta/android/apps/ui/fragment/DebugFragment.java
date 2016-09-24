package zeta.android.apps.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.inject.Inject;

import zeta.android.apps.R;
import zeta.android.apps.di.component.ZetaAppComponent;
import zeta.android.apps.presenter.DebugPresenter;
import zeta.android.apps.ui.common.BaseViews;

@ParametersAreNonnullByDefault
public class DebugFragment extends BaseNavigationFragment implements DebugPresentation {

    @Inject
    public DebugPresenter mPresenter;
    private Views mViews;

    public static DebugFragment newInstance() {
        return new DebugFragment();
    }

    @Override
    public void configureDependencies(ZetaAppComponent component) {
        component.debugComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_debug, container, false);
        mViews = new Views(rootView);
        mPresenter.onCreateView(this);
        registerClickListener();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.onViewCreated();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unRegisterClickListener();
        mViews.clear();
        mViews = null;
        mPresenter.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
        mPresenter = null;
    }

    private void registerClickListener() {
        //register all views click listener here
    }

    private void unRegisterClickListener() {
        //un register all views click listener here
    }

    static class Views extends BaseViews {

        Views(View root) {
            super(root);
        }
    }

}

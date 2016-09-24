package zeta.android.apps.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import zeta.android.apps.ui.activity.navigation.INavigationFragmentManager;
import zeta.android.apps.ui.activity.navigation.NavigationFragmentManager;

public abstract class BaseNavigationFragment extends DaggerAwareFragment implements INavigationFragmentManager {

    private INavigationFragmentManager mNavigationFragmentManager;
    private boolean mOnSaveInstanceStateCalled;

    @Override
    public void onCreate(@Nullable Bundle savedInstance) {
        super.onCreate(savedInstance);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mNavigationFragmentManager = (INavigationFragmentManager) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement NavigationFragmentManagerHolder");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mOnSaveInstanceStateCalled = false;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mOnSaveInstanceStateCalled = true;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    //region INavigationFragmentManager

    @Override
    public NavigationFragmentManager getNavigationFragmentManager() {
        if (mNavigationFragmentManager == null) {
            return null;
        }
        return mNavigationFragmentManager.getNavigationFragmentManager();
    }

    //endregion

    public final boolean onSavedInstanceStateCalled() {
        return mOnSaveInstanceStateCalled;
    }

}

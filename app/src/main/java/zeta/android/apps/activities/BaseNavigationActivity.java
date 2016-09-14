package zeta.android.apps.activities;

import android.support.design.widget.NavigationView;

import javax.inject.Inject;

import zeta.android.apps.activities.managers.INavigationFragmentManager;
import zeta.android.apps.activities.managers.NavigationFragmentManager;
import zeta.android.apps.views.common.BaseViews;

public abstract class BaseNavigationActivity<VH extends BaseViews> extends DaggerAwareActivity<VH> implements INavigationFragmentManager,
        NavigationView.OnNavigationItemSelectedListener {

    protected NavigationFragmentManager mNavigationFragmentManager;

    @SuppressWarnings("unused")
    @Inject
    protected void setNavigationFragmentManager(NavigationFragmentManager navigationFragmentManager) {
        mNavigationFragmentManager = navigationFragmentManager;
    }

}

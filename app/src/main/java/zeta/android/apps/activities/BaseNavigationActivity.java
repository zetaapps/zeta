package zeta.android.apps.activities;

import android.support.design.widget.NavigationView;

import javax.inject.Inject;

import zeta.android.apps.activities.managers.INavigationFragmentManager;
import zeta.android.apps.activities.managers.NavigationFragmentManager;

public abstract class BaseNavigationActivity extends DaggerAwareActivity implements INavigationFragmentManager,
        NavigationView.OnNavigationItemSelectedListener {

    protected NavigationFragmentManager mNavigationFragmentManager;

    @SuppressWarnings("unused")
    @Inject
    protected void setNavigationFragmentManager(NavigationFragmentManager navigationFragmentManager) {
        mNavigationFragmentManager = navigationFragmentManager;
    }

}

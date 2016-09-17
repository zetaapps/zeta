package zeta.android.apps.view.activity;

import android.support.design.widget.NavigationView;

import javax.inject.Inject;

import zeta.android.apps.view.activity.navigation.INavigationFragmentManager;
import zeta.android.apps.view.activity.navigation.NavigationFragmentManager;

public abstract class BaseNavigationActivity extends DaggerAwareActivity implements INavigationFragmentManager,
        NavigationView.OnNavigationItemSelectedListener {

    protected NavigationFragmentManager mNavigationFragmentManager;

    @SuppressWarnings("unused")
    @Inject
    protected void setNavigationFragmentManager(NavigationFragmentManager navigationFragmentManager) {
        mNavigationFragmentManager = navigationFragmentManager;
    }

}

package zeta.android.apps.ui.activity;

import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import zeta.android.apps.ui.activity.navigation.INavigationFragmentManager;
import zeta.android.apps.ui.activity.navigation.NavigationFragmentManager;

public abstract class BaseNavigationActivity extends AppCompatActivity implements INavigationFragmentManager,
        NavigationView.OnNavigationItemSelectedListener {

    protected NavigationFragmentManager mNavigationFragmentManager;

    @SuppressWarnings("unused")
    @Inject
    protected void setNavigationFragmentManager(NavigationFragmentManager navigationFragmentManager) {
        mNavigationFragmentManager = navigationFragmentManager;
    }

}

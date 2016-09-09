package zeta.android.apps.activities;

import android.app.ActivityManager;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import zeta.android.apps.BuildConfig;
import zeta.android.apps.R;
import zeta.android.apps.ZetaApplication;
import zeta.android.apps.activities.managers.INavigationFragmentManager;
import zeta.android.apps.activities.managers.NavigationFragmentManager;
import zeta.android.apps.developer.debug.DebugFragment;
import zeta.android.apps.views.common.BaseViews;

public class NavigationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        INavigationFragmentManager {

    private Views mViews;
    private NavigationFragmentManager mNavigationFragmentManager;

    static class Views extends BaseViews {

        @Bind(R.id.zeta_drawer_layout)
        DrawerLayout drawerLayout;

        @Bind(R.id.zeta_app_bar_layout)
        AppBarLayout appBarLayout;

        @Bind(R.id.zeta_toolbar)
        Toolbar toolbar;

        @Bind(R.id.zeta_nav_view)
        NavigationView navigationView;

        @Bind(R.id.container)
        View fragmentContainer;

        ImageView headerImageView;

        TextView headerTitle;

        TextView headerEmail;

        @SuppressWarnings("ConstantConditions")
        Views(AppCompatActivity root) {
            super(root.findViewById(R.id.zeta_drawer_layout));
            final View headerView = navigationView.getHeaderView(0);
            headerImageView = (ImageView) headerView.findViewById(R.id.header_image_view);
            headerTitle = (TextView) headerView.findViewById(R.id.header_title);
            headerEmail = (TextView) headerView.findViewById(R.id.header_email);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        configureTaskDescription();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        mViews = new Views(this);
        setSupportActionBar(mViews.toolbar);

        final FragmentManager supportFragmentManager = getSupportFragmentManager();
        mNavigationFragmentManager = new NavigationFragmentManager(supportFragmentManager,
                R.id.container,
                mViews.drawerLayout,
                mViews.navigationView);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mViews.drawerLayout, mViews.toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mViews.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        final Menu menu = mViews.navigationView.getMenu();
        menu.findItem(R.id.nav_debug).setVisible(BuildConfig.DEBUG);
        mViews.navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            // mNavigationFragmentManager.addAsBaseFragment(HomeFragment.newInstance());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ZetaApplication.watchForMemoryLeaks(getApplicationContext(), this);
        mViews.clear();
        mViews = null;
    }

    @Override
    public void onBackPressed() {
        if (mViews != null && mViews.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            mViews.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Handle click events from option menus
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                mNavigationFragmentManager.clearToBaseFragment();
                break;
            case R.id.nav_flickr:
                break;
            case R.id.nav_score:
                break;
            case R.id.nav_favorites:
                break;
            case R.id.nav_settings:
                break;
            case R.id.nav_debug:
                mNavigationFragmentManager.addFragmentToBackStack(DebugFragment.newInstance());
                break;
        }
        mViews.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public NavigationFragmentManager getNavigationFragmentManager() {
        return mNavigationFragmentManager;
    }

    //region INavigationFragmentManager

    /**
     * Since the primary color is indigo_700 and
     * we want the overview TopBar color to be different and we override it to be white
     * Note: This can be removed if the app icon contrasts with primaryColor or we change
     * primaryColor to be different
     */
    private void configureTaskDescription() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            int topBarColor = ContextCompat.getColor(this, R.color.zeta_white);
            ActivityManager.TaskDescription taskDescription =
                    new ActivityManager.TaskDescription(null, null, topBarColor);
            setTaskDescription(taskDescription);
        }
    }
    //endregion

}

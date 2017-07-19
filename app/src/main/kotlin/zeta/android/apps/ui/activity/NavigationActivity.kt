package zeta.android.apps.ui.activity

import android.app.ActivityManager
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.NavigationView
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import dagger.android.AndroidInjection
import zeta.android.apps.R
import zeta.android.apps.ZetaApplication
import zeta.android.apps.presenter.NavigationPresenter
import zeta.android.apps.ui.activity.navigation.NavigationFragmentManager
import zeta.android.apps.ui.common.BaseViews
import zeta.android.apps.ui.fragment.DebugFragment
import zeta.android.apps.ui.presentation.NavigationPresentation
import javax.inject.Inject


class NavigationActivity : BaseNavigationActivity(), NavigationPresentation {

    private var mViews: Views? = null

    @Inject lateinit var mPresenter: NavigationPresenter

    internal class Views(root: AppCompatActivity) : BaseViews(root.findViewById(R.id.zeta_drawer_layout)) {

        @BindView(R.id.zeta_drawer_layout)
        lateinit var drawerLayout: DrawerLayout

        @BindView(R.id.zeta_app_bar_layout)
        lateinit var appBarLayout: AppBarLayout

        @BindView(R.id.zeta_toolbar)
        lateinit var toolbar: Toolbar

        @BindView(R.id.zeta_nav_view)
        lateinit var navigationView: NavigationView

        @BindView(R.id.container)
        lateinit var fragmentContainer: View

        val headerImageView: ImageView

        val headerTitle: TextView

        val headerEmail: TextView

        init {
            val headerView = navigationView.getHeaderView(0)
            headerImageView = headerView.findViewById(R.id.header_image_view) as ImageView
            headerTitle = headerView.findViewById(R.id.header_title) as TextView
            headerEmail = headerView.findViewById(R.id.header_email) as TextView
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        setTheme(R.style.AppTheme)
        configureTaskDescription()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        mViews = Views(this)
        setSupportActionBar(mViews!!.toolbar)

        val supportFragmentManager = supportFragmentManager
        mNavigationFragmentManager.setFragmentManager(supportFragmentManager)
        mNavigationFragmentManager.setContainerId(R.id.container)
        mNavigationFragmentManager.setDrawerLayout(mViews!!.drawerLayout)
        mNavigationFragmentManager.setDrawer(mViews!!.navigationView)

        val toggle = ActionBarDrawerToggle(
                this, mViews!!.drawerLayout, mViews!!.toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)
        mViews!!.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        mPresenter.onCreate(this)

        mViews!!.navigationView.setNavigationItemSelectedListener(this)

        if (savedInstanceState == null) {
            // mNavigationFragmentManager.addAsBaseFragment(HomeFragment.newInstance());
        }
    }

    override fun onPostResume() {
        super.onPostResume()
        mPresenter.onPostResume()
    }

    override fun onPause() {
        super.onPause()
        mPresenter.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        ZetaApplication.watchForMemoryLeaks(applicationContext, this)
        mPresenter.onDestroy()
        mViews!!.clear()
        mViews = null
    }

    override fun onBackPressed() {
        val drawerLayout = mViews?.drawerLayout

        drawerLayout?.run {
            if (isDrawerOpen(GravityCompat.START)) {
                closeDrawer(GravityCompat.START)
            } else {
                super.onBackPressed()
            }
        }

        if (drawerLayout == null) {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //Handle click events from option menus
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> mPresenter.onMenuItemHomeSelected()
            R.id.nav_score -> {
            }
            R.id.nav_favorites -> {
            }
            R.id.nav_settings -> {
            }
            R.id.nav_debug -> mPresenter.onMenuItemDebugSelected()
        }
        mViews!!.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun getNavigationFragmentManager(): NavigationFragmentManager {
        return mNavigationFragmentManager
    }

    //region INavigationFragmentManager

    /**
     * Since the primary color is indigo_700 and
     * we want the overview TopBar color to be different and we override it to be white
     * Note: This can be removed if the app icon contrasts with primaryColor or we change
     * primaryColor to be different
     */
    private fun configureTaskDescription() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            val topBarColor = ContextCompat.getColor(this, R.color.zeta_white)
            val taskDescription = ActivityManager.TaskDescription(null, null, topBarColor)
            setTaskDescription(taskDescription)
        }
    }
    //endregion

    //region NavigationPresentation

    override fun showDebugMenuItem(show: Boolean) {
        val menu = mViews!!.navigationView.menu
        menu.findItem(R.id.nav_debug).isVisible = show
    }

    override fun showBaseScreen() {
        mNavigationFragmentManager.clearToBaseFragment()
    }

    override fun showDebugScreen() {
        mNavigationFragmentManager.addFragmentToBackStack(DebugFragment.newInstance())
    }

    //endregion

}

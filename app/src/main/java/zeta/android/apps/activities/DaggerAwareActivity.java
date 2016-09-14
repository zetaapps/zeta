package zeta.android.apps.activities;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import zeta.android.apps.ZetaAppComponent;
import zeta.android.apps.ZetaApplication;
import zeta.android.apps.views.common.BaseViews;

public abstract class DaggerAwareActivity<VH extends BaseViews> extends AppCompatActivity {

    protected VH mViews;

    protected abstract void configureDependencies(ZetaAppComponent component);

    protected abstract VH onCreateViewHolder(View view);

    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ZetaApplication application = (ZetaApplication) getApplication();
        configureDependencies(application.getZetaAppComponent());
    }

    @CallSuper
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        mViews = onCreateViewHolder(getWindow().getDecorView().getRootView());
    }
}

package zeta.android.apps.views.common;

import android.support.annotation.CallSuper;
import android.view.View;

import javax.annotation.ParametersAreNonnullByDefault;

import butterknife.ButterKnife;

@ParametersAreNonnullByDefault
public abstract class BaseViews {

    private View mRoot;

    protected BaseViews(View root) {
        mRoot = root;
        ButterKnife.bind(this, root);
    }

    public View getRootView() {
        return mRoot;
    }

    @CallSuper
    public void clear() {
        mRoot = null;
    }

}

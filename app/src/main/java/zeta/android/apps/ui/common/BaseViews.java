package zeta.android.apps.ui.common;

import android.support.annotation.CallSuper;
import android.support.v4.app.Fragment;
import android.view.View;

import javax.annotation.ParametersAreNonnullByDefault;

import butterknife.ButterKnife;

@ParametersAreNonnullByDefault
/**
 * {@link BaseViews} is a container for {@link View} objects, typically used to keep the {@link View}s
 * inside a named member variable for fast and easy dot-reference access.  This pattern helps hold views
 * distinct from other member variables which don't get nulled out by the operating system in quite the same
 * way.  For example, a {@link android.support.v4.app.Fragment} may have its views all nulled in
 * {@link Fragment#onDestroyView()}, without losing any other member variables until {@link Fragment#onDestroy()}.
 *
 * <br><br>
 *
 * As an additional convenience, {@link BaseViews} will also bind via butterknife upon instantiation.
 */
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

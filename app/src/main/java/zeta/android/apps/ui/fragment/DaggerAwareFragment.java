package zeta.android.apps.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import javax.annotation.ParametersAreNonnullByDefault;

import zeta.android.apps.ZetaApplication;
import zeta.android.apps.di.component.ZetaAppComponent;

@ParametersAreNonnullByDefault
public abstract class DaggerAwareFragment extends Fragment {

    public abstract void configureDependencies(ZetaAppComponent component);

    @Override
    public void onCreate(@Nullable Bundle savedInstance) {
        super.onCreate(savedInstance);
        ZetaApplication app = (ZetaApplication) getActivity().getApplication();
        configureDependencies(app.getZetaAppComponent());
    }

}

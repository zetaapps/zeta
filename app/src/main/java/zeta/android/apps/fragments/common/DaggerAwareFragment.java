package zeta.android.apps.fragments.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import javax.annotation.ParametersAreNonnullByDefault;

import zeta.android.apps.ZetaAppComponent;
import zeta.android.apps.ZetaApplication;

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

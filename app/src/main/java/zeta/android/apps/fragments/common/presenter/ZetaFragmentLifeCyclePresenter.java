package zeta.android.apps.fragments.common.presenter;

import android.view.Menu;
import android.view.MenuInflater;

public interface ZetaFragmentLifeCyclePresenter<Presentation> {

    void onCreateOptionsMenu(Menu menu, MenuInflater inflater);

    void onCreateView(Presentation presentation);

    void onViewCreated();

    void onDestroyView();

    void onDestroy();

}

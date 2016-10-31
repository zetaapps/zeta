package zeta.android.apps.presenter;

public interface ZetaActivityLifeCyclePresenter <Presentation> {

    void onCreate(Presentation presentation);

    void onPostResume();

    void onPause();

    void onDestroy();
}

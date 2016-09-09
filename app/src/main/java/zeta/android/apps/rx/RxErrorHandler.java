package zeta.android.apps.rx;

import rx.functions.Action1;
import timber.log.Timber;
import zeta.android.apps.BuildConfig;
import zeta.android.apps.network.ZetaNoNetworkConnectivityException;

public class RxErrorHandler implements Action1<Throwable> {

    @Override
    public void call(Throwable throwable) {
        if (!BuildConfig.DEBUG) {
            return;
        }

        if (throwable instanceof ZetaNoNetworkConnectivityException) {
            //We don't want to print stack trace here as we know this exception is caused due to
            //no network connectivity
            Timber.e("RxErrorHandler No network error");
            return;
        }

        Timber.e(throwable, "RxErrorHandler Uncaught error thrown");
    }
}

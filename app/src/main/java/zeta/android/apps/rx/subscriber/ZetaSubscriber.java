package zeta.android.apps.rx.subscriber;

import android.support.annotation.Nullable;

import com.github.zetaapps.either.Either;

import rx.Subscriber;
import timber.log.Timber;
import zeta.android.apps.network.exception.ZetaNoNetworkConnectivityException;

/**
 * A special {@link Subscriber} made for network calls.  In addition to explicit types for success and failure
 * conditions, the {@link #onNoNetworkFailure()} method makes a clear distinction for failures that result
 * from connectivity problems.
 */
public abstract class ZetaSubscriber<S, F> extends Subscriber<Either<S, F>> {

    protected abstract void onSuccess(S success);

    protected abstract void onFailure(@Nullable F failure);

    protected abstract void onNoNetworkFailure();

    @Override
    final public void onCompleted() {
        // do nothing
    }

    @Override
    final public void onError(Throwable e) {
        Timber.e(e, "onError reached from subscriber %s", this);
        if (e instanceof ZetaNoNetworkConnectivityException) {
            onNoNetworkFailure();
            return;
        }
        onFailure(null);
    }

    @Override
    final public void onNext(Either<S, F> either) {
        if (either.failureValue != null) {
            Timber.w("onNext FAILURE RESULT from subscriber %s, result=%s", this, either);
            onFailure(either.failureValue);
        } else {
            onSuccess(either.successValue);
        }
    }

}

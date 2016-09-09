package zeta.android.apps.rx;

import android.support.annotation.Nullable;

import rx.Subscriber;
import timber.log.Timber;
import zeta.android.apps.network.ZetaNoNetworkConnectivityException;
import zeta.apps.flickr.models.common.OneOf;

public abstract class ZetaSubscriber<S, F> extends Subscriber<OneOf<S, F>> {

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
    final public void onNext(OneOf<S, F> seOneOf) {
        if (seOneOf.failureValue != null) {
            Timber.w("onNext FAILURE RESULT from subscriber %s, result=%s", this, seOneOf);
            onFailure(seOneOf.failureValue);
        } else {
            onSuccess(seOneOf.successValue);
        }
    }

}

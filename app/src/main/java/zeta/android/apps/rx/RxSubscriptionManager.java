package zeta.android.apps.rx;

import javax.annotation.ParametersAreNonnullByDefault;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Actions;
import rx.subscriptions.CompositeSubscription;
import zeta.android.apps.rx.interfaces.RxSchedulerProvider;

@ParametersAreNonnullByDefault
public class RxSubscriptionManager {

    protected final RxSchedulerProvider mSchedulerProvider;
    private final CompositeSubscription mCompositeSubscription;

    public RxSubscriptionManager(RxSchedulerProvider schedulerProvider) {
        mCompositeSubscription = new CompositeSubscription();
        mSchedulerProvider = schedulerProvider;
    }

    public void cancelSubscriptions() {
        mCompositeSubscription.clear();
    }

    public void addSubscription(Subscription subscription) {
        mCompositeSubscription.add(subscription);
    }

    public <T> Observable.Transformer<T, T> applySchedulers() {
        return observable -> observable.subscribeOn(mSchedulerProvider.schedulerBackgroundThread())
                .observeOn(mSchedulerProvider.schedulerMainThread());
    }

    public <T> Subscription subscribe(Observable<T> observable, Observer<T> callbacks) {
        Subscription subscription = observable.compose(applySchedulers())
                .subscribe(callbacks);
        addSubscription(subscription);
        return subscription;
    }

    public <T> Subscription subscribe(Observable<T> observable, Action1<? super T> onNext,
                                      Action1<Throwable> onError, Action0 onCompleted) {
        Subscription subscription = observable.compose(applySchedulers())
                .subscribe(onNext, onError, onCompleted);
        addSubscription(subscription);
        return subscription;
    }

    public <T> Subscription subscribe(Observable<T> observable, Action1<? super T> onNext,
                                      Action1<Throwable> onError) {
        return subscribe(observable, onNext, onError, Actions.empty());
    }

    public <T> Subscription subscribe(Observable<T> observable, Action1<? super T> onNext) {
        return subscribe(observable, onNext, Actions.empty(), Actions.empty());
    }

}

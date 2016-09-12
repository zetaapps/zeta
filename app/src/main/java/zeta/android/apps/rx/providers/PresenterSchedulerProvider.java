package zeta.android.apps.rx.providers;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import zeta.android.apps.rx.interfaces.RxSchedulerProvider;

public class PresenterSchedulerProvider implements RxSchedulerProvider {
    @Override
    public Scheduler schedulerMainThread() {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler schedulerBackgroundThread() {
        return Schedulers.io();
    }
}

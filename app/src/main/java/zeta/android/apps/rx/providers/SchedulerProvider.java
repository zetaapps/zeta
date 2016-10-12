package zeta.android.apps.rx.providers;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SchedulerProvider implements RxSchedulerProvider {
    @Override
    public Scheduler schedulerMainThread() {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler schedulerBackgroundThread() {
        return Schedulers.io();
    }
}

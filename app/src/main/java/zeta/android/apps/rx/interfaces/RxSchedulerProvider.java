package zeta.android.apps.rx.interfaces;

import rx.Scheduler;

public interface RxSchedulerProvider {

    Scheduler schedulerMainThread();

    Scheduler schedulerBackgroundThread();
}

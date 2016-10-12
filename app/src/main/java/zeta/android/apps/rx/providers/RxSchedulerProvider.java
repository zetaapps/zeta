package zeta.android.apps.rx.providers;

import rx.Scheduler;

public interface RxSchedulerProvider {

    Scheduler schedulerMainThread();

    Scheduler schedulerBackgroundThread();
}

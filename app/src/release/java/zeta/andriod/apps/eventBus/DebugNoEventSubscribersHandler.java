package zeta.android.apps.eventBus;

import android.os.Handler;
import android.os.Looper;

import org.greenrobot.eventbus.NoSubscriberEvent;

public class DebugNoEventSubscribersHandler implements NoEventSubscribersHandler {

    @Override
    public void handleNoEventSubscribers(NoSubscriberEvent event) {
        // NOTE: if this behavior ends up being overly aggressive, consider looking into
        // a whitelist or blacklist of certain events for throwing this exception.
        // (using tagging interfaces maybe?)
        new Handler(Looper.getMainLooper()).post(() -> {
            throw new IllegalStateException("No subscribers found for published event: " +
                    event.originalEvent);
        });
    }
}


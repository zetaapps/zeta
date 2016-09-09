package zeta.android.apps.eventBus;

import org.greenrobot.eventbus.NoSubscriberEvent;

/**
 * Interface which enforces explicit handling of when an event has no subscribers.
 * This is the kind of thing you'd normally handle via the event bus -- in fact, that's exactly
 * how EventBus notifies you of an event with no subscribers -- but we should have a stronger
 * contract than that for this issue.
 */
public interface NoEventSubscribersHandler {
    // note intentional lack of @Subscribe annotation here: this is handled internally
    // by BusProvider.
    void handleNoEventSubscribers(NoSubscriberEvent event);
}

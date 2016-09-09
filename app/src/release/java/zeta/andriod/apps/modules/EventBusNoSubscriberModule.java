package zeta.android.apps.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import zeta.android.apps.eventBus.DebugNoEventSubscribersHandler;
import zeta.android.apps.eventBus.NoEventSubscribersHandler;

@Module
@Singleton
public class EventBusNoSubscriberModule {

    @Provides
    NoEventSubscribersHandler providerNoEventSubscribersHandler() {
        return new DebugNoEventSubscribersHandler();
    }

}

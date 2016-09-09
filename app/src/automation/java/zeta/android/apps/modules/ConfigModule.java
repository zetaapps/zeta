package zeta.android.apps.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import zeta.apps.flickr.FlickrConfig;

@Singleton
@Module
public class ConfigModule {

    @Provides
    FlickrConfig provideConfig() {
        //Release uses default configurations
        return FlickrConfig.create().build();
    }
}

package zeta.android.apps.modules;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import zeta.android.apps.environments.FlickrDebugEnv;
import zeta.android.apps.sharedPref.DebugSharedPreferences;
import zeta.apps.flickr.CachePolicy;
import zeta.apps.flickr.Flickr;
import zeta.apps.flickr.FlickrConfig;

@Singleton
@Module
public class ConfigModule {

    @Provides
    FlickrConfig provideConfig(Context context, DebugSharedPreferences sharedPreferences) {
        //Debug app uses 10 min's max age cache policy
        final int currentFlickrEnvironment = sharedPreferences.getCurrentFlickrEnvironment();
        final CachePolicy cachePolicy = CachePolicy.create(context.getCacheDir())
                .setCacheMaxAgeInSeconds(TimeUnit.MINUTES.toMinutes(5))
                .build();
        return FlickrConfig.create()
                .setFlickrEnvironment(transform(currentFlickrEnvironment))
                .setCachePolicy(cachePolicy)
                .build();
    }

    @Flickr.Environment
    private int transform(@FlickrDebugEnv int env) {
        switch (env) {
            case FlickrDebugEnv.DEBUG_STAGE:
                return Flickr.Environment.STAGE;
            case FlickrDebugEnv.DEBUG_UAT:
                return Flickr.Environment.UAT;
            default:
            case FlickrDebugEnv.DEBUG_PROD:
                return Flickr.Environment.PROD;
        }
    }

}

package zeta.android.apps.di.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import zeta.android.apps.sharedPref.DebugSharedPreferences;
import zeta.android.apps.tools.DeveloperTools;
import zeta.android.apps.tools.DeveloperToolsImpl;

@Module
@Singleton
public class DebugModule {

    @Provides
    DeveloperTools providesDeveloperTools(DebugSharedPreferences preferences) {
        return new DeveloperToolsImpl(preferences);
    }

    @Provides
    DebugSharedPreferences providesDebugSharedPreferences(Context context) {
        return new DebugSharedPreferences(context);
    }
}

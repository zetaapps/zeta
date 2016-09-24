package zeta.andriod.apps.di.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import zeta.andriod.apps.sharedPref.DebugSharedPreferences;
import zeta.andriod.apps.tools.DeveloperToolsImpl;
import zeta.android.apps.tools.DeveloperTools;

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

package zeta.android.apps.modules;

import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;
import zeta.android.apps.dagger.OkHttpInterceptors;
import zeta.android.apps.dagger.OkHttpNetworkInterceptors;
import zeta.android.apps.sharedPref.DebugSharedPreferences;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

@Module
@ParametersAreNonnullByDefault
public class OkHttpInterceptorsModule {

    @Provides
    @Singleton
    public HttpLoggingInterceptor provideHttpLoggingInterceptor(DebugSharedPreferences sharedPreferences) {
        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor(message -> Timber.d(message));
        logging.setLevel(sharedPreferences.getHttpLoggingLevel());
        return logging;
    }

    @Provides
    @Singleton
    @OkHttpInterceptors
    public List<Interceptor> provideOkHttpInterceptors(HttpLoggingInterceptor httpLoggingInterceptor) {
        return singletonList(httpLoggingInterceptor);
    }

    @Provides
    @Singleton
    @OkHttpNetworkInterceptors
    public List<Interceptor> provideOkHttpNetworkInterceptors() {
        return emptyList();
    }

}

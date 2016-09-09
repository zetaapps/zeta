package zeta.android.apps.modules;

import android.content.Context;

import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import zeta.android.apps.dagger.OkHttpInterceptors;
import zeta.android.apps.dagger.OkHttpNetworkInterceptors;
import zeta.android.apps.network.ConnectivityCheckInterceptor;
import zeta.android.apps.providers.interfaces.ConnectivityProvider;
import zeta.android.apps.sessionMangement.LazyLoadingPersistentCookieJar;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    @Named
    public OkHttpClient provideOkHttpClient(Context context,
                                            ConnectivityProvider connectivityProvider,
                                            @OkHttpInterceptors List<Interceptor> interceptors,
                                            @OkHttpNetworkInterceptors List<Interceptor> networkInterceptors) {
        //Common interceptors / other OkHttp builder things should go here
        final OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.cookieJar(new LazyLoadingPersistentCookieJar(new SetCookieCache(),
                new SharedPrefsCookiePersistor(context)));

        //Check for no connectivity
        okHttpBuilder.addInterceptor(new ConnectivityCheckInterceptor(connectivityProvider));

        for (Interceptor interceptor : interceptors) {
            okHttpBuilder.addInterceptor(interceptor);
        }

        for (Interceptor networkInterceptor : networkInterceptors) {
            okHttpBuilder.addNetworkInterceptor(networkInterceptor);
        }

        return okHttpBuilder.build();
    }
}

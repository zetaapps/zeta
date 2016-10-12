package zeta.android.apps.di.module;

import android.content.Context;

import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import zeta.android.apps.di.qualifier.OkHttpInterceptors;
import zeta.android.apps.di.qualifier.OkHttpNetworkInterceptors;
import zeta.android.apps.network.interceptor.ConnectivityCheckInterceptor;
import zeta.android.apps.providers.interfaces.ConnectivityProvider;

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

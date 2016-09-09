package zeta.apps.flickr;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import zeta.apps.flickr.environments.FlickrEnvironment;
import zeta.apps.flickr.interceptors.CacheHeadersInterceptor;
import zeta.apps.flickr.qualifiers.FlickrEnv;
import zeta.apps.flickr.qualifiers.FlickrOkHttp;
import zeta.apps.flickr.qualifiers.RetrofitFlickr;

@Module
@ParametersAreNonnullByDefault
public class FlickrModule {

    @Provides
    @Singleton
    @FlickrOkHttp
    public OkHttpClient providesOkHttpClient(CachePolicy cachePolicy, @Named OkHttpClient okHttpClient) {
        final long cacheMaxAgeInSeconds = cachePolicy.getCacheMaxAgeInSeconds();
        final Cache cache = new Cache(cachePolicy.getCacheDirectory(), cachePolicy.getCacheSizeInMb());

        return okHttpClient.newBuilder()
                .cache(cache)
                .addNetworkInterceptor(new CacheHeadersInterceptor(cacheMaxAgeInSeconds))
                .build();
    }

    @Provides
    @Singleton
    public Gson providesGson() {
        return new GsonBuilder()
                .setLenient()               //Oops this is what Flickr API demands!
                .create();
    }

    @Provides
    @Singleton
    public Converter.Factory providesGsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @Singleton
    public CallAdapter.Factory providesRxJavaCallAdapterFactory() {
        return RxJavaCallAdapterFactory.create();
    }

    //region environments

    @Provides
    @Singleton
    @FlickrEnv
    public FlickrEnvironment providesFlickrEnvironment(FlickrConfig flickrConfig) {
        return new FlickrEnvironment(flickrConfig.getFlickrEnvironment());
    }

    //Add more env here

    //endregion environments

    @Provides
    @Singleton
    public CachePolicy providesCachePolicy(Context context, FlickrConfig config) {
        final CachePolicy cachePolicy = config.getCachePolicy();
        return cachePolicy != null ? cachePolicy : CachePolicy.create(context.getCacheDir()).build();
    }

    //region retrofits
    @Provides
    @Singleton
    @RetrofitFlickr
    public Retrofit providesFlickrRetrofit(Converter.Factory gsonConverterFactory,
                                           CallAdapter.Factory rxJavaCallAdapterFactory,
                                           @FlickrOkHttp OkHttpClient okHttpClient,
                                           @FlickrEnv FlickrEnvironment flickrEnvironment) {
        return new Retrofit.Builder()
                .baseUrl(flickrEnvironment.getBaseUrl())
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .validateEagerly(false /**BuildConfig.DEBUG**/)
                .build();
    }
    //endregion retrofits

}
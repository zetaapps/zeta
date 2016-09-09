package zeta.apps.flickr.interceptors;

import java.io.IOException;
import java.net.HttpURLConnection;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * THIS IS VERY DANGEROUS! YET VERY POWERFUL!
 * <p>
 * Well, It's given that overriding the header is dangerous. However if the server doesn't tell us to cache
 * we want to add the cache information to the response on our own.
 * Also our cache policy default values are very sensible & safe.
 * Refer " https://github.com/square/okhttp/wiki/Interceptors "
 * see {@code CachePolicy.DEFAULT_MAX_AGE_IN_SECONDS}
 */
public class CacheHeadersInterceptor implements Interceptor {
    private final long maxAgeInSeconds;

    public CacheHeadersInterceptor(long maxAgeInSeconds) {
        this.maxAgeInSeconds = maxAgeInSeconds;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response originalResponse = chain.proceed(request);

        // Respect the max age from server!
        // If and only if server does NOT provide the cache control.
        // Then we shall add our own. only for 200 response :)
        if (originalResponse.code() == HttpURLConnection.HTTP_OK &&
                originalResponse.cacheControl().maxAgeSeconds() == -1) {
            return originalResponse.newBuilder().header("Cache-Control", "max-age=" + maxAgeInSeconds).build();
        }

        return originalResponse;
    }
}

package zeta.apps.flickr;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

import java.io.File;
import java.util.concurrent.TimeUnit;

@AutoValue
public abstract class CachePolicy implements Parcelable {
    private static final long DEFAULT_MAX_AGE_IN_SECONDS = TimeUnit.SECONDS.toSeconds(30);

    //Default 15 MB space in the cache folder
    private static final long DEFAULT_CACHE_SIZE_IN_MB = 1024 * 1024 * 15;

    public static Builder create(File cacheDirectory) {
        return new AutoValue_CachePolicy.Builder()
                .setCacheDirectory(cacheDirectory)
                .setCacheSizeInMb(DEFAULT_CACHE_SIZE_IN_MB)
                .setCacheMaxAgeInSeconds(DEFAULT_MAX_AGE_IN_SECONDS);
    }

    public abstract File getCacheDirectory();

    public abstract long getCacheSizeInMb();

    public abstract long getCacheMaxAgeInSeconds();

    @AutoValue.Builder
    public static abstract class Builder {

        public abstract Builder setCacheDirectory(File cacheDirectory);

        public abstract Builder setCacheSizeInMb(long cacheSizeInMb);

        public abstract Builder setCacheMaxAgeInSeconds(long maxAgeInSeconds);

        public abstract CachePolicy build();
    }
}

package zeta.apps.flickr;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

import static zeta.apps.flickr.Flickr.Environment;

@AutoValue
public abstract class FlickrConfig implements Parcelable {

    public static Builder create() {
        return new AutoValue_FlickrConfig.Builder()
                .setFlickrEnvironment(Environment.PROD);
    }

    @Nullable
    public abstract CachePolicy getCachePolicy();

    @Environment
    public abstract int getFlickrEnvironment();

    //Add more env if we are using more API's later!

    @AutoValue.Builder
    public static abstract class Builder {

        public abstract Builder setCachePolicy(@Nullable CachePolicy cachePolicy);

        public abstract Builder setFlickrEnvironment(@Environment int flickr);

        public abstract FlickrConfig build();
    }
}

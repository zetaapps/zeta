package zeta.apps.flickr.models.flickr;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class FlickrImages implements Parcelable {

    public static Builder create() {
        return new AutoValue_FlickrImages.Builder();
    }

    @Nullable
    public abstract String getTitle();

    @Nullable
    public abstract String getDescription();

    public abstract String getImageUrl();

    @AutoValue.Builder
    public static abstract class Builder {

        public abstract Builder setTitle(@Nullable String title);

        public abstract Builder setDescription(@Nullable String description);

        public abstract Builder setImageUrl(String imageUrl);

        public abstract FlickrImages build();
    }
}

package zeta.apps.flickr.models.flickr;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

import java.util.List;

@AutoValue
public abstract class FlickrImageModel implements Parcelable {

    public static Builder create() {
        return new AutoValue_FlickrImageModel.Builder();
    }

    @Nullable
    public abstract String getTitle();

    @Nullable
    public abstract String getDescription();

    public abstract List<FlickrImages> getFlickrImages();

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setTitle(@Nullable String title);

        public abstract Builder setDescription(@Nullable String description);

        public abstract Builder setFlickrImages(List<FlickrImages> images);

        public abstract FlickrImageModel build();
    }
}

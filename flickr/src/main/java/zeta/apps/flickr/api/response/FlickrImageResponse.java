package zeta.apps.flickr.api.response;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class FlickrImageResponse {

    @Nullable
    public final String title;
    @Nullable
    public final String description;
    public final String link;
    public final String modified;
    public final String generator;
    @SerializedName("items")
    public final List<FlickrImageItemResponse> imageItems;

    public FlickrImageResponse(@Nullable String title, @Nullable String description,
                               String link,
                               String modified, String generator,
                               List<FlickrImageItemResponse> imageItems) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.modified = modified;
        this.generator = generator;
        this.imageItems = imageItems;
    }
}

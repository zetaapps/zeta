package zeta.apps.flickr.api.response;

import com.google.gson.annotations.SerializedName;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class FlickrMediaResponse {

    @SerializedName("m")
    public final String imageUrl;

    public FlickrMediaResponse(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

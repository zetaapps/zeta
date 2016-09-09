package zeta.apps.flickr.api;

import javax.annotation.ParametersAreNonnullByDefault;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;
import zeta.apps.flickr.api.response.FlickrImageResponse;

/**
 * Issue with the Flickr API.
 * http://stackoverflow.com/questions/8684667/nsjsonserialization
 * <p>
 * Old API is: http://api.flickr.com/services/feeds/photos_public.gne?id=xxxxxxxx&lang=en-us&format=json
 * current API is: http://api.flickr.com/services/feeds/photos_public.gne?id=xxxxxxxx&lang=en-us&format=json&nojsoncallback=1
 */
@ParametersAreNonnullByDefault
public interface FlickrApi {

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("services/feeds/photos_public.gne")
    Observable<Response<FlickrImageResponse>> getImagesFromFlickr(@Query("format") String format,
                                                                  @Query("id") String userId,
                                                                  @Query("nojsoncallback") int callBackType);
}

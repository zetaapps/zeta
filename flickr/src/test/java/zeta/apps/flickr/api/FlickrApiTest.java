package zeta.apps.flickr.api;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import retrofit2.Response;
import retrofit2.mock.BehaviorDelegate;
import rx.observers.TestSubscriber;
import zeta.ApiTestBase;
import zeta.apps.flickr.api.response.FlickrImageItemResponse;
import zeta.apps.flickr.api.response.FlickrImageResponse;

import static org.junit.Assert.assertEquals;

public class FlickrApiTest extends ApiTestBase {

    private FlickrApi mFlickrApi;

    @Before
    public void setUpMockRetrofit() {
        super.setUpMockRetrofit();
        final BehaviorDelegate<FlickrApi> flickrServiceBehaviorDelegate =
                mMockRetrofit.create(FlickrApi.class);
        mFlickrApi = (format, userId, callback) -> flickrServiceBehaviorDelegate.returning(
                buildResponse("flickr_sample_data.json", FlickrImageResponse.class))
                .getImagesFromFlickr(format, userId, callback);
    }

    @Test
    public void testGetImagesFromFlickr_main_object() throws Exception {
        TestSubscriber<Response<FlickrImageResponse>> testSubscriber = new TestSubscriber<>();
        mFlickrApi.getImagesFromFlickr("json", "140440909@N04", 1).subscribe(testSubscriber);

        testSubscriber.assertNoErrors();

        final List<Response<FlickrImageResponse>> onNextEvents = testSubscriber.getOnNextEvents();
        final FlickrImageResponse responses = onNextEvents.get(0).body();

        assertEquals("Uploads from Maverick Photographers", responses.title);
        assertEquals("", responses.description);
        assertEquals("https://www.flickr.com/", responses.generator);
        assertEquals("https://www.flickr.com/photos/140440909@N04/", responses.link);
        assertEquals("2016-07-24T05:08:14Z", responses.modified);
    }

    @Test
    public void testGetImagesFromFlickr_list_size() throws Exception {
        TestSubscriber<Response<FlickrImageResponse>> testSubscriber = new TestSubscriber<>();
        mFlickrApi.getImagesFromFlickr("json", "140440909@N04", 1).subscribe(testSubscriber);

        testSubscriber.assertNoErrors();

        final List<Response<FlickrImageResponse>> onNextEvents = testSubscriber.getOnNextEvents();
        final FlickrImageResponse responses = onNextEvents.get(0).body();

        final List<FlickrImageItemResponse> imageItems = responses.imageItems;
        assertEquals(20, imageItems.size());
    }

    @Test
    public void testGetImagesFromFlickr_list_items() throws Exception {
        TestSubscriber<Response<FlickrImageResponse>> testSubscriber = new TestSubscriber<>();
        mFlickrApi.getImagesFromFlickr("json", "140440909@N04", 1).subscribe(testSubscriber);

        testSubscriber.assertNoErrors();

        final List<Response<FlickrImageResponse>> onNextEvents = testSubscriber.getOnNextEvents();
        final FlickrImageResponse responses = onNextEvents.get(0).body();

        final List<FlickrImageItemResponse> imageItems = responses.imageItems;
        final FlickrImageItemResponse flickrImageItemResponse = imageItems.get(0);

        assertEquals("DSC_9771-Edit-2", flickrImageItemResponse.title);
        assertEquals("https://www.flickr.com/photos/140440909@N04/28222482850/", flickrImageItemResponse.link);
        assertEquals("https://farm9.staticflickr.com/8008/28222482850_b712ff2f77_m.jpg", flickrImageItemResponse.media.imageUrl);

        assertEquals("2016-07-23T15:42:58-08:00", flickrImageItemResponse.dateTaken);
        assertEquals(" <p><a href=\"https://www.flickr.com/people/140440909@N04/\">Maverick Photographers</a> posted a photo:</p> <p><a href=\"https://www.flickr.com/photos/140440909@N04/28222482850/\" title=\"DSC_9771-Edit-2\"><img src=\"https://farm9.staticflickr.com/8008/28222482850_b712ff2f77_m.jpg\" width=\"160\" height=\"240\" alt=\"DSC_9771-Edit-2\" /></a></p> <p>Maverick Photography</p>", flickrImageItemResponse.description);
        assertEquals("2016-07-24T05:08:14Z", flickrImageItemResponse.published);

        assertEquals("nobody@flickr.com (Maverick Photographers)", flickrImageItemResponse.author);
        assertEquals("140440909@N04", flickrImageItemResponse.authorId);
        assertEquals("maverick", flickrImageItemResponse.tags);
    }

}
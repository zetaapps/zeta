package zeta.apps.flickr.models.transformers;

import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import zeta.apps.flickr.api.response.FlickrImageItemResponse;
import zeta.apps.flickr.api.response.FlickrImageResponse;
import zeta.apps.flickr.api.response.FlickrMediaResponse;
import zeta.apps.flickr.models.flickr.FlickrImageModel;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * https://api.flickr.com/services/feeds/photos_public.gne?format=json&id=140440909@N04
 */
public class FlickrModelTransformerTest {

    FlickrModelTransformer mFlickrModelTransformer;

    @Before
    public void setUp() throws Exception {
        mFlickrModelTransformer = new FlickrModelTransformer();
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    public void parse_flickr_images() throws MalformedURLException {
        final FlickrImageModel transform = mFlickrModelTransformer.transform(getMockFlickrResponse());

        assertNotNull(transform);
        assertEquals("Uploads from Maverick Photographers", transform.getTitle());
        assertEquals("", transform.getDescription());
        assertEquals("DSC_9771-Edit-2", transform.getFlickrImages().get(0).getTitle());
        assertEquals(" <p><a href=\"https://www.flickr.com/people/140440909@N04/\">Maverick Photographers</a> posted a photo:</p> <p><a href=\"https://www.flickr.com/photos/140440909@N04/28222482850/\" title=\"DSC_9771-Edit-2\"><img src=\"https://farm9.staticflickr.com/8008/28222482850_b712ff2f77_m.jpg\" width=\"160\" height=\"240\" alt=\"DSC_9771-Edit-2\" /></a></p> <p>Maverick Photography</p>", transform.getFlickrImages().get(0).getDescription());
        assertEquals("https://farm9.staticflickr.com/8008/28222482850_b712ff2f77_m.jpg", transform.getFlickrImages().get(0).getImageUrl());
    }

    private FlickrImageResponse getMockFlickrResponse() {
        String title1 = "DSC_9771-Edit-2";
        String link1 = "https://www.flickr.com/photos/140440909@N04/28222482850/";
        String imageUrl1 = "https://farm9.staticflickr.com/8008/28222482850_b712ff2f77_m.jpg";
        String dateTaken1 = "2016-07-23T15:42:58-08:00";
        String description1 = " <p><a href=\"https://www.flickr.com/people/140440909@N04/\">Maverick Photographers</a> posted a photo:</p> <p><a href=\"https://www.flickr.com/photos/140440909@N04/28222482850/\" title=\"DSC_9771-Edit-2\"><img src=\"https://farm9.staticflickr.com/8008/28222482850_b712ff2f77_m.jpg\" width=\"160\" height=\"240\" alt=\"DSC_9771-Edit-2\" /></a></p> <p>Maverick Photography</p>";
        String published1 = "2016-07-24T05:08:14Z";
        String author1 = "nobody@flickr.com (Maverick Photographers)";
        String authorId1 = "140440909@N04";
        String tags1 = "maverick";
        FlickrMediaResponse mediaResponse = new FlickrMediaResponse(imageUrl1);
        FlickrImageItemResponse imageItemResponse = new FlickrImageItemResponse(title1, link1, dateTaken1, description1, published1, mediaResponse, author1, authorId1, tags1);
        List<FlickrImageItemResponse> responseList = new ArrayList<>();
        responseList.add(imageItemResponse);

        String title = "Uploads from Maverick Photographers";
        String link = "https://www.flickr.com/photos/140440909@N04/";
        String description = "";
        String modified = "2016-07-24T05:08:14Z";
        String generator = "https://www.flickr.com/";
        return new FlickrImageResponse(title, description, link, modified, generator, responseList);
    }
}
package zeta.apps.flickr.models.transformers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

import zeta.apps.flickr.api.response.FlickrImageItemResponse;
import zeta.apps.flickr.api.response.FlickrImageResponse;
import zeta.apps.flickr.models.common.ITransformer;
import zeta.apps.flickr.models.flickr.FlickrImageModel;
import zeta.apps.flickr.models.flickr.FlickrImages;

@ParametersAreNonnullByDefault
public class FlickrModelTransformer implements ITransformer<FlickrImageResponse, FlickrImageModel> {

    @Override
    public FlickrImageModel transform(FlickrImageResponse flickrImageResponse) {
        final List<FlickrImageItemResponse> imageItems = flickrImageResponse.imageItems;
        final int size = imageItems.size();
        final List<FlickrImages> images = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            final FlickrImageItemResponse imageItemResponse = imageItems.get(i);
            final FlickrImages imagesModel = FlickrImages.create()
                    .setTitle(imageItemResponse.title)
                    .setDescription(imageItemResponse.description)
                    .setImageUrl(imageItemResponse.media.imageUrl)
                    .build();
            images.add(imagesModel);
        }

        return FlickrImageModel.create()
                .setTitle(flickrImageResponse.title)
                .setDescription(flickrImageResponse.description)
                .setFlickrImages(images)
                .build();
    }
}
package zeta.apps.flickr.modules;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import zeta.apps.flickr.api.FlickrApi;
import zeta.apps.flickr.api.response.FlickrImageResponse;
import zeta.apps.flickr.managers.params.flickr.FlickrImageManager;
import zeta.apps.flickr.models.common.ITransformer;
import zeta.apps.flickr.models.flickr.FlickrImageModel;
import zeta.apps.flickr.models.transformers.FlickrModelTransformer;
import zeta.apps.flickr.qualifiers.RetrofitFlickr;

@Module
public class FlickrImageModule {

    @Provides
    FlickrApi providesFlickrApi(@RetrofitFlickr Retrofit retrofit) {
        return retrofit.create(FlickrApi.class);
    }

    @Provides
    ITransformer<FlickrImageResponse, FlickrImageModel> providesFlickrModelTransformer() {
        return new FlickrModelTransformer();
    }

    @Provides
    FlickrImageManager providesFlickrImageManager(FlickrApi flickrApi, ITransformer<FlickrImageResponse, FlickrImageModel> transformer) {
        return new FlickrImageManager(flickrApi, transformer);
    }
}

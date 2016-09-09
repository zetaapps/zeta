package zeta.apps.flickr.managers.params.flickr;

import com.fernandocejas.frodo.annotation.RxLogObservable;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.inject.Inject;

import rx.Observable;
import zeta.apps.flickr.api.FlickrApi;
import zeta.apps.flickr.api.response.FlickrImageResponse;
import zeta.apps.flickr.models.common.ITransformer;
import zeta.apps.flickr.models.common.Managers;
import zeta.apps.flickr.models.common.OneOf;
import zeta.apps.flickr.models.flickr.FlickrImageModel;
import zeta.apps.flickr.models.flickr.errors.FlickrException;

@ParametersAreNonnullByDefault
public class FlickrImageManager {

    private final FlickrApi mFlickrApi;
    private final ITransformer<FlickrImageResponse, FlickrImageModel> mFlickerTransfomer;

    @Inject
    public FlickrImageManager(FlickrApi flickrApi, ITransformer<FlickrImageResponse, FlickrImageModel> transformer) {
        mFlickrApi = flickrApi;
        mFlickerTransfomer = transformer;
    }

    @RxLogObservable
    public Observable<OneOf<FlickrImageModel, FlickrException>> getFlickrImages() {
        return mFlickrApi.getImagesFromFlickr("json", "140440909@N04", 1)
                .map(response -> Managers.buildOneOf(
                        response,
                        FlickrException::new,
                        mFlickerTransfomer::transform));
    }

}

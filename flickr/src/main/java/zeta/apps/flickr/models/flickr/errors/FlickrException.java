package zeta.apps.flickr.models.flickr.errors;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.util.Pair;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.annotation.ParametersAreNonnullByDefault;

import zeta.apps.flickr.models.common.errors.BaseException;

@ParametersAreNonnullByDefault
public class FlickrException extends BaseException {

    public FlickrException(@Nullable Pair<String, String> errorCodeAndParam) {
        super(errorCodeAndParam);
    }

    @NonNull
    @Override
    @FlickrError
    public String getErrorCode() {
        if (errorCode == null) {
            return FlickrError.UNKNOWN;
        }

        switch (errorCode) {
            case FlickrError.NO_IMAGES_FOUND:
                return FlickrError.NO_IMAGES_FOUND;
            default:
                return FlickrError.UNKNOWN;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({FlickrError.NO_IMAGES_FOUND, FlickrError.UNKNOWN})
    public @interface FlickrError {
        String NO_IMAGES_FOUND = "no images found";
        String UNKNOWN = "UNKNOWN";
    }
}

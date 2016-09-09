package zeta.apps.flickr.models.common.errors;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@StringDef({BaseError.UNKNOWN, BaseError.EMPTY_RESPONSE})
public @interface BaseError {
    String UNKNOWN = "UNKNOWN ERROR";
    String EMPTY_RESPONSE = "EMPTY_RESPONSE";
}

package zeta.apps.flickr;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Flickr {

    @IntDef({Environment.PROD, Environment.STAGE, Environment.UAT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Environment {
        int PROD = 1;
        int STAGE = 2;
        int UAT = 3;
    }
}

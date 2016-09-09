package zeta.apps.flickr.environments;

import android.support.annotation.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

import zeta.apps.flickr.Flickr;

@ParametersAreNonnullByDefault
public class FlickrEnvironment implements BaseEnvironment {

    @Flickr.Environment
    private final int mEnvironment;
    private final String mBaseUrl;
    String PROD_BASE_URL = "https://api.flickr.com";
    String STAGE_BASE_URL = PROD_BASE_URL;                      //Just using prod env for demo purpose
    String UAT_BASE_URL = STAGE_BASE_URL;                       //Just using prod env for demo purpose

    public FlickrEnvironment(@Flickr.Environment int environment) {
        mEnvironment = environment;
        switch (environment) {
            case Flickr.Environment.STAGE:
                mBaseUrl = PROD_BASE_URL;
                break;
            case Flickr.Environment.UAT:
                mBaseUrl = UAT_BASE_URL;
                break;
            case Flickr.Environment.PROD:
            default:
                mBaseUrl = PROD_BASE_URL;
                break;
        }
    }

    @Override
    @Flickr.Environment
    public int getEnvironment() {
        return mEnvironment;
    }

    @Override
    public String getBaseUrl() {
        return mBaseUrl;
    }

    @Override
    @Nullable
    public String getKey() {
        return null;
    }

    @Override
    @Nullable
    public String getSecureKey() {
        return null;
    }
}

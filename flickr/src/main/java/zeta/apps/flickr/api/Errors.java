package zeta.apps.flickr.api;


import android.support.v4.util.Pair;

import java.net.HttpURLConnection;

import javax.annotation.ParametersAreNonnullByDefault;

import retrofit2.Response;

@ParametersAreNonnullByDefault
public class Errors {
    private static final String KEY_ERROR_RE_AUTH = "KEY_ERROR_RE_AUTH";
    private static final String KEY_EMPTY_RESPONSE_VALUE = "KEY_EMPTY_RESPONSE_VALUE";
    private static final Pair<String, String> sNullPair = Pair.create(null, null);

    public static Pair<String, String> findFirstErrorCodeAndParam(Response<?> response) {
        //All these are hypothetical
        if (response.code() == HttpURLConnection.HTTP_NO_CONTENT) {
            return Pair.create(KEY_EMPTY_RESPONSE_VALUE, null);
        }

        if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
            return Pair.create(KEY_ERROR_RE_AUTH, null);
        }
        //Parse the error here if required
        return sNullPair;
    }
}

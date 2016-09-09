package zeta.apps.flickr.models.common;

import android.support.v4.util.Pair;

import java.net.HttpURLConnection;

import javax.annotation.ParametersAreNonnullByDefault;

import retrofit2.Response;
import rx.functions.Func1;
import zeta.apps.flickr.api.Errors;

@ParametersAreNonnullByDefault
public class Managers {

    public static <R, M, E> OneOf<M, E> buildOneOf(Response<R> response,
                                                   Func1<Pair<String, String>, E> errorMaker,
                                                   ITransformer<R, M> modelTransformer) {
        if (shouldParseError(response)) {
            Pair<String, String> codeAndParam = Errors.findFirstErrorCodeAndParam(response);
            return OneOf.fromFailure(errorMaker.call(codeAndParam));
        }
        return OneOf.fromSuccess(modelTransformer.transform(response.body()));
    }

    private static boolean shouldParseError(Response response) {
        return !response.isSuccessful() || response.code() == HttpURLConnection.HTTP_NO_CONTENT;
    }
}

package zeta.apps.flickr.models.common.errors;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public abstract class BaseException extends Exception {

    @Nullable
    protected final String errorCode;
    @Nullable
    private final String errorParam;
    protected BaseException(@Nullable Pair<String, String> errorCodeAndParam) {
        this(
                (errorCodeAndParam == null ? null : errorCodeAndParam.first),
                (errorCodeAndParam == null ? null : errorCodeAndParam.second));
    }

    private BaseException(@Nullable String errorCode, @Nullable String errorParam) {
        this.errorCode = errorCode;
        this.errorParam = errorParam;
    }

    @NonNull
    public abstract String getErrorCode();

    @Nullable
    public String getErrorParam() {
        return errorParam;
    }

    @Nullable
    public String getRawErrorValue() {
        return errorCode;
    }

}

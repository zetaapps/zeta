package zeta.apps.flickr.models.common;

import android.support.annotation.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class OneOf<S, F> {

    @Nullable
    public final S successValue;
    @Nullable
    public final F failureValue;

    // require using the static factory methods given
    private OneOf(@Nullable S successValue, @Nullable F failureValue) {
        this.successValue = successValue;
        this.failureValue = failureValue;
    }

    public static <S, F> OneOf<S, F> fromSuccess(S successValue) {
        return new OneOf<>(successValue, null);
    }

    public static <S, F> OneOf<S, F> fromFailure(F failureValue) {
        return new OneOf<>(null, failureValue);
    }

    private static boolean objectsEqual(@Nullable Object a, @Nullable Object b) {
        return a == b || (a != null && a.equals(b));
    }

    @Override
    public boolean equals(@Nullable Object o) {
        // lifted from the AOSP "Pair" class
        if (!(o instanceof OneOf)) {
            return false;
        }
        OneOf<?, ?> p = (OneOf<?, ?>) o;
        return objectsEqual(p.successValue, successValue) && objectsEqual(p.failureValue, failureValue);
    }

    @Override
    public int hashCode() {
        // lifted from the AOSP "Pair" class
        return (successValue == null ? 0 : successValue.hashCode())
                ^ (failureValue == null ? 0 : failureValue.hashCode());
    }

    @Override
    public String toString() {
        return "OneOf{" +
                "successValue=" + successValue +
                ", failureValue=" + failureValue +
                '}';
    }
}

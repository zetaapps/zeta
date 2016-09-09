package zeta.apps.flickr.models.common;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public interface Predicate<T> {

    boolean test(T t);

}

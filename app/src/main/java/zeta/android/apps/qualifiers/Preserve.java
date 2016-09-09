package zeta.android.apps.qualifiers;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotates unused code that we still want to preserve.
 */
@Documented
@Inherited
@Retention(RetentionPolicy.SOURCE)
public @interface Preserve {
    /**
     * Why are we preserving this unused code?
     */
    String explanation();
}

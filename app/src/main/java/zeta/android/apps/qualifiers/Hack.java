package zeta.android.apps.qualifiers;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * HACK ALERT!
 * <p>
 * Annotates any element which represents some solution that you or the team overall are
 * unhappy with, but which you intend to ship anyhow because a better solution is blocked on
 * something.  That something should be described in the hackType and explanation elements.
 */
@Retention(RetentionPolicy.SOURCE)
@Documented
@Inherited
public @interface Hack {
    /**
     * A general category describing what's blocking you from solving this problem the right way.
     */
    @HackType String hackType();

    /**
     * Specific details about what's blocking a proper solution, how the hack works, etc.
     */
    String explanation();
}

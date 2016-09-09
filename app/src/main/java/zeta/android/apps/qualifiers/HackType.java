package zeta.android.apps.qualifiers;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * For use with {@link @Hack}.  Describes the kind of blockage you're experiencing which is
 * obliging you to ship a hack.
 * <p>
 * Please add to this list as you see fit.  If you're genuinely unsure if you should add a new
 * HackType or re-use an existing one, err on the side of creating a new one.  We can always
 * collapse the list down easily enough.
 */
@StringDef({HackType.EXPOSED_FOR_TESTING, HackType.FOR_DEMO_ONLY, HackType.SHIPIT})
@Retention(RetentionPolicy.SOURCE)
public @interface HackType {

    String EXPOSED_FOR_TESTING = "Public method is exposed only for testing";
    String FOR_DEMO_ONLY = "Demo type";
    String SHIPIT = "Potentially problematic, but quick/simple solution to a problem that needs " +
            "more time and energy to solve than is currently allowed by a deadline.";

}

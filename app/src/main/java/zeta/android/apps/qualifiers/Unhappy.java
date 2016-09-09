package zeta.android.apps.qualifiers;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotates a solution which you are in some way unhappy with or uneasy about, but you have no
 * solid ideas about better alternatives.
 * <p>
 * Contrast with {@link Hack}: this annotation is for when you don't know the right answer.
 * If you believe you do have the right solution, but just don't have time to implement it, you want to use
 * {@link Hack} with {@link HackType#SHIPIT}.
 */
@Documented
@Inherited
@Retention(RetentionPolicy.SOURCE)
public @interface Unhappy {
    /**
     * Why is the code you're annotating making you unhappy/uneasy?
     */
    String explanation();
}

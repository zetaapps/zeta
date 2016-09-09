package zeta.android.apps.qualifiers;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotates any element which:
 * <ol>
 * <li>exists solely for testing purposes, AND</li>
 * <li>would not exist at all if not for testing</li>
 * </ol>
 * <p>
 * Contrast with:
 * <ul>
 * <li>{@link Hack}: use {@link ExposedForTesting} instead of {@link Hack} when the code in
 * question isn't a "hack" per se, but is considered to be genuinely the only reasonable
 * approach.</li>
 * <li>{@link VisibleForTesting}: unlike {@link VisibleForTesting}, which is for elements that are
 * part of an implementation, but are being made <em>more visible</em> for testing,
 * {@link ExposedForTesting} is for elements which, if not for testing purposes, <em>wouldn't
 * exist at all.</em></li>
 * </ul>
 * Note : This is not same as `ExistsForTesting`
 */
@Retention(RetentionPolicy.SOURCE)
@Documented
@Inherited
public @interface ExposedForTesting {
}

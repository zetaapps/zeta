package zeta.apps.flickr.utils;

import android.support.annotation.Nullable;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.annotation.ParametersAreNonnullByDefault;

import timber.log.Timber;

@ParametersAreNonnullByDefault
public class DateUtils {

    private static final String TAG = DateUtils.class.getSimpleName();
    // Date parsing code from Jackson databind ISO8601Utils.java
    // https://github.com/FasterXML/jackson-databind/blob/master/src/main/java/com/fasterxml/jackson/databind/util/ISO8601Utils.java
    private static final String UTC_ID = "UTC";


    //region Jackson ISO-8601 date string parser
    private static final TimeZone TIMEZONE_Z = TimeZone.getTimeZone(UTC_ID);

    @Nullable
    public static Date parseDate(String dateFormat, @Nullable String rawDate) {
        if (rawDate == null) {
            return null;
        }
        try {
            return new SimpleDateFormat(dateFormat, Locale.US).parse(rawDate,
                    new ParsePosition(0));
        } catch (IllegalArgumentException ex) {
            Timber.e(TAG, "Unable to parse the date: " + rawDate);
            return null;
        }
    }

    @Nullable
    public static Date parseISO8601Date(@Nullable String date) {
        try {
            return doParseISO8601Date(date);
        } catch (Exception e) {
            // normally you don't want to do just catch *any* exception, but
            // it doesn't matter what exception fires here: we want to catch
            // it and return null, not interrupt control flow.
            return null;
        }
    }

    @Nullable
    private static Date doParseISO8601Date(@Nullable String date) throws ParseException {
        if (date == null) {
            return null;
        }

        Exception fail = null;
        int offset = 0;
        try {
            // extract year
            int year = parseInt(date, offset, offset += 4);
            if (checkOffset(date, offset, '-')) {
                offset += 1;
            }

            // extract month
            int month = parseInt(date, offset, offset += 2);
            if (checkOffset(date, offset, '-')) {
                offset += 1;
            }

            // extract day
            int day = parseInt(date, offset, offset += 2);
            // default time value
            int hour = 0;
            int minutes = 0;
            int seconds = 0;
            int milliseconds = 0; // use 0, otherwise returned date will include millis of current time

            // if the value has no time component (and no time zone), we are done
            boolean hasT = checkOffset(date, offset, 'T');

            if (!hasT && (date.length() <= offset)) {
                Calendar calendar = new GregorianCalendar(year, month - 1, day);
                return calendar.getTime();
            }

            if (hasT) {

                // extract hours, minutes, seconds and milliseconds
                hour = parseInt(date, offset += 1, offset += 2);
                if (checkOffset(date, offset, ':')) {
                    offset += 1;
                }

                minutes = parseInt(date, offset, offset += 2);
                if (checkOffset(date, offset, ':')) {
                    offset += 1;
                }
                // second and milliseconds can be optional
                if (date.length() > offset) {
                    char c = date.charAt(offset);
                    if (c != 'Z' && c != '+' && c != '-') {
                        seconds = parseInt(date, offset, offset += 2);
                        if (seconds > 59 && seconds < 63)
                            seconds = 59; // truncate up to 3 leap seconds
                        // milliseconds can be optional in the format
                        if (checkOffset(date, offset, '.')) {
                            offset += 1;
                            int endOffset = indexOfNonDigit(date, offset + 1); // assume at least 1 digit
                            int parseEndOffset = Math.min(endOffset, offset + 3); // parse up to 3 digits
                            int fraction = parseInt(date, offset, parseEndOffset);
                            // compensate for "missing" digits
                            switch (parseEndOffset - offset) { // number of digits parsed
                                case 2:
                                    milliseconds = fraction * 10;
                                    break;
                                case 1:
                                    milliseconds = fraction * 100;
                                    break;
                                default:
                                    milliseconds = fraction;
                            }
                            offset = endOffset;
                        }
                    }
                }
            }

            // extract timezone
            if (date.length() <= offset) {
                throw new IllegalArgumentException("No time zone indicator");
            }

            TimeZone timezone;
            char timezoneIndicator = date.charAt(offset);

            if (timezoneIndicator == 'Z') {
                timezone = TIMEZONE_Z;
                offset += 1;
            } else if (timezoneIndicator == '+' || timezoneIndicator == '-') {
                String timezoneOffset = date.substring(offset);
                offset += timezoneOffset.length();
                // 18-Jun-2015, tatu: Minor simplification, skip offset of "+0000"/"+00:00"
                if ("+0000".equals(timezoneOffset) || "+00:00".equals(timezoneOffset)) {
                    timezone = TIMEZONE_Z;
                } else {
                    String timezoneId = (timezoneOffset.length() == 3)
                            ? "GMT" + timezoneOffset + ":00"
                            : "GMT" + timezoneOffset;
                    timezone = TimeZone.getTimeZone(timezoneId);

                    String act = timezone.getID();
                    if (!act.equals(timezoneId)) {
                        /* 22-Jan-2015, tatu: Looks like canonical version has colons, but we may be given
                         *    one without. If so, don't sweat.
                         *   Yes, very inefficient. Hopefully not hit often.
                         *   If it becomes a perf problem, add 'loose' comparison instead.
                         */
                        String cleaned = act;
                        int idx = act.indexOf(':');
                        if (idx != -1) {
                            cleaned = new StringBuilder(act)
                                    .deleteCharAt(idx)
                                    .toString();
                        }
                        if (!cleaned.equals(timezoneId)) {
                            throw new IndexOutOfBoundsException("Mismatching time zone indicator: "
                                    + timezoneId + " given, resolves to " + timezone.getID());
                        }
                    }
                }
            } else {
                throw new IndexOutOfBoundsException("Invalid time zone indicator '" + timezoneIndicator + "'");
            }

            Calendar calendar = new GregorianCalendar(timezone);
            calendar.setLenient(false);
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month - 1);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minutes);
            calendar.set(Calendar.SECOND, seconds);
            calendar.set(Calendar.MILLISECOND, milliseconds);

            return calendar.getTime();
            // If we get a ParseException it'll already have the right message/offset.
            // Other exception types can convert here.
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            fail = e;
        }

        String input = '"' + date + '"';
        String msg = fail.getMessage();
        if (msg == null || msg.isEmpty()) {
            msg = "(" + fail.getClass().getName() + ")";
        }
        ParseException ex = new ParseException("Failed to parse date " + input + ": " + msg, offset);
        ex.initCause(fail);
        throw ex;
    }

    private static boolean checkOffset(String value, int offset, char expected) {
        return (offset < value.length()) && (value.charAt(offset) == expected);
    }

    private static int parseInt(String value, int beginIndex, int endIndex)
            throws NumberFormatException {
        if (beginIndex < 0 || endIndex > value.length() || beginIndex > endIndex) {
            throw new NumberFormatException(value);
        }
        // use same logic as in Integer.parseInt() but less generic;
        // we're not supporting negative values
        int i = beginIndex;
        int result = 0;
        int digit;
        if (i < endIndex) {
            digit = Character.digit(value.charAt(i++), 10);
            if (digit < 0) {
                throw new NumberFormatException("Invalid number: " + value.substring(beginIndex, endIndex));
            }
            result = -digit;
        }
        while (i < endIndex) {
            digit = Character.digit(value.charAt(i++), 10);
            if (digit < 0) {
                throw new NumberFormatException("Invalid number: " + value.substring(beginIndex, endIndex));
            }
            result *= 10;
            result -= digit;
        }
        return -result;
    }

    private static int indexOfNonDigit(String string, int offset) {
        for (int i = offset; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c < '0' || c > '9') return i;
        }
        return string.length();
    }
    //endregion

}

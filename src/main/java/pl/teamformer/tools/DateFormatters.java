package pl.teamformer.tools;

import java.text.SimpleDateFormat;

public final class DateFormatters {

        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public static final SimpleDateFormat SDF_ALL = new SimpleDateFormat("HH:mm:ss yyyy-MMM-dd");
        public static final SimpleDateFormat SDF_DATE = new SimpleDateFormat("yyyy-MMM-dd");
        public static final SimpleDateFormat SDF_HOUR = new SimpleDateFormat("HH:mm:ss");
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        private DateFormatters() {
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
}

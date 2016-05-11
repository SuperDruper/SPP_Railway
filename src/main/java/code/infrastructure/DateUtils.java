package code.infrastructure;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by PC-Alyaksei on 09.05.2016.
 */
public class DateUtils {

    public static String humanReadableDateTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        return dateFormat.format(date);
    }


    public static String humanReadableDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return dateFormat.format(date);
    }
}

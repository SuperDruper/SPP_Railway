package code.helpers;

/**
 * Created by dzmitry.antonenka on 05.05.2016.
 */
public class StringHelper {
    public static String getUTF8EncodedStringFromString(String str) {
        try {
            byte bytes[] = str.getBytes("UTF-8");
            return new String(bytes, "UTF-8");
        } catch (Exception exc) {
            exc.printStackTrace();
            return str;
        }
    }
}

package android.util;

/**
 * Created by areful on 2020/10/24.
 */
public class Log {
    public static void e(String tag, String msg) {
        System.err.printf("%s\t%s", tag, msg);
    }
}
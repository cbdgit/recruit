package com.example.asus.recruit.widget;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by WaxBerry on 2018/3/13.
 */

public class RegExpValidatorUtils {

    public static boolean isPhone(String str) {
        String regex = "^1[3,4,5,7,8]\\d{9}$";
        return match(regex, str);
    }

    public static boolean isStuNum(String str) {
        String regex = "^3[1,2]1[6,7]\\d{6}$";
        return match(regex, str);
    }

    public static boolean isName(String str) {
        String regex = "^[^ ]+$";
        return match(regex, str);
    }

    public static boolean isClass(String str) {
        String regex = "^[^ ]+$";
        return match(regex, str);
    }

    public static boolean isQQ(String str) {
        String regex = "^\\d{1,}$";
        return match(regex, str);
    }

    public static boolean isEmail(String str) {
        String regex = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        return match(regex, str);
    }

    public static boolean isSelf(String str) {
        String regex = "^[^ ]+$";
        return match(regex, str);
    }

    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

}

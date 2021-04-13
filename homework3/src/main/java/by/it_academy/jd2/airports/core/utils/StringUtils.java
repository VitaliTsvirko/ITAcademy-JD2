package by.it_academy.jd2.airports.core.utils;

/**
 * @author Vitali Tsvirko
 */
public class StringUtils {
    public static boolean isNullOrEmpty(String value){
        return value == null || value.isEmpty();
    }

    public static boolean isNotNullOrEmpty(String value){
        return !isNullOrEmpty(value);
    }

    public static boolean isAnyNullOrEmpty(String... value){
        for (String s : value) {
            if (isNullOrEmpty(s)){
                return true;
            }
        }
        return false;
    }

    public static boolean isAnyNotNullOrEmpty(String... value){
        for (String s : value) {
            if (isNotNullOrEmpty(s)){
                return true;
            }
        }
        return false;
    }

    public static boolean isAllNotNullOrEmpty(String... value){
        for (String s : value) {
            if (isNullOrEmpty(s)){
                return false;
            }
        }
        return true;
    }

    public static boolean isAllNullOrEmpty(String... value){
        int count = 0;

        for (String s : value) {
            if (isNullOrEmpty(s)){
                count++;
            }
        }
        return count == value.length;
    }

}

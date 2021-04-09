package by.it_academy.jd2.airports.core.utils;

/**
 * Created by Vitali Tsvirko
 */
public class StringUtils {
    public static boolean isNullOrEmpty(String value){
        return value == null || value.isEmpty();
    }

    public static boolean isAnyNullOrEmpty(String... value){
        for (String s : value) {
            if (isNullOrEmpty(s)){
                return true;
            }
        }
        return false;
    }

}

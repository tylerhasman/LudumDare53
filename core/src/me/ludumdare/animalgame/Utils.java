package me.ludumdare.animalgame;

public class Utils {

    /**
     * Checks if an object is an instanceof a class.
     * We use this because for the HTML build Class.isInstance doesn't exist :(
     */
    public static <T> boolean isInstanceOf(Class<T> type, Object object) {
        try {
            T objectAsType = (T) object;
        } catch (ClassCastException exception) {
            return false;
        }
        return true;
    }

}

package com.ekalips.instastudy.stuff;

/**
 * Created by Ekalips on 10/2/17.
 */

public class StringUtils {
    public static boolean isEmpty(CharSequence text) {
        return text == null || text.toString().trim().isEmpty();
    }

    public static String getFirstLetters(String text) {
        StringBuilder placeholderText = new StringBuilder("");

        if (text != null && text.length() > 0) {
            String[] parts = text.trim().split(" ");

            for (int i = 0; i < Math.min(2, parts.length); i++) {
                placeholderText.append(parts[i].length() > 0 ? parts[i].substring(0, 1) : "");
            }
        }
        return placeholderText.toString();
    }
}

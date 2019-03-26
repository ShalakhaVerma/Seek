package android.seek.com.seek.utils;

import androidx.annotation.Nullable;

/**
 * Created by vermas12 on 25/3/19.
 */

public class StringUtils {

    /**
     * Get whether or not the given text is null or contains only whitespace characters.
     *
     * @param text The text to verify.
     * @return Return true if empty.
     */
    public static boolean isEmpty(@Nullable CharSequence text) {
        if (text == null) return true;
        int len = text.length();
        for (int i = 0; i < len; i++) {
            if (text.charAt(i) > ' ') return false;
        }
        return true;
    }

}

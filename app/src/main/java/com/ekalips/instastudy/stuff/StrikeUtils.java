package com.ekalips.instastudy.stuff;

import com.ekalips.instastudy.data.user.User;

/**
 * Created by Ekalips on 11/7/17.
 */

public class StrikeUtils {

    public static String getMainGroupName(User user) {
        if (user.getGroups() != null && user.getGroups().size() > 0) {
            return user.getGroups().get(0).getTitle();
        } else {
            return "";
        }
    }

}

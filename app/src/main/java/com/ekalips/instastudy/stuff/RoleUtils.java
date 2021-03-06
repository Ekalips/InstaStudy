package com.ekalips.instastudy.stuff;

import android.content.Context;

import com.ekalips.instastudy.R;

/**
 * Created by ekalips on 12/4/17.
 */

public class RoleUtils {

    public static String getRoleName(Context context, Role role) {
        if (role != null) {
            switch (role) {
                case USER: {
                    return context.getString(R.string.user);
                }
                case PATRIARCH: {
                    return context.getString(R.string.starosta);
                }
                case ADMIN: {
                    return context.getString(R.string.admin);
                }
                default:
                    return "";
            }
        } else {
            return "";
        }
    }

    public static boolean hasPermissions(int roleInt) {
        Role role = Role.getRoleFromInt(roleInt);
        return role != Role.USER;
    }

}

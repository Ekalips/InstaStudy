package com.ekalips.instastudy.stuff;

/**
 * Created by Ekalips on 11/4/17.
 */

public enum Role {
    ADMIN(1), PATRIARCH(4), USER(5);

    private int role;

    Role(int role) {
        this.role = role;
    }

    public int getRole() {
        return role;
    }

    public static Role getRoleFromInt(int roleInt) {
        for (Role role :
                Role.values()) {
            if (role.getRole() == roleInt) {
                return role;
            }
        }
        return USER;
    }

    public boolean canPublishNotifications() {
        return this != USER;
    }
}

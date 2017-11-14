package com.ekalips.instastudy.main.mvvm.model;

import android.support.annotation.IdRes;

/**
 * Created by Ekalips on 11/14/17.
 */

public class MenuItemSelectedEvent {

    @IdRes
    private final int menuItemRes;

    public MenuItemSelectedEvent(int menuItemRes) {
        this.menuItemRes = menuItemRes;
    }

    @IdRes
    public int getMenuItemRes() {
        return menuItemRes;
    }
}

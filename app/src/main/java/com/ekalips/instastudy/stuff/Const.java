package com.ekalips.instastudy.stuff;

import com.ekalips.instastudy.BuildConfig;

/**
 * Created by Ekalips on 11/4/17.
 */

public class Const {

    public static final String CONTENT_AUTHORITY = BuildConfig.APPLICATION_ID + ".provider";

    public static final String NAME_FIREBASE_EVENT_BUS = "firebase_bus";

    public static final int MAX_GROUP_LENGTH = 9;
    public static final int MAX_GROUP_NAME_LENGTH = 4;
    public static final int MAX_GROUP_YEAR_LENGTH = 2;
    public static final int MAX_GROUP_NUMBER_LENGTH = 1;

    public static final int MAX_DAYS = 5;
    public static final int MAX_LESSONS = 6;
    public static final String INITAL_DIR = Const.DIR_DIVIDER;
    public static final String DIR_DIVIDER = "/";
}

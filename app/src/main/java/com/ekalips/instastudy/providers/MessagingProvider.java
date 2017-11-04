package com.ekalips.instastudy.providers;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

import javax.inject.Inject;

/**
 * Created by Ekalips on 10/3/17.
 */

public class MessagingProvider {

    private final Context context;

    @Inject
    public MessagingProvider(Context context) {
        this.context = context;
    }

    public void showToast(String text) {
        showToast(text, Toast.LENGTH_SHORT);
    }

    public void showToast(@StringRes int textRes) {
        showToast(context.getString(textRes));
    }

    public void showToast(@StringRes int textRes, int duration) {
        showToast(context.getString(textRes), duration);
    }

    public void showToast(String text, int duration) {
        Toast.makeText(context, text, duration).show();
    }
}

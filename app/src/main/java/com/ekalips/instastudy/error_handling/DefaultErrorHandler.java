package com.ekalips.instastudy.error_handling;

import android.util.Log;

import com.ekalips.instastudy.R;
import com.ekalips.instastudy.error_handling.throwables.ServerErrorException;
import com.ekalips.instastudy.providers.MessagingProvider;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.exceptions.OnErrorNotImplementedException;

/**
 * Created by Ekalips on 11/4/17.
 */

public class DefaultErrorHandler implements com.wonderslab.base.rx.DefaultErrorHandler {

    private static final String TAG = DefaultErrorHandler.class.getSimpleName();
    private final MessagingProvider messagingProvider;

    @Inject
    public DefaultErrorHandler(MessagingProvider messagingProvider) {
        this.messagingProvider = messagingProvider;
    }

    @Override
    public boolean handleError(Throwable throwable) {
        if (throwable instanceof OnErrorNotImplementedException) {
            throwable = throwable.getCause();
        }

        if (throwable instanceof ServerErrorException) {
            messagingProvider.showToast(R.string.error_server);
            return true;
        }

        if (throwable instanceof IOException) {
            Log.e(TAG, "handleError: ", throwable);
            messagingProvider.showToast(R.string.error_network);
            return true;
        }

        return false;
    }
}

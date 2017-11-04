package com.ekalips.instastudy.error_handling;

import com.ekalips.instastudy.providers.MessagingProvider;

import javax.inject.Inject;

/**
 * Created by Ekalips on 11/4/17.
 */

public class DefaultErrorHandler implements com.wonderslab.base.rx.DefaultErrorHandler {

    private final MessagingProvider messagingProvider;

    @Inject
    public DefaultErrorHandler(MessagingProvider messagingProvider) {
        this.messagingProvider = messagingProvider;
    }

    @Override
    public boolean handleError(Throwable throwable) {
        return false;
    }
}

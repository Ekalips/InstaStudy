package com.ekalips.instastudy.error_handling;

/**
 * Created by Ekalips on 11/4/17.
 */

public class DefaultErrorHandler implements com.wonderslab.base.rx.DefaultErrorHandler{
    @Override
    public boolean handleError(Throwable throwable) {
        return false;
    }
}

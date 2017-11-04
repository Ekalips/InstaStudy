package com.ekalips.instastudy.di.modules;

import com.wonderslab.base.rx.DefaultErrorHandler;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Ekalips on 11/4/17.
 */

@Module
public abstract class ErrorHandlerModule {

    @Binds
    abstract DefaultErrorHandler bindHandler(com.ekalips.instastudy.error_handling.DefaultErrorHandler defaultErrorHandler);

}

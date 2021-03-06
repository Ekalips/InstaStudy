package com.ekalips.instastudy.di.modules;

import com.ekalips.instastudy.error_handling.ErrorThrower;
import com.ekalips.instastudy.providers.ToastProvider;
import com.wonderslab.base.rx.DefaultErrorHandler;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ekalips on 11/4/17.
 */

@Module
public class ErrorHandlerModule {

    @Provides
    @Singleton
    ErrorThrower provideErrorThrower() {
        return new ErrorThrower();
    }

    @Provides
    @Singleton
    DefaultErrorHandler providerDefaultHandler(ToastProvider toastProvider) {
        return new com.ekalips.instastudy.error_handling.DefaultErrorHandler(toastProvider);
    }

}

package com.ekalips.instastudy.di.scopes;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Ekalips on 10/5/17.
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Scope
public @interface SubFragmentScope {
}

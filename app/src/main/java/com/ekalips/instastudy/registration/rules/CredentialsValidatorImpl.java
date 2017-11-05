package com.ekalips.instastudy.registration.rules;

import android.support.annotation.Nullable;

import com.ekalips.instastudy.stuff.StringUtils;

import javax.inject.Inject;

/**
 * Created by Ekalips on 11/5/17.
 */

public class CredentialsValidatorImpl implements CredentialsValidator {

    @Inject
    public CredentialsValidatorImpl() {
    }

    @Override
    public boolean isNameValid(@Nullable String name) {
        return !StringUtils.isEmpty(name);
    }

    @Override
    public boolean inGroupValid(String group) {
        return !StringUtils.isEmpty(group) && group.matches("[A-ZА-Я]{2,4}(-[0-9]{1,2}){1,2}");
    }
}

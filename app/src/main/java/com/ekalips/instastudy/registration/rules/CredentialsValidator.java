package com.ekalips.instastudy.registration.rules;

/**
 * Created by Ekalips on 11/5/17.
 */

public interface CredentialsValidator {

    boolean isNameValid(String name);

    boolean isGroupValid(String group);

}

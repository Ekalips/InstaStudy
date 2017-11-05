package com.ekalips.instastudy.error_handling.throwables;

/**
 * Created by Ekalips on 11/5/17.
 */

public class ClientErrorException extends RequestException{
    public ClientErrorException(int responseCode, String message) {
        super(responseCode, message);
    }
}

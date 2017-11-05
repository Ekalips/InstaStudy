package com.ekalips.instastudy.error_handling.throwables;

/**
 * Created by Ekalips on 11/5/17.
 */

public class RequestException extends RuntimeException {

    private final int responseCode;
    private final String message;

    public RequestException(int responseCode, String message) {
        super(message);
        this.responseCode = responseCode;
        this.message = message;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getMessage() {
        return message;
    }
}

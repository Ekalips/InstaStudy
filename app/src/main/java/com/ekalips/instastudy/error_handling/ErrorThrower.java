package com.ekalips.instastudy.error_handling;

import com.ekalips.instastudy.error_handling.throwables.ClientErrorException;
import com.ekalips.instastudy.error_handling.throwables.ServerErrorException;

import retrofit2.Response;

/**
 * Created by Ekalips on 11/5/17.
 */

public class ErrorThrower {

    public void throwFromResponse(Response response) {
        if (response.code() >= 400 && response.code() < 500) {
            throw new ClientErrorException(response.code(), response.message());
        } else {
            throw new ServerErrorException(response.code(), response.message());
        }
    }

}

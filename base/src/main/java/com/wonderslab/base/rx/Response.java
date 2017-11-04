package com.wonderslab.base.rx;


import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by Ekalips on 11/4/17.
 */

public interface Response {

    interface SuccessConsumer<T> extends Consumer<T> {

    }

    interface UnhandledError extends Consumer<Throwable> {

    }

    interface Complete extends Action {

    }

}

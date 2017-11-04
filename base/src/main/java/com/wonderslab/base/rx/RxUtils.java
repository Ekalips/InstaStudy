package com.wonderslab.base.rx;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.databinding.Observable.OnPropertyChangedCallback;

/**
 * Created by wl-11 on 9/21/17.
 */

public class RxUtils {

    public RxUtils() {
    }

    public static <T> Observable<T> toObservable(@NonNull final ObservableField<T> observableField) {
        return Observable.create(asyncEmitter -> {

            final OnPropertyChangedCallback callback = new OnPropertyChangedCallback() {
                @Override
                public void onPropertyChanged(android.databinding.Observable dataBindingObservable, int propertyId) {
                    if (dataBindingObservable == observableField) {
                        asyncEmitter.onNext(observableField.get());
                    }
                }
            };

            observableField.addOnPropertyChangedCallback(callback);

            asyncEmitter.setCancellable(() -> observableField.removeOnPropertyChangedCallback(callback));

        });
    }

    public static <T> Flowable<T> toFlowable(@NonNull final ObservableField<T> observableField) {
        return Flowable.create(asyncEmitter -> {

            final OnPropertyChangedCallback callback = new OnPropertyChangedCallback() {
                @Override
                public void onPropertyChanged(android.databinding.Observable dataBindingObservable, int propertyId) {
                    if (dataBindingObservable == observableField) {
                        asyncEmitter.onNext(observableField.get());
                    }
                }
            };

            observableField.addOnPropertyChangedCallback(callback);

            asyncEmitter.setCancellable(() -> observableField.removeOnPropertyChangedCallback(callback));

        }, BackpressureStrategy.LATEST);
    }

    public static <T> Observable<T> wrapAsIO(@NonNull final Observable<T> observable) {
        return wrapAs(observable, Schedulers.io());
    }

    public static <T> Observable<T> wrapAsNewThread(@NonNull final Observable<T> observable) {
        return wrapAs(observable, Schedulers.newThread());
    }

    public static <T> Observable<T> wrapAs(@NonNull final Observable<T> observable, Scheduler scheduler) {
        return observable
                .subscribeOn(scheduler)
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> Single<T> wrapAsIO(@NonNull final Single<T> single) {
        return wrapAs(single, Schedulers.io());
    }

    public static <T> Single<T> wrapAs(@NonNull final Single<T> single, Scheduler scheduler) {
        return single
                .subscribeOn(scheduler)
                .observeOn(AndroidSchedulers.mainThread());
    }
}

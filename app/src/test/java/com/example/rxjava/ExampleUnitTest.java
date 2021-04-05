package com.example.rxjava;

import android.util.Log;

import org.junit.Test;

import io.reactivex.ObservableEmitter;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testRx() {

        //io.reactivex.Observable.just(1)

        io.reactivex.Observable.create(new io.reactivex.ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {

            }
        }).map(new Function<String, String>() {
            @Override
            public String apply(@NonNull String s) throws Exception {
                return null;
            }
        }).subscribe(new io.reactivex.Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull String s) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }



}
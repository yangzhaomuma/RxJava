package com.example.rxjava;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;


import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.rxjava", appContext.getPackageName());
    }


    @Test
    public void test() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(Observer<String> t) {
                System.out.println("线程 create：" + Thread.currentThread().getName());
                t.onNext("1");
                t.onNext("11");
                t.onNext("111");
                t.onNext("1111");
                t.onComplete();
            }
        })
                .map(new FunctionMap<String, String>() {
                    @Override
                    public String apply(String s) {
                        System.out.println("线程 map：" + Thread.currentThread().getName());
                        return "1";
                    }
                })
                .observeOn()
                .scribeOn()
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe() {
                        System.out.println("线程 onsubscribe：" + Thread.currentThread().getName());
                        System.out.println("onSubscribe hit");
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("线程 onnext：" + Thread.currentThread().getName());
                        System.out.println("onNext hit:" + s);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("onError hit:" + Log.getStackTraceString(throwable));
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("线程 oncomplete：" + Thread.currentThread().getName());

                        System.out.println("onComplete hit");
                    }
                });
    }
}
package com.example.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

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
package com.example.rxjava;

import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Handler;

public class ObservableObserveOn<T> extends ObservableBase<T, T> {


    public ObservableObserveOn(Observable<T> source) {
        super(source);

    }

    @Override
    protected void realSubscribe(Observer<T> observer) {
        observer.onSubscribe();

        source.subscribe(new ObserverOnObserveOn<>(observer));

    }

    final static class ObserverOnObserveOn<T> extends ObserverBase<T> {
        final android.os.Handler handler;
        final Observer<T> observer;

        public ObserverOnObserveOn(Observer<T> observer) {
            this.observer = observer;
            handler = new android.os.Handler(Looper.getMainLooper());
        }

        @Override
        public void onNext(T t) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    observer.onNext(t);
                }
            });

        }

        @Override
        public void onError(Throwable throwable) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    observer.onError(throwable);
                }
            });
        }

        @Override
        public void onComplete() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    observer.onComplete();
                }
            });
        }
    }


}

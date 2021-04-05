package com.example.rxjava;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ObservableSubscribeOn<T> extends ObservableBase<T,T> {

    ExecutorService executors;
    public ObservableSubscribeOn(Observable<T> source) {
        super(source);
        executors = Executors.newCachedThreadPool();
    }

    @Override
    protected void realSubscribe(Observer<T> observer) {
        observer.onSubscribe();
        executors.submit(new Runnable() {
            @Override
            public void run() {
                source.subscribe(new ObserverOnSubscribeOn<>(observer));
            }
        });
    }

    final static class ObserverOnSubscribeOn<T> extends ObserverBase<T> {
        final Observer<T> observer;

        public ObserverOnSubscribeOn(Observer<T> observer) {
            this.observer = observer;
        }

        @Override
        public void onNext(T t) {
            observer.onNext(t);
        }

        @Override
        public void onError(Throwable throwable) {
            observer.onError(throwable);
        }

        @Override
        public void onComplete() {
            observer.onComplete();
        }
    }


}

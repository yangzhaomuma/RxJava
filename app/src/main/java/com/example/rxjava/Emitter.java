package com.example.rxjava;

public interface Emitter<T> {
    void onNext(T t);

    void onError(Throwable throwable);

    void onComplete();
}

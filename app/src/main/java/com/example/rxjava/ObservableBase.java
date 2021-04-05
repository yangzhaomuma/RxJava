package com.example.rxjava;

public abstract class ObservableBase<T,U> extends Observable<U> {
    final Observable<T> source;

    public ObservableBase(Observable<T> source) {
        this.source = source;
    }
}

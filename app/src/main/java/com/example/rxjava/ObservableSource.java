package com.example.rxjava;

public interface ObservableSource<T> {

    void subscribe(Observer<T> observer);
}

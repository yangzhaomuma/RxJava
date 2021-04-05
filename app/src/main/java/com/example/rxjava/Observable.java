package com.example.rxjava;

public abstract class Observable<T> implements ObservableSource<T>{


    @Override
    public void subscribe(Observer<T> observer) {
        realSubscribe(observer);

    }

    protected abstract void realSubscribe(Observer<T> observer);


    public static<T> Observable<T> create(ObservableOnSubscribe<T> observableOnSubscribe ){
        return new ObservableCreate<>(observableOnSubscribe);
    }

    public <R> Observable<R> map(FunctionMap<T,R> functionMap){
        return new ObservableMap<>(this,functionMap);
    }

    public  Observable<T>  observeOn(){
        return new ObservableObserveOn<>(this);
    }

    public  Observable<T>  scribeOn(){
        return new ObservableSubscribeOn<>(this);
    }
}

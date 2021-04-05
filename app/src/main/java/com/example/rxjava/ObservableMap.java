package com.example.rxjava;

public class ObservableMap<T, U> extends ObservableBase<T,U> {

    final FunctionMap<T, U> functionMap;

    public ObservableMap(Observable<T> source, FunctionMap<T, U> functionMap) {
        super(source);
        this.functionMap = functionMap;
    }

    @Override
    protected void realSubscribe(Observer<U> observer) {
        observer.onSubscribe();
        source.subscribe(new MapObserver<>(observer, functionMap));
    }

    final static class MapObserver<T, U> extends ObserverBase<T> {
        final Observer<U> observer;
        final FunctionMap<T, U> functionMap;

        public MapObserver(Observer<U> observer, FunctionMap<T, U> functionMap) {
            this.observer = observer;
            this.functionMap = functionMap;
        }

        @Override
        public void onNext(T t) {
            U u = functionMap.apply(t);
            observer.onNext(u);
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

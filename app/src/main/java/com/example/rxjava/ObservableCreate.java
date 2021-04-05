package com.example.rxjava;

public class ObservableCreate<T> extends Observable<T> {

    final ObservableOnSubscribe<T> observableOnSubscribe;

    public ObservableCreate(ObservableOnSubscribe<T> observableOnSubscribe) {
        this.observableOnSubscribe = observableOnSubscribe;
    }

    @Override
    protected void realSubscribe(Observer<T> observer) {
        observer.onSubscribe();
        observableOnSubscribe.subscribe(observer);
    }

    final static class ObserverOnCreate<T> extends ObserverBase<T> {
        final Observer<T> observer;

        public ObserverOnCreate(Observer<T> observer) {
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

package com.ykim.android_mvp_base.util;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RxEventBus {
    @Inject
    RxEventBus() {

    }

    private PublishSubject<Object> subject = PublishSubject.create();

    public void post(Object object) {
        subject.onNext(object);
    }

    public Observable<Object> getEvents() {
        return subject;
    }

    public <T> Observable<T> filteredObservable(final Class<T> eventClass) {
        return subject.ofType(eventClass);
    }
}

package com.aliabozaid.grabilitytask.domain.interactors.base;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by aliabozaid on 4/5/16.
 */
public abstract class UseCase {
    private Subscription subscription = Subscriptions.empty();


    protected abstract Observable buildUseCaseObservable();

    public void execute(Subscriber UseCaseSubscriber) {
        this.subscription = this.buildUseCaseObservable()
                .subscribeOn(Schedulers.io())//Schedulers.from(threadExecutor) threadpoolexectors
                .observeOn(AndroidSchedulers.mainThread())// postExecutionThread.getScheduler() your main thread
                .subscribe(UseCaseSubscriber);
    }

    /**
     * Unsubscribes from current {@link rx.Subscription}.
     */
    public void unsubscribe() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}

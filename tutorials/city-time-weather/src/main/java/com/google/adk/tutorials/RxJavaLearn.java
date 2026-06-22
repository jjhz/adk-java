package com.google.adk.tutorials;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class RxJavaLearn {

//    Single<T>	       Exactly 1 value or error
//    Maybe<T>	       0 or 1 value or error
//    Completable	   No value, only success/error
//    Observable<T>	   0 to many values
//    Flowable<T>	   0 to many values + backpressure

    public static void main(String[] args) {

        Single<String> name = Single.just("JJ");

        name.subscribe(
                System.out::println,
                error -> System.out.println(error.toString())
        );

        Flowable<Integer> flowable = Flowable.just(1, 2, 3, 4, 5);
        flowable.subscribe(i -> {
            System.out.println(i);
        });

        flowable.subscribe(new Subscriber<Integer>() {

            private Subscription subscription;

            @Override
            public void onSubscribe(Subscription s) {
                System.out.println("Subscribed");

                this.subscription = s;

                s.request(2);
            }

            @Override
            public void onNext(Integer item) {
                System.out.println("Received: " + item);
                //subscription.request(1);
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("Done");
            }
        });
        System.out.println("-----");
        flowable.flatMap( i -> {
            return Flowable.just(i * 10);
        }, 2).subscribe(System.out::println);

    }
}

package com.google.adk.tutorials;

import io.reactivex.rxjava3.core.Flowable;

public class RxJavaLearn {
    
    public static void main(String[] args) {
        Flowable<String> f = Flowable.just("a", "b", "c");
    }
}

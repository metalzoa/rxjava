package com.itvillage.chapter01.chapter0101;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class ToDoSample {
    public static void main(String[] args) throws InterruptedException {
        Observable.just(100, 200, 300, 400, 500) // data 발행
                .doOnNext(data -> System.out.println(getThreadName() + " : " + "#doOnNext() : " + data)) // data 발행
                .subscribeOn(Schedulers.io()) // main 쓰래드 아닌 다른 쓰래드 에서 실행 하게됨, 이 쓰래드가 실행 되기 전에 종료 되버리면 아무것도 실행 안되기 떄문에 하단에 Thread.sleep 가 존재함
                // RxCachedThread 는 위에 subscribeOn 실행시 나타남, 데이터 발생/흐름 쓰래드 결정
                .observeOn(Schedulers.computation())
                // 데이터 발행과 구독 쓰래드가 분리됨, 발행된 데이터 처리 하는 쓰래드 지정
                // 발행 쓰래드 : RxCachedThreadPool
                // 구독 쓰래드 : RxComputatioinThreadPool
                .filter(number -> number > 300)
                .subscribe(num -> System.out.println(getThreadName() + " : result : " + num)); // 데이터 구독 해서 처리, main 쓰래드 처리

        Thread.sleep(500); // main 쓰래드의 동작을 딜레이 시키는 역할을 함
    }

    public static String getThreadName(){
        return Thread.currentThread().getName();
    }
}

package com.example.rxjava2demo;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class RXActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
        //初始化事件
        init();
        //Rxjava2支持链式编程,下面我们创建被观察者，然后创建观察者并订阅。
        init2();
    }

    private void init2() {
        //创建被观察者
        Observable.
        just("Hello","World").
        //被观察者切换到子线程
        subscribeOn(Schedulers.io()).
        //将观察者切换到主线程
        observeOn(AndroidSchedulers.mainThread()).
        //创建观察者并订阅
        subscribe(new Observer<String>() {
        //Disposable 相当于RxJava1.x中的 Subscription，用于解除订阅
            private Disposable disposable;
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(String s) {
                Log.i("JAVA", "被观察者向观察者发送的数据:" + s);
                if (s == "-1") {   // "-1" 时为异常数据，解除订阅
                    disposable.dispose();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    @SuppressLint("CheckResult")
    private void init() {
        // RxJava2 可以通过下面这几种方法创建被观察者
        Observable.create(new ObservableOnSubscribe<String>() {
            //默认在主线程执行该方法
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("Hello");
                e.onNext("World");

                //结束标志
                e.onComplete();
            }
        });

        //发送多个数据
        Observable.just("Hello","World");

        //发送数据
        Observable.fromArray("Hello","World");

        //发送一个数据
        Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Hello ";
            }
        });
    }
}

package com.example.administrator.mvphelperdemo.login.presenter;

import android.content.Context;
import android.util.Log;

import com.example.administrator.mvphelperdemo.login.contract.MainContract;
import com.example.administrator.mvphelperdemo.login.model.MainModelImpl;
import com.example.administrator.mvphelperdemo.util.RxManager;
import com.example.administrator.mvphelperdemo.util.RxSubscriber;
import com.example.administrator.mvphelperdemo.util.ToastUtils;

import java.io.IOException;

import io.reactivex.Flowable;
import io.reactivex.subscribers.DisposableSubscriber;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.subscriptions.CompositeSubscription;

/**
 *   P层 的实现类中，持有Model和View接口 的引用
 */
public class MainPresenterImpl implements MainContract.Presenter {

    private MainContract.View iMainView;
    private MainContract.Model iMainModel;
    private RxManager mRxmanager;
    public MainPresenterImpl(MainContract.View iMainView){
        this.iMainView = iMainView;
        iMainModel = new MainModelImpl();
        mRxmanager = new RxManager();
    }


    @Override
    public void postSendLoginData(final Context context, final RequestBody requestBody) {
//        Flowable<ResponseBody> flowable = iMainModel.login(requestBody,this);
//
//        DisposableSubscriber<ResponseBody> rxSubscriber = new DisposableSubscriber<ResponseBody>() {
//
//            @Override
//            public void onNext(ResponseBody responseBody) {
//
//            }
//
//            @Override
//            public void onError(Throwable t) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        };
//        //订阅观察者
//        flowable.subscribe(rxSubscriber);
//
//
//        Observable.range(0,10).map(String :: valueOf).forEach(System.out::println);

       iMainModel.login(requestBody,this).
                  //订阅观察者
                  subscribe(new RxSubscriber<ResponseBody>(context,"添加网络",true) {
                      @Override
                      protected void _onNext(ResponseBody responseBody) throws IOException {
                          iMainView.updateUI(responseBody);
                      }

                      @Override
                      protected void _onError(String message) {
                          ToastUtils.showToast(context,message);
                      }
                  });

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFail(String msg) {

    }
}

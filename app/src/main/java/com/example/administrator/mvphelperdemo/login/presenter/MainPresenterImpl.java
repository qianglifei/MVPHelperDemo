package com.example.administrator.mvphelperdemo.login.presenter;

import android.content.Context;
import android.util.Log;

import com.example.administrator.mvphelperdemo.login.contract.MainContract;
import com.example.administrator.mvphelperdemo.login.model.MainModelImpl;
import com.example.administrator.mvphelperdemo.util.RxSubscriber;
import com.example.administrator.mvphelperdemo.util.ToastUtils;

import java.io.IOException;

import io.reactivex.Flowable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 *   P层 的实现类中，持有Model和View接口 的引用
 */
public class MainPresenterImpl implements MainContract.Presenter {

    private MainContract.View iMainView;
    private MainContract.Model iMainModel;

    public MainPresenterImpl(MainContract.View iMainView){
        this.iMainView = iMainView;
        iMainModel = new MainModelImpl();
    }


    @Override
    public void postSendLoginData(final Context context, final RequestBody requestBody) {
        Flowable<ResponseBody> flowable = iMainModel.login(requestBody,this);

        flowable.subscribe();



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

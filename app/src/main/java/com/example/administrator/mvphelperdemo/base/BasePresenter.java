package com.example.administrator.mvphelperdemo.base;

public interface BasePresenter {
    //请求网络成功
    void onSuccess();

    //请求网络失败
    void onFail(String msg);

}

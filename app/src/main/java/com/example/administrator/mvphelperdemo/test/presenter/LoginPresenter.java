package com.example.administrator.mvphelperdemo.test.presenter;


public interface LoginPresenter {
    //登陆成功
    void onSuccess();
    //登陆失败
    void onFail(String msg);
}

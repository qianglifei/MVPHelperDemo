package com.example.administrator.mvphelperdemo.test.model;

import com.example.administrator.mvphelperdemo.test.presenter.LoginPresenter;

public interface LoginModel {
    void login(String name, String psw, LoginPresenter loginPresenter);
}

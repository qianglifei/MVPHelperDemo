package com.example.administrator.mvphelperdemo.test.view;

public interface LoginView {
    void showToast(String msg);

    void onSuccess();

    String getName();

    String getPassWord();
}

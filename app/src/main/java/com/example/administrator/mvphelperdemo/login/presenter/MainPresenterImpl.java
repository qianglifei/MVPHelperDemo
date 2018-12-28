package com.example.administrator.mvphelperdemo.login.presenter;

import com.example.administrator.mvphelperdemo.login.contract.MainContract;

/**
 *   P层 的实现类中，持有Model和View 的引用
 */
public class MainPresenterImpl implements MainContract.Presenter {

    private MainContract.View iMainView;
    private MainContract.Model iMainModel;

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFail(String msg) {

    }
}

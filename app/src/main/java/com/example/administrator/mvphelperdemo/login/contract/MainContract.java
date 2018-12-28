package com.example.administrator.mvphelperdemo.login.contract;

import com.example.administrator.mvphelperdemo.base.BaseModel;
import com.example.administrator.mvphelperdemo.base.BasePresenter;
import com.example.administrator.mvphelperdemo.base.BaseView;

public interface MainContract {

    interface Model extends BaseModel {
        void login(String name,String password,Presenter iPresenter);
    }

    interface View extends BaseView {
        String getName();
        String getPassword();

        void clearName();
        void clearPassword();


    }

    interface Presenter extends BasePresenter {

    }
}

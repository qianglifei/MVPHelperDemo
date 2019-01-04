package com.example.administrator.mvphelperdemo.login.contract;

import android.content.Context;

import com.example.administrator.mvphelperdemo.base.BaseModel;
import com.example.administrator.mvphelperdemo.base.BasePresenter;
import com.example.administrator.mvphelperdemo.base.BaseView;

import java.io.IOException;

import io.reactivex.Flowable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public interface MainContract {

    interface Model extends BaseModel {
        Flowable<ResponseBody> login(RequestBody requestBody, Presenter iPresenter);
    }

    interface View extends BaseView {
        void updateUI(ResponseBody responseBody);
        void clearName();
        void clearPassword();
    }

    interface Presenter extends BasePresenter {
        void postSendLoginData(Context context, RequestBody requestBody);
    }
}

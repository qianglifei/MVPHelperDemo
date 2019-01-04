package com.example.administrator.mvphelperdemo.login.model;

import com.example.administrator.mvphelperdemo.api.RetrofitApiService;
import com.example.administrator.mvphelperdemo.http.RetrofitManager;
import com.example.administrator.mvphelperdemo.login.contract.MainContract;

import com.example.administrator.mvphelperdemo.util.RxSchedulers;

import io.reactivex.Flowable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class MainModelImpl implements MainContract.Model {

    @Override
    public Flowable<ResponseBody> login(RequestBody requestBody, MainContract.Presenter iPresenter) {
        return  RetrofitManager.
                getInstance().
                createReq(RetrofitApiService.class).
                loginData(requestBody).
                compose(RxSchedulers.<ResponseBody>io_main());
    }
}

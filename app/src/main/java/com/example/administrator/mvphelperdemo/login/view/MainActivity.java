package com.example.administrator.mvphelperdemo.login.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.mvphelperdemo.R;
import com.example.administrator.mvphelperdemo.base.BaseActivity;
import com.example.administrator.mvphelperdemo.login.contract.MainContract;
import com.example.administrator.mvphelperdemo.login.presenter.MainPresenterImpl;
import com.example.administrator.mvphelperdemo.util.ToastUtils;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class MainActivity extends BaseActivity<MainPresenterImpl> implements MainContract.View {
    String name = "123456";
    String password = "a00000";
    private MainContract.Presenter mPresenter;
    private Context mContext = this;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initEvent();
    }

    @Override
    protected void createPresenter() {
        mPresenter = new MainPresenterImpl(this);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_main;
    }

    private void initEvent() {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("yhzh","15210603710");
        hashMap.put("yhmm","a00000");
        String obj = new JSONObject(hashMap).toString();
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),obj);
        mPresenter.postSendLoginData(mContext,body);
    }

    @Override
    public void showDialog(String msg) {

    }

    @Override
    public void dismissDialog(String msg) {

    }

    @Override
    public void updateUI(ResponseBody responseBody) {
        try {
            ToastUtils.showToast(mContext,responseBody.string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

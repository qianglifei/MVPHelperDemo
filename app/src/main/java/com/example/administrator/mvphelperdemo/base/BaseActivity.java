package com.example.administrator.mvphelperdemo.base;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.mvphelperdemo.R;
import com.example.administrator.mvphelperdemo.customview.StateLayout;
import com.example.administrator.mvphelperdemo.util.RxManager;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {
    private Context mContext = this;
    private RxManager mRxManager;
    private StateLayout mStateLayout;
    private T mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStateLayout = new StateLayout(mContext);
        mRxManager = new RxManager();

        setContentView(getLayoutResource());

        createPresenter();

        //初始化Title
        initTitle();

        //初始化事件
        initEvent();
    }

    protected abstract void createPresenter();

    public abstract int getLayoutResource();

    private void initTitle() {

    }

    private void initEvent() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRxManager.clear();
    }
}

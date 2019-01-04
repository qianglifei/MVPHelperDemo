package com.example.administrator.mvphelperdemo.util;

import android.content.Context;


import com.example.administrator.mvphelperdemo.R;

import java.io.IOException;

import io.reactivex.subscribers.DisposableSubscriber;


/********************使用例子********************/

/**
    apiService.
    login(mobile, verifyCode).
    //省略
    subscribe(new RxSubscriber<User user>(mContext,false) {
    @Override
    public void _onNext(User user) {
        //处理user
    }
    @Override
    public void _onError(String msg) {
        ToastUtil.showShort(mActivity, msg);
    });
*/

public abstract class RxSubscriber<T> extends DisposableSubscriber<T> {

    private Context mContext;
    private String msg;
    private boolean showDialog=true;

    /**
     * 是否显示浮动dialog
     */
    public void showDialog() {
        this.showDialog= true;
    }
    public void hideDialog() {
        this.showDialog= true;
    }

    public RxSubscriber(Context context, String msg, boolean showDialog) {
        this.mContext = context;
        this.msg = msg;
        this.showDialog = showDialog;
    }

//    public RxSubscriber(Context context) {
//        this(context, context.getString(R.string.loading),true);
//    }
//
//    public RxSubscriber(Context context, boolean showDialog) {
//        this(context, context.getString(R.string.loading),showDialog);
//    }



    @Override
    public void onStart() {
        super.onStart();
        if (showDialog) {
            try {
                LoadingDialog.showLoadingDialog(mContext,0,msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        if (showDialog){
            LoadingDialog.cancelLoadingDialog();
        }

        if (!NetWorkUtils.isNetConnected(mContext)) {
            //网络
            _onError("");
        } else if (e instanceof ServerException) {
            //服务器
            _onError(e.getMessage());
        } else {
            //其它
            _onError(e.getMessage());
        }
    }

    @Override
    public void onComplete() {
        if (showDialog){
            LoadingDialog.cancelLoadingDialog();
        }
    }

    protected abstract void _onNext(T t) throws IOException;

    protected abstract void _onError(String message);

}

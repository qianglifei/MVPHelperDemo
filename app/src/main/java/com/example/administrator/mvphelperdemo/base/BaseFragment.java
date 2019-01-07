package com.example.administrator.mvphelperdemo.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.mvphelperdemo.customview.StateLayout;
import com.example.administrator.mvphelperdemo.util.RxManager;

public abstract class BaseFragment extends Fragment {
    public RxManager mRxManager;
    private Context mContext;
    private StateLayout mStateLayout;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mStateLayout = new StateLayout(mContext);
        //设置布局
        mStateLayout.setContentView(getLayoutResource());

        //初始化TitleBar
        initTitle();
        return mStateLayout;
    }

    private void initTitle() {

    }

    public abstract int getLayoutResource();

}

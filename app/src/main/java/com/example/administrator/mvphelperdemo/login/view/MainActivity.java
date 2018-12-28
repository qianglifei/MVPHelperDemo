package com.example.administrator.mvphelperdemo.login.view;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.mvphelperdemo.login.contract.MainContract;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    String name = "123456";
    String password = "a00000";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void showDialog(String msg) {

    }

    @Override
    public void dismissDialog(String msg) {

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void clearName() {
        name = "";
    }

    @Override
    public void clearPassword() {
        password = "";
    }
}

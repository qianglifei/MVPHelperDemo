package com.example.administrator.mvphelperdemo.test.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.administrator.mvphelperdemo.R;
import com.example.administrator.mvphelperdemo.test.presenter.LoginPresenterImpl;

public class LoginActivity extends AppCompatActivity  implements LoginView{

    private LoginPresenterImpl mLoginPreImpl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        //
        login();
    }

    private void initView() {
        //实现我们的PresenterImpl实现类
        mLoginPreImpl = new LoginPresenterImpl(this);
    }

    @Override
    public void showToast(String msg) {
        
    }

    @Override
    public void onSuccess() {
        Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getName() {
        return "name";
    }

    @Override
    public String getPassWord() {
        return "18303012347";
    }


    public void login(){
        mLoginPreImpl.login();
    }
}

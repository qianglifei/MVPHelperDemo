package com.example.administrator.mvphelperdemo.test.presenter;


import com.example.administrator.mvphelperdemo.test.model.LoginModel;
import com.example.administrator.mvphelperdemo.test.view.LoginView;
import com.example.administrator.mvphelperdemo.test.model.LoginModelImpl;

public class LoginPresenterImpl implements LoginPresenter {
    private LoginView iLoginView;
    //声明出LoginModel业务处理层的接口
    private LoginModel iLoginModel;

    /**
     * 这里我们写一个构造函数
     * @param iLoginView 哪个View调用我们，就需要传输哪个View的参数
     */
    public LoginPresenterImpl(LoginView iLoginView){
        this.iLoginView = iLoginView;
        //再new出我们业务处理接口的具体实现类
        iLoginModel = new LoginModelImpl();
    }

    public void login(){
        //将View 中的参数获取出来
        String name = iLoginView.getName();
        String passWord = iLoginView.getPassWord();

        /**
         * 调用业务处理Model层登陆方法
         *
         * 参数1 ： 账号
         * 参数2 ： 密码
         * 参数2 : 登陆状态监听器
         */
        iLoginModel.login(name,passWord,this);

    }


    /**
     * 实现接口中的方法
     */
    @Override
    public void onSuccess() {

    }

    @Override
    public void onFail(String msg) {

    }
}

package com.example.administrator.mvphelperdemo.test.model;


import com.example.administrator.mvphelperdemo.test.presenter.LoginPresenter;

public class LoginModelImpl implements LoginModel {
    @Override
    public void login(String name, String psw, LoginPresenter loginPresenter) {
        //这里我们对数据进行简单的业务逻辑判断
        UserBean ub = getBean();
        if (name.isEmpty() || psw.isEmpty()){
            loginPresenter.onFail("账号密码不能为空");
        }else if (name.equalsIgnoreCase(ub.accountNumber) && psw.equalsIgnoreCase(ub.password)){
            loginPresenter.onSuccess();
        }else {
            loginPresenter.onFail("账号密码错误");
        }
    }


    /**
     * 模拟数据账号和密码，【其实大可不必这样写，你也可以直接定义两个String值就好了】
     * @return
     */
    public UserBean getBean(){
        UserBean bean = new UserBean();
        bean.accountNumber = "zhangsan";
        bean.password = "123456";
        return bean;
    }

    /**
     * 这里就先模拟一下网络数据,定义一下账号和密码
     */
    public class UserBean{
        public String accountNumber ;
        public String password;
    }
}

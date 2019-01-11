package com.example.administrator.mvphelperdemo.login.model;

import com.example.administrator.mvphelperdemo.api.RetrofitApiService;
import com.example.administrator.mvphelperdemo.http.RetrofitManager;
import com.example.administrator.mvphelperdemo.login.contract.MainContract;

import com.example.administrator.mvphelperdemo.util.RxSchedulers;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.internal.functions.ObjectHelper;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import static java.nio.ByteBuffer.wrap;

public class MainModelImpl implements MainContract.Model {
    /**
     *
     * @param requestBody
     * @param iPresenter
     * @return
     */



    @Override
    public Flowable<ResponseBody> login(RequestBody requestBody, MainContract.Presenter iPresenter) {
        return  RetrofitManager.
                getInstance().
                createReq(RetrofitApiService.class).
                loginData(requestBody).
                //线程切换，封装
                compose(RxSchedulers.io_main());
    }

    /**
     * compose 操作符
     * public final <R> Observable<R> compose(ObservableTransformer<? super T, ? extends R> composer) {
     *    return wrap(((ObservableTransformer<T, R>) ObjectHelper.requireNonNull(composer, "composer is null")).apply(this));
     * }
     *
     *   compose 区分于lift（lift 是Map和flatMap等操作的根本），compose是对ObservableSource
     *  本身进行操作的，apply（this），里面的this就是代表ObservableSource自己，而lift是对ObservableSource
     *  发送的数据进行操作的
     *
     *  compose(transformer)  接受一个参数 ObservableTransformer,tansformer 是一个接口，我们
     *  实现他，为了避免 Object-> Observable 的强转，我们在方法里定义了泛型，这个结合自己的返回数据和逻辑自行修改
     *
     *
     */
}

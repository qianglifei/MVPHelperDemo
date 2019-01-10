package com.example.administrator.mvphelperdemo.login.presenter;

import android.content.Context;
import android.util.Log;

import com.example.administrator.mvphelperdemo.login.contract.MainContract;
import com.example.administrator.mvphelperdemo.login.model.MainModelImpl;
import com.example.administrator.mvphelperdemo.util.RxManager;
import com.example.administrator.mvphelperdemo.util.RxSubscriber;
import com.example.administrator.mvphelperdemo.util.ToastUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.IOException;

import io.reactivex.Flowable;
import io.reactivex.subscribers.DisposableSubscriber;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.subscriptions.CompositeSubscription;

import static android.content.ContentValues.TAG;

/**
 *   P层 的实现类中，持有Model和View接口 的引用
 */
public class MainPresenterImpl implements MainContract.Presenter {

    private MainContract.View iMainView;
    private MainContract.Model iMainModel;
    private RxManager mRxmanager;
    public MainPresenterImpl(MainContract.View iMainView){
        this.iMainView = iMainView;
        iMainModel = new MainModelImpl();
        mRxmanager = new RxManager();
    }


    @Override
    public void postSendLoginData(final Context context, final RequestBody requestBody) {
        Flowable<ResponseBody> flowable = iMainModel.login(requestBody,this);
        /**
         *
         * 我的理解就是subscribeWith中会把方法参数返回回去接收的是ResourceSubscriber,
         *
         * 而ResourceSubscriber实现了Disposable接口所以,一般subscribeWith用到使用Rx请求接口的这种情况,
         *
         * 订阅后把请求参数返回回去,可以添加到CompositeDisposable中方便绑定Activity生命周期取消
         *
         * 其实subscribe中除了重载参数是Observer的其他也都返回了Dispose对象,
         *
         * 至于为什么这个方法没有返回暂时也不知道作者怎么想的.
         *
         * 因为它返回值是void所以在请求接口时最好还是使用subscribeWith,添加订阅关系更方便了
         *
         */
        DisposableSubscriber<ResponseBody> rxSubscriber = new DisposableSubscriber<ResponseBody>() {

           @Override
            public void onNext(ResponseBody responseBody) {
                iMainView.updateUI(responseBody);
            }

            @Override
            public void onError(Throwable t) {
                Log.i(TAG, "===onError: " + t.getMessage());
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "===onComplete: " + "加载成功");
            }
        };

        //订阅观察者
        flowable.subscribe(rxSubscriber);

        boolean ble = rxSubscriber.isDisposed();

        Log.i(TAG, "===postSendLoginData: " + ble);
        //rxSubscriber.dispose();


//       iMainModel.login(requestBody,this).
//                  //订阅观察者
//                  subscribeWith(new RxSubscriber<ResponseBody>(context,"添加网络",true) {
//                      @Override
//                      protected void _onNext(ResponseBody responseBody) {
//                          iMainView.updateUI(responseBody);
//                      }
//
//                      @Override
//                      protected void _onError(String message) {
//                          ToastUtils.showToast(context,message);
//                      }
//                  });


    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFail(String msg) {

    }
}

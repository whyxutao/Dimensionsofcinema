package com.bawei.cinema.base;

import android.widget.Toast;

import com.bawei.cinema.app.App;
import com.bawei.cinema.bean.Data;
import com.bawei.cinema.model.DataCallBack;
import com.bawei.cinema.model.IRequest;
import com.bawei.cinema.until.RetrofitUntil;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * author: 徐涛
 * data: 2019/11/5 19:19:58
 * function:
 */
public abstract class BasePresenter {

    private DataCallBack dataCallBack;

    public BasePresenter(DataCallBack dataCallBack) {
        this.dataCallBack = dataCallBack;
    }

    public void getRetrofitData(Object...args){

        if (RetrofitUntil.getInstance().NetWork(App.context)){

            IRequest iRequest = RetrofitUntil.getInstance().create(IRequest.class);

            getData(iRequest,args).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Data>() {
                        @Override
                        public void accept(Data o) throws Exception {

                            if (o.status.equals("0000")){
                                dataCallBack.onSuccess(o.result);
                            }else {
                                dataCallBack.onError(o);
                            }

                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            throwable.printStackTrace();
                        }
                    });
        }else {
            Toast.makeText(App.context, "没有网络,请连接您的网络", Toast.LENGTH_SHORT).show();
        }
    }

    protected abstract Observable getData(IRequest iRequest,Object...args);

    public void desTory(){
        dataCallBack = null;
    }

}

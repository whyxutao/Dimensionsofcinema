package com.bawei.cinema.model;

import com.bawei.cinema.bean.Data;

/**
 * author: 徐涛
 * data: 2019/11/5 19:19:48
 * function:
 */
public interface DataCallBack<T> {

    void onSuccess(T data);
    void onError(Data data);

}

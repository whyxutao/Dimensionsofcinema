package com.bawei.cinema.until;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author: 徐涛
 * data: 2019/11/5 19:19:52
 * function:
 */
public class RetrofitUntil {

    private static RetrofitUntil retrofitUntil;

    private Retrofit retrofit;

    //单例模式
    public static RetrofitUntil getInstance(){
        if (retrofitUntil==null) {
            retrofitUntil = new RetrofitUntil();
        }
        return retrofitUntil;
    }

    //网络判断
    public boolean NetWork(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info!=null) {
            return info.isAvailable();
        }
        return false;
    }

    //Retrofit网络请求
   private RetrofitUntil(){

        //OkHttp拦截器
       HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
       interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

       //OkHttp
       OkHttpClient okHttpClient = new OkHttpClient.Builder()
               .addInterceptor(interceptor)
               .build();

       retrofit = new Retrofit.Builder()
               .client(okHttpClient)
               .baseUrl("http://172.17.8.100/movieApi/")
               .addConverterFactory(GsonConverterFactory.create())
               .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
               .build();
   }

    public <T> T create(final Class<T> service) {
        return retrofit.create(service);
    }


    }

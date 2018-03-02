package com.example.asus.recruit.widget;

import com.example.asus.recruit.application.MyApplication;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AddCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        if (!MyApplication.getCookie().equals("")) {
            builder.addHeader("Cookie", MyApplication.getCookie());
        }
        return chain.proceed(builder.build());
    }
}

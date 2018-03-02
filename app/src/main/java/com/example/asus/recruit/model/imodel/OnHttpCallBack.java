package com.example.asus.recruit.model.imodel;

public interface OnHttpCallBack<T> {

    void onSuccessful(T t);
    void onFailed(String errorMsg);
}

package com.example.asus.recruit.model;

import android.util.Log;

import com.example.asus.recruit.application.MyApplication;
import com.example.asus.recruit.contract.RecruitContract;
import com.example.asus.recruit.entity.HTTPResult;
import com.example.asus.recruit.entity.ValidateResult;
import com.example.asus.recruit.model.imodel.OnHttpCallBack;
import com.example.asus.recruit.retrofitService.IRecruitService;
import com.example.asus.recruit.widget.RetrofitProviderUtil;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Headers;
import retrofit2.Response;

public class RecruitModel implements RecruitContract.IRecruitModel {
    @Override
    public void commit(String challenge, String validate, String seccode, String name, String number, String sex, String majorAndClass,
                       String duties, String phone, String shortNumber, String email, String QQ, String organize, String speciality,
                       String introduce, String purpose, final OnHttpCallBack<Response<HTTPResult>> callBack) {

//        MyApplication.setCookie("");
        RetrofitProviderUtil.getRetrofitProvider()
                .create(IRecruitService.class)
                .commit(challenge, validate, seccode, name, number, sex, majorAndClass, duties, phone, shortNumber, email, QQ, organize, speciality, introduce, purpose)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<Response<HTTPResult>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("RecruitModel","onSubscribe");
                    }

                    @Override
                    public void onNext(Response<HTTPResult> response) {
                        Log.d("RecruitModel","onNext");

//                        Headers headers = response.headers();
//                        List<String> list = headers.values(("Set-Cookie"));
//                        if (list.size() == 0) {
//                            callBack.onFailed("cookie为空！");
//                            Log.d("RecruitModel", "cookie为空！");
//                            return;
//                        } else {
//                            MyApplication.setCookie(list.get(0));
//                            Log.d("RecruitModel", "cookie不为空！");
//                        }

                        Log.d("RecruitModel", "cookie:" + MyApplication.getCookie());

                        HTTPResult result = response.body();
                        if (result != null) {
                            if (result.getResult().equals("success")){
                                callBack.onSuccessful(response);
                                Log.d("RecruitModel", "SUCCESS");
                            }else {
                                Log.d("RecruitModel", "FAILED");
                                Log.d("RecruitModel",result.getMessage());
                                if (response.code() != 200 || result.getMessage().length() > 50) {
                                    callBack.onFailed("服务器正在维护中，请稍后再尝试！");
                                }else {
                                    callBack.onFailed(result.getMessage());
                                }
                            }
                        }else {
                            callBack.onFailed("服务器正在维护中，请稍后再尝试！");
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.d("RecruitModel","onError");
                        t.printStackTrace();
                        if (t instanceof HttpException) {
                            HttpException httpException = (HttpException) t;
                            int code = httpException.code();
                            if (code == 500 || code == 404) {
                                callBack.onFailed("服务器出错");
                            }
                        } else if (t instanceof ConnectException) {
                            callBack.onFailed("网络断开,请打开网络!");
                        } else if (t instanceof SocketTimeoutException) {
                            callBack.onFailed("网络连接超时!!");
                        } else {
                            callBack.onFailed("发生未知错误" + t.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                        Log.d("RecruitModel","onComplete");
                    }
                });
    }

    @Override
    public void withoutValidate(String name, String number, String sex, String majorAndClass,
                       String duties, String phone, String shortNumber, String email, String QQ, String organize, String speciality,
                       String introduce, String purpose, final OnHttpCallBack<Response<HTTPResult>> callBack) {

//        MyApplication.setCookie("");
        RetrofitProviderUtil.getRetrofitProvider()
                .create(IRecruitService.class)
                .withoutValidate(name, number, sex, majorAndClass, duties, phone, shortNumber, email, QQ, organize, speciality, introduce, purpose)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<Response<HTTPResult>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("RecruitModel3","onSubscribe");
                    }

                    @Override
                    public void onNext(Response<HTTPResult> response) {
                        Log.d("RecruitModel3","onNext");

                        Log.d("RecruitModel3", "cookie:" + MyApplication.getCookie());

                        HTTPResult result = response.body();
                        if (result != null) {
                            if (result.getResult().equals("success")){
                                callBack.onSuccessful(response);
                                Log.d("RecruitModel3", "SUCCESS");
                            }else {
                                Log.d("RecruitModel3", "FAILED");
                                Log.d("RecruitModel3",result.getMessage());
                                if (response.code() != 200 || result.getMessage().length() > 50) {
                                    callBack.onFailed("服务器正在维护中，请稍后再尝试！");
                                }else {
                                    callBack.onFailed(result.getMessage());
                                }
                            }
                        }else {
                            callBack.onFailed("服务器正在维护中，请稍后再尝试！");
                        }

                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.d("RecruitModel","onError");
                        t.printStackTrace();
                        if (t instanceof HttpException) {
                            HttpException httpException = (HttpException) t;
                            int code = httpException.code();
                            if (code == 500 || code == 404) {
                                callBack.onFailed("服务器出错");
                            }
                        } else if (t instanceof ConnectException) {
                            callBack.onFailed("网络断开,请打开网络!");
                        } else if (t instanceof SocketTimeoutException) {
                            callBack.onFailed("网络连接超时!!");
                        } else {
                            callBack.onFailed("发生未知错误" + t.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                        Log.d("RecruitModel","onComplete");
                    }
                });
    }

    @Override
    public void getValidate(String t, final OnHttpCallBack<Response<ValidateResult>> callBack) {

        MyApplication.setCookie("");
        RetrofitProviderUtil.getRetrofitProvider()
                .create(IRecruitService.class)
                .getValidate(t)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<Response<ValidateResult>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("RecruitModel2","onSubscribe");
                    }

                    @Override
                    public void onNext(Response<ValidateResult> response) {
                        Log.d("RecruitModel2","onNext");

                        Headers headers = response.headers();
                        List<String> list = headers.values(("Set-Cookie"));
                        if (list.size() == 0) {
                            callBack.onFailed("cookie为空！");
                            Log.d("RecruitModel2", "cookie为空！");
                            return;
                        } else {
                            MyApplication.setCookie(list.get(0));
                            Log.d("RecruitModel2", "cookie不为空！");
                        }

                        Log.d("RecruitModel2", "cookie:" + MyApplication.getCookie());

                        ValidateResult result = response.body();
                        if (result.getSuccess().equals("1")){
                            callBack.onSuccessful(response);
                            Log.d("RecruitModel2", "SUCCESS");
                        }else {
                            Log.d("RecruitModel2", "FAILED");
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.d("RecruitModel2","onError");
                        t.printStackTrace();
                        if (t instanceof HttpException) {
                            HttpException httpException = (HttpException) t;
                            int code = httpException.code();
                            if (code == 500 || code == 404) {
                                callBack.onFailed("服务器出错");
                            }
                        } else if (t instanceof ConnectException) {
                            callBack.onFailed("网络断开,请打开网络!");
                        } else if (t instanceof SocketTimeoutException) {
                            callBack.onFailed("网络连接超时!!");
                        } else {
                            callBack.onFailed("发生未知错误" + t.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                        Log.d("RecruitModel2","onComplete");
                    }
                });
    }
}


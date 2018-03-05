package com.example.asus.recruit.contract;

import com.example.asus.recruit.entity.HTTPResult;
import com.example.asus.recruit.entity.ValidateResult;
import com.example.asus.recruit.model.imodel.OnHttpCallBack;

import retrofit2.Response;

public class RecruitContract {

    public interface IRecruitView{
        void onSuccess(HTTPResult httpResult);
        void onFailed(String errMsg);
        void onValidateSuccess(ValidateResult validateResult);
    }

    public interface IRecruitModel{
        void commit(String challenge, String validate
                , String seccode, String name
                , String number, String sex
                , String majorAndClass, String duties
                , String phone, String shortNumber
                , String email, String QQ
                , String organize, String speciality
                , String introduce, String purpose, OnHttpCallBack<Response<HTTPResult>> callBack);

        void getValidate(String t, OnHttpCallBack<Response<ValidateResult>> callBack);
    }

    public interface IRecruitPresenter{
        void commit(String challenge, String validate
                , String seccode, String name
                , String number, String sex
                , String majorAndClass, String duties
                , String phone, String shortNumber
                , String email, String QQ
                , String organize, String speciality
                , String introduce, String purpose);

        void getValidate(String t);
    }
}

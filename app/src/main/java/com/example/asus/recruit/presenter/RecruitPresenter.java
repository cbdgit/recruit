package com.example.asus.recruit.presenter;

import com.example.asus.recruit.contract.RecruitContract;
import com.example.asus.recruit.entity.HTTPResult;
import com.example.asus.recruit.entity.ValidateResult;
import com.example.asus.recruit.model.RecruitModel;
import com.example.asus.recruit.model.imodel.OnHttpCallBack;

import retrofit2.Response;


public class RecruitPresenter implements RecruitContract.IRecruitPresenter {

    private RecruitContract.IRecruitModel mIRecruitModel;
    private RecruitContract.IRecruitView mIRecruitView;

    public RecruitPresenter(RecruitContract.IRecruitView mIRecruitView) {

        this.mIRecruitView = mIRecruitView;
        mIRecruitModel = new RecruitModel();
    }

    @Override
    public void commit(String challenge, String validate, String seccode, String name, String number, String sex,
                       String majorAndClass, String duties, String phone, String shortNumber, String email,
                       String QQ, String organize, String speciality, String introduce, String purpose) {

        mIRecruitModel.commit(challenge, validate, seccode, name, number, sex, majorAndClass, duties, phone, shortNumber, email, QQ, organize,
                speciality, introduce, purpose, new OnHttpCallBack<Response<HTTPResult>>() {
            @Override
            public void onSuccessful(Response<HTTPResult> result) {
                mIRecruitView.onSuccess(result.body());
            }

            @Override
            public void onFailed(String errorMsg) {
                mIRecruitView.onFailed(errorMsg);
            }
        });
    }

    @Override
    public void getValidate(String t) {

        mIRecruitModel.getValidate(t, new OnHttpCallBack<Response<ValidateResult>>() {
                    @Override
                    public void onSuccessful(Response<ValidateResult> result) {
                        //mIRecruitView.onSuccess(result.body());
                    }

                    @Override
                    public void onFailed(String errorMsg) {
                        //mIRecruitView.onFailed(errorMsg);
                    }
                });

    }

    @Override
    public void withoutValidate(String name, String number, String sex, String majorAndClass, String duties,
                                String phone, String shortNumber, String email, String QQ, String organize, String speciality, String introduce, String purpose) {

        mIRecruitModel.withoutValidate(name, number, sex, majorAndClass, duties, phone, shortNumber, email, QQ, organize,
                speciality, introduce, purpose, new OnHttpCallBack<Response<HTTPResult>>() {
                    @Override
                    public void onSuccessful(Response<HTTPResult> result) {
                        mIRecruitView.onSuccess(result.body());
                    }

                    @Override
                    public void onFailed(String errorMsg) {
                        mIRecruitView.onFailed(errorMsg);
                    }
                });
    }
}

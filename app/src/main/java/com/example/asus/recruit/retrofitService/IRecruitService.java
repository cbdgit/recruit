package com.example.asus.recruit.retrofitService;

import com.example.asus.recruit.entity.HTTPResult;
import com.example.asus.recruit.entity.ValidateResult;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IRecruitService {

    @POST("validate")
    @FormUrlEncoded
    Observable<Response<HTTPResult>> commit(@Field("challenge") String challenge, @Field("validate") String validate
            , @Field("seccode") String seccode, @Field("name") String name
            , @Field("number") String number, @Field("sex") String sex
            , @Field("majorAndClass") String majorAndClass, @Field("duties") String duties
            , @Field("phone") String phone, @Field("shortNumber") String shortNumber
            , @Field("email") String email, @Field("QQ") String QQ
            , @Field("organize") String organize, @Field("speciality") String speciality
            , @Field("introduce") String introduce, @Field("purpose") String purpose);


    @GET("ready")
    Observable<Response<ValidateResult>> getValidate(@Query("t") String date);

}

package com.example.iem.cirad.rest;

/**
 * Created by iem on 10/01/2017.
 */

import com.example.iem.cirad.Model.Pojo.Action;
import com.example.iem.cirad.Model.Pojo.TypeAction;
import com.example.iem.cirad.Model.Pojo.Farm;
import com.example.iem.cirad.Model.Pojo.Parcel;
import com.example.iem.cirad.Model.Pojo.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface ApiInterface {


    @FormUrlEncoded
    @POST("/CiradWB/web/index.php/login")
    Call<User> getAuthentification(@Field("login")String login, @Field("password") String password);

    @FormUrlEncoded
    @POST("/sendActions")
    Call<Object> sendActions(@Body Parcel parcel,@Body List<Action> action);

    @FormUrlEncoded
    @POST("/CiradWB/web/index.php/parceluser")
    Call<List<Parcel>> getParcelByUserId(@Field("id") int id);

    @GET("/CiradWB/web/index.php/typeaction")
    Call<List<TypeAction>> getActionType();



    //UNUSED
    @GET("/CiradWB/web/index.php/parcel")
    List<Call<Parcel>> getParcel();
    //UNUSED
    @GET("/CiradWB/web/index.php/farm")
    Call<List<Farm>> getFarm();



}

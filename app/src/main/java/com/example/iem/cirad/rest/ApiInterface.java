package com.example.iem.cirad.rest;

/**
 * Created by iem on 10/01/2017.
 */

import com.example.iem.cirad.Model.Pojo.Action;
import com.example.iem.cirad.Model.Pojo.ActionType;
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

    @POST("/sendActions")
    Call<Object> sendActions(@Body Parcel parcel,@Body List<Action> action);

    @GET("/CiradWB/web/index.php//parcel/{Id}")
    Call<List<Parcel>> getParcelByUserId(@Path("Id") int Id);

    @GET("/typeAction")
    List<Call<ActionType>> getActionType();

    @GET("/CiradWB/web/index.php/parcel")
    List<Call<Parcel>> getParcel();

    @GET("/CiradWB/web/index.php/farm")
    Call<List<Farm>> getFarm();



}

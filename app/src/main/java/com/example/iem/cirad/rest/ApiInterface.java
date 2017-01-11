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
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface ApiInterface {



    @POST("/login")
    Call<User> getAuthentification(@Body User user);

    @POST("/sendActions")
    Call<Object> sendActions(@Body Parcel parcel,@Body List<Action> action);

    @GET("/parcel/{Id}")
    List<Call<Parcel>> getParcelByUserId(@Path("Id") int Id);

    @GET("/typeAction")
    List<Call<ActionType>> getActionType();



}

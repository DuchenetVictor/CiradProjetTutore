package com.example.iem.cirad.rest;

import com.example.iem.cirad.Model.Pojo.Action;
import com.example.iem.cirad.Model.Pojo.ActionType;
import com.example.iem.cirad.Model.Pojo.Farm;
import com.example.iem.cirad.Model.Pojo.Parcel;
import com.example.iem.cirad.Model.Pojo.User;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by iem on 10/01/2017.
 */

public class ApiClient  {
    public static final String BASE_URL = "http://138.68.89.183/CiradWB/web/index.php/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static ApiInterface getApiInterface(){
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        return apiService;
    }

    public static void CheckAuthetification(User user){


        Call<User> call = getApiInterface().getAuthentification(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }

        });
    }

    public static void getParcelByUserId(int id){

        Call<List<Parcel>> call = (Call<List<Parcel>>) getApiInterface().getParcelByUserId(id);

        call.enqueue(new Callback<List<Parcel>>() {
            @Override
            public void onResponse(Call<List<Parcel>> call, Response<List<Parcel>> response) {
                String e = "";
            }

            @Override
            public void onFailure(Call<List<Parcel>> call, Throwable t) {
                String f = "";

            }

        });
    }

    public static void getActionType(){

        Call<List<ActionType>> call = (Call<List<ActionType>>) getApiInterface().getActionType();

        call.enqueue(new Callback<List<ActionType>>() {
            @Override
            public void onResponse(Call<List<ActionType>> call, Response<List<ActionType>> response) {
                String e = "";
            }

            @Override
            public void onFailure(Call<List<ActionType>> call, Throwable t) {
                String f = "";

            }

        });
    }

    public static void sendAction(Parcel parcel,List<Action> actions){

        Call<Object> call =  getApiInterface().sendActions(parcel,actions);

        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }



        });
    }



}

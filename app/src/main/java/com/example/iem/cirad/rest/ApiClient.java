package com.example.iem.cirad.rest;

import android.content.Context;
import android.content.Intent;

import com.example.iem.cirad.Activity.LoginActivity;
import com.example.iem.cirad.Activity.dashBoardActivity;
import com.example.iem.cirad.Model.Manager.ParcelManager;
import com.example.iem.cirad.Model.Manager.TypeActionManager;
import com.example.iem.cirad.Model.Manager.UserManager;
import com.example.iem.cirad.Model.Pojo.Action;
import com.example.iem.cirad.Model.Pojo.TypeAction;
import com.example.iem.cirad.Model.Pojo.Farm;
import com.example.iem.cirad.Model.Pojo.Parcel;
import com.example.iem.cirad.Model.Pojo.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Multipart;

/**
 * Created by iem on 10/01/2017.
 */

public class ApiClient  {
      static long id = -1;

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


    public static void GetFarm(){


        Call<List<Farm>> call =  getApiInterface().getFarm();

        call.enqueue(new Callback<List<Farm>>() {
            @Override
            public void onResponse(Call<List<Farm>> call, Response<List<Farm>> response) {
            }

            @Override
            public void onFailure(Call<List<Farm>> call, Throwable t) {
            }

        });
    }

    public static void CheckParcel(){
        Call<List<Parcel>> call = (Call<List<Parcel>>) getApiInterface().getParcel();

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


    public static void CheckAuthentification(String login, String password, final Context context){

        Call<User> call = getApiInterface().getAuthentification(login,password);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                response.body().setIsConnected(true);
                UserManager.getInstance(context).setUser(response.body());
                getParcelByUserId(response.body().getId(),context);

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                Intent i = new Intent(context, LoginActivity.class);
                context.startActivity(i);

            }

        });
    }

    public static void getParcelByUserId(int id, final Context context){

        Call<List<Parcel>> call =  getApiInterface().getParcelByUserId(id);

        call.enqueue(new Callback<List<Parcel>>() {
            @Override
            public void onResponse(Call<List<Parcel>> call, Response<List<Parcel>> response) {
                if(response.body() == null)
                {
                }
                else {
                    for (Parcel parcel : response.body()) {
                        ParcelManager.getInstance(context).setParcel(parcel);
                    }
                }
                getTypeAction(context);

            }

            @Override
            public void onFailure(Call<List<Parcel>> call, Throwable t) {

            }

        });
    }

    public static void getTypeAction(final Context context){

        Call<List<TypeAction>> call =  getApiInterface().getActionType();

        call.enqueue(new Callback<List<TypeAction>>() {
            @Override
            public void onResponse(Call<List<TypeAction>> call, Response<List<TypeAction>> response) {
                ArrayList<String> listActionType = new ArrayList<>();
                for (TypeAction typeAction : response.body()) {
                    listActionType.add(typeAction.getName());
                }
                TypeActionManager.getInstance(context).setTypesAction(listActionType);
                Intent i = new Intent(context, dashBoardActivity.class);
                context.startActivity(i);
            }

            @Override
            public void onFailure(Call<List<TypeAction>> call, Throwable t) {

            }

        });
    }

    public static void sendAction(Action actions){


        Call<String> call =  getApiInterface().sendActions(actions);


        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }

        });

    }



}

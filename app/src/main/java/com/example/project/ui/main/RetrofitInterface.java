package com.example.project.ui.main;

import com.example.project.ListItem;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import java.util.HashMap;

public interface RetrofitInterface {

    @POST("/user/login")
    Call<JSONObject> executeLogin(@Body HashMap<String,String> map);

    @POST("/user/signup")
    Call<JSONObject> executeSignup(@Body HashMap<String,String> map);

    @POST("/broker/login")
    Call<JSONObject>executeBlogin(@Body HashMap<String,String> map);

}

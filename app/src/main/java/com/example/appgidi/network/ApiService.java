package com.example.appgidi.network;

import com.example.appgidi.models.LoginResponse;
import com.example.appgidi.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/api/login")
    Call<LoginResponse> loginUser(@Body User user);
}
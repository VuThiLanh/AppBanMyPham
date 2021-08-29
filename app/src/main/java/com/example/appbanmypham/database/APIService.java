package com.example.appbanmypham.database;

import com.example.appbanmypham.model.ThanhToan;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIService {
    //https://demo1777035.mockable.io/DonHang/posts
    //https://jsonplaceholder.typicode.com/posts
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    APIService apiService = new Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(APIService.class);

    @POST("posts")
    Call<ThanhToan> sendPosts(@Body ThanhToan thanhToan);
}

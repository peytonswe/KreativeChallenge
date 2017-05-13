package com.example.ksusha.kreativechallenge;


import com.example.ksusha.kreativechallenge.entities.Challenge;
import com.example.ksusha.kreativechallenge.entities.User;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    String BASE_URL = "http://10.0.3.112";

    OkHttpClient httpClient = new OkHttpClient.Builder()
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build();


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL).client(httpClient)
            .addConverterFactory(GsonConverterFactory.create()).build();

    ApiService instance = retrofit.create(ApiService.class);

    @GET("/challenge/all")
    @Headers("UUID: 5555-5555-5555-5555")
    Call<List<Challenge>> getChallenges();

    @GET("/challenge/{id}")
    @Headers("UUID: 5555-5555-5555-5555")
    Call<Challenge> getChallenge(@Query("id") int id);

    @FormUrlEncoded
    @POST("/register")
    @Headers("UUID: 5555-5555-5555-5555")
    Call<User> register(@Field("firstName") String firstName, @Field("lastName") String lastName);


    @POST("/challenge/add")
    @Headers("UUID: 5555-5555-5555-5555")
    Call<ResponseBody> getChallenge();

    @POST("/challenge/{id}/complete")
    @Headers("UUID: 5555-5555-5555-5555")
    Call<ResponseBody> getChallengeComplete(@Query("id") int id);

    @POST("/challenge/{id}/rating")
    @Headers("UUID: 5555-5555-5555-5555")
    Call<ResponseBody> getChallengeRating(@Query("id") int id);


}


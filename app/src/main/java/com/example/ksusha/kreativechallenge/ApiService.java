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
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
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
        //@Headers("UUID: 5555-5555-5555-5555")
    Call<List<Challenge>> getChallenges(@Header("UUID") String UUID);

    @GET("/challenge/{id}")
        //@Headers("UUID: 5555-5555-5555-5555")
    Call<Challenge> getChallenge(@Path("id") int id, @Header("UUID") String UUID);

    @FormUrlEncoded
    @POST("/register")
        //@Headers("UUID: 5555-5555-5555-5555")
    Call<User> register(@Field("firstName") String firstName, @Field("lastName") String lastName,
                        @Header("UUID") String UUID);

    @FormUrlEncoded
    @POST("/challenge/add")
        //Headers("UUID: 5555-5555-5555-5555")
    Call<ResponseBody> addChallenge(@Field("title") String title, @Field("description") String description,
                                    @Field("latitude") double latitude, @Field("longitude") double longitude,
                                    @Header("UUID") String UUID);

    @POST("/challenge/{id}/complete")
        //@Headers("UUID: 5555-5555-5555-5555")
    Call<ResponseBody> getChallengeComplete(@Path("id") int id, @Header("UUID") String UUID);

    @FormUrlEncoded
    @POST("/challenge/{id}/rate")
        //@Headers("UUID: 5555-5555-5555-5555")
    Call<ResponseBody> getChallengeRating(@Path("id") int id, @Field("rating") String rating,
                                          @Header("UUID") String UUID);


}


package com.example.demoretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApliInterface {
    @GET("volley_array.json")
    Call<List<Movie>> getMovies();
}
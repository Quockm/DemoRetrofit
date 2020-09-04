package com.example.demoretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
        List<Movie> movieList;
        RecyclerView recyclerView;
        RecyclerAdapter recyclerAdapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            movieList = new ArrayList<>();
            recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerAdapter = new RecyclerAdapter(getApplicationContext(),movieList);
            recyclerView.setAdapter(recyclerAdapter);

            ApliInterface apiService = ApiClient.getClient().create(ApliInterface.class);
            retrofit2.Call<List<Movie>> call = apiService.getMovies();

            call.enqueue(new Callback<List<Movie>>() {
                @Override
                public void onResponse(retrofit2.Call<List<Movie>> call, Response<List<Movie>> response) {
                    movieList = response.body();
                    Log.d("TAG","Response = "+movieList);
                    recyclerAdapter.setMovieList(movieList);
                }

                @Override
                public void onFailure(retrofit2.Call<List<Movie>> call, Throwable t) {
                    Log.d("TAG","Response = "+t.toString());
                }
            });
        }
    }
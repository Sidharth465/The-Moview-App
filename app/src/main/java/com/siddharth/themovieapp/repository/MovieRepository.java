package com.siddharth.themovieapp.repository;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.siddharth.themovieapp.R;
import com.siddharth.themovieapp.model.Movie;
import com.siddharth.themovieapp.model.Result;
import com.siddharth.themovieapp.serviceapi.MovieApiService;
import com.siddharth.themovieapp.serviceapi.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MovieRepository {
    private final MutableLiveData<List<Movie>> mutableLiveData = new MutableLiveData<>();
    private final Application application;
    private ArrayList<Movie> movies;

    public MovieRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Movie>> getMutableLiveData() {
        MovieApiService movieApiService = RetrofitInstance.getService();
        Call<Result> call = movieApiService.getPopularMovies(application.getApplicationContext().getString(R.string.api_key));
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();
                if (result != null && result.getResults() != null) {
                    movies = (ArrayList<Movie>) result.getResults();
                    mutableLiveData.setValue(movies);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Result> call, @NonNull Throwable t) {
                mutableLiveData.setValue(null);
            }

        });
        return mutableLiveData;
    }
}

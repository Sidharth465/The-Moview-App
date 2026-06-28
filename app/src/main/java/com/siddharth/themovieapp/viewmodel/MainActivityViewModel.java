package com.siddharth.themovieapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.siddharth.themovieapp.model.Movie;
import com.siddharth.themovieapp.repository.MovieRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    private final MovieRepository repository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.repository = new MovieRepository(application);
    }


    public LiveData<List<Movie>> getAllMovies() {

        
        return repository.getMutableLiveData();

    }
}

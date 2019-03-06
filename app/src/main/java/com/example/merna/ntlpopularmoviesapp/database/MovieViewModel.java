package com.example.merna.ntlpopularmoviesapp.database;

import android.app.Application;
import android.util.Log;

import com.example.merna.ntlpopularmoviesapp.model.Movie;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MovieViewModel extends AndroidViewModel {

    private static final String TAG = MovieViewModel.class.getSimpleName();
    private LiveData<List<Movie>> favMovies;
    private List<Movie> movies;
    private int currentSelectedSort = -1;
    private int adapterPosition = -1;

    public MovieViewModel(Application application) {
        super(application);
        MovieDB database = MovieDB.getInstance(this.getApplication());
        Log.d(TAG, "Actively retrieving the tasks from the DataBase");
        favMovies = database.movieDao().getAllMovies();
    }

    public LiveData<List<Movie>> getFavMovies() {
        return favMovies;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> popularMovie) {
        this.movies = popularMovie;
    }

    public int getCurrentSelectedSort() {
        return currentSelectedSort;
    }

    public void setCurrentSelectedSort(int currentSelectedSort) {
        this.currentSelectedSort = currentSelectedSort;
    }

    public int getAdapterPosition() {
        return adapterPosition;
    }

    public void setAdapterPosition(int adapterPosition) {
        this.adapterPosition = adapterPosition;
    }
}

package com.example.merna.ntlpopularmoviesapp.presenter.main;

import com.example.merna.ntlpopularmoviesapp.model.Movie;

import java.util.List;

import androidx.lifecycle.LiveData;

public interface IMainPresenter {

    void getPopularMovies();

    void getTopRatedMovies();

    LiveData<List<Movie>> getFavoritesMovies();
}

package com.example.merna.ntlpopularmoviesapp.service;

import com.example.merna.ntlpopularmoviesapp.model.MoviesModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieAPIInterface {

    @GET("movie/popular?api_key=8a4b73fbb82e276723a2b32b8c49f736")
    Call<MoviesModel> getPopularMovies();

    @GET("movie/top_rated?api_key=8a4b73fbb82e276723a2b32b8c49f736")
    Call<MoviesModel> getTopRatedMovies();
}

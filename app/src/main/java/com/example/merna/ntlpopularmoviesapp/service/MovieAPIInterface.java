package com.example.merna.ntlpopularmoviesapp.service;

import com.example.merna.ntlpopularmoviesapp.model.MoviesModel;
import com.example.merna.ntlpopularmoviesapp.model.ReviewModel;
import com.example.merna.ntlpopularmoviesapp.model.TrailerModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MovieAPIInterface {

    @GET("movie/popular?api_key=8a4b73fbb82e276723a2b32b8c49f736")
    Call<MoviesModel> getPopularMovies();

    @GET("movie/top_rated?api_key=8a4b73fbb82e276723a2b32b8c49f736")
    Call<MoviesModel> getTopRatedMovies();


    @GET("/3/movie/{id}/videos?api_key=8a4b73fbb82e276723a2b32b8c49f736")
    Call<TrailerModel> getTrailersByMovieId(@Path("id") String id);

    @GET("/3/movie/{id}/reviews?api_key=8a4b73fbb82e276723a2b32b8c49f736")
    Call<ReviewModel> getReviewsByMovieId(@Path("id") String id);
}

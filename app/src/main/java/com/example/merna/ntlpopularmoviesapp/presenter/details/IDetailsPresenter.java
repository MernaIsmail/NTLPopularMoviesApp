package com.example.merna.ntlpopularmoviesapp.presenter.details;

import com.example.merna.ntlpopularmoviesapp.model.Movie;

public interface IDetailsPresenter {

    void getReviewsById(String id);

    void getTrailersById(String id);

    void deleteFavoriteMovie(Movie movie);

    void insertFavoriteMovie(Movie movie);

    void setupFavBtn(String id);
}

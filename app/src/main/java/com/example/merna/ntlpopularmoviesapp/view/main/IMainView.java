package com.example.merna.ntlpopularmoviesapp.view.main;

import android.content.Context;

import com.example.merna.ntlpopularmoviesapp.model.Movie;
import com.example.merna.ntlpopularmoviesapp.model.MoviesModel;

import java.util.List;

public interface IMainView {

    void updateView(List<Movie> moviesModel);

    void showLoading();

    void hideLoading();

    Context getViewContext();
}

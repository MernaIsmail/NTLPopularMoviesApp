package com.example.merna.ntlpopularmoviesapp.view.main;

import com.example.merna.ntlpopularmoviesapp.model.MoviesModel;

public interface IMainView {

    void updateView(MoviesModel moviesModel);

    void showLoading();

    void hideLoading();

}

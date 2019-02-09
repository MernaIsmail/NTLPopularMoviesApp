package com.example.merna.ntlpopularmoviesapp.presenter;

import android.util.Log;

import com.example.merna.ntlpopularmoviesapp.model.Movie;
import com.example.merna.ntlpopularmoviesapp.model.MoviesModel;
import com.example.merna.ntlpopularmoviesapp.service.MovieService;
import com.example.merna.ntlpopularmoviesapp.view.IMainView;
import com.example.merna.ntlpopularmoviesapp.view.MainFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements IMainPresenter {

    private MovieService movieService;
    private IMainView mainView;

    public MainPresenter(MainFragment mainFragment) {
        mainView = mainFragment;
        this.movieService = new MovieService();
    }

    @Override
    public void getPopularMoves() {
        movieService.getAPI()
                .getPopularMovies()
                .enqueue(new Callback<MoviesModel>() {
                    @Override
                    public void onResponse(Call<MoviesModel> call, Response<MoviesModel> response) {
                        MoviesModel data = response.body();
                        if (data != null && data.getResult() != null) {
                            List<Movie> result = data.getResult();
                            mainView.updateView(data);
                        }
                    }

                    @Override
                    public void onFailure(Call<MoviesModel> call, Throwable t) {
                        try {
                            throw new InterruptedException("Something went wrong!");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Log.d("data", "\"Something went wrong!\"");
                        }
                    }
                });
    }
}

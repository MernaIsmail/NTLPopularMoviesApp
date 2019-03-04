package com.example.merna.ntlpopularmoviesapp.presenter.main;

import android.util.Log;

import com.example.merna.ntlpopularmoviesapp.database.MovieDB;
import com.example.merna.ntlpopularmoviesapp.database.MovieViewModel;
import com.example.merna.ntlpopularmoviesapp.model.Movie;
import com.example.merna.ntlpopularmoviesapp.model.MoviesModel;
import com.example.merna.ntlpopularmoviesapp.service.MovieService;
import com.example.merna.ntlpopularmoviesapp.view.main.IMainView;
import com.example.merna.ntlpopularmoviesapp.view.main.MainFragment;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements IMainPresenter {

    private MovieService movieService;
    private IMainView mainView;

    public MainPresenter(MainFragment mainFragment) {
        this.mainView = mainFragment;
        this.movieService = new MovieService();
    }

    @Override
    public void getPopularMovies() {
        mainView.showLoading();
        movieService.getAPI().getPopularMovies().enqueue(new Callback<MoviesModel>() {
            @Override
            public void onResponse(Call<MoviesModel> call, Response<MoviesModel> response) {
                MoviesModel data = response.body();
                if (data != null && data.getResult() != null) {
                    mainView.updateView(data.getResult());
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

    @Override
    public void getTopRatedMovies() {
        mainView.showLoading();
        movieService.getAPI().getTopRatedMovies().enqueue(new Callback<MoviesModel>() {
            @Override
            public void onResponse(Call<MoviesModel> call, Response<MoviesModel> response) {
                MoviesModel data = response.body();
                if (data != null && data.getResult() != null) {
                    mainView.updateView(data.getResult());
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

    @Override
    public LiveData<List<Movie>> getFavoritesMovies() {
        return MovieDB.getInstance(mainView.getViewContext()).movieDao().getAllMovies();
    }
}

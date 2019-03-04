package com.example.merna.ntlpopularmoviesapp.presenter.details;

import android.util.Log;

import com.example.merna.ntlpopularmoviesapp.database.AppExecutors;
import com.example.merna.ntlpopularmoviesapp.database.MovieDB;
import com.example.merna.ntlpopularmoviesapp.model.Movie;
import com.example.merna.ntlpopularmoviesapp.model.ReviewModel;
import com.example.merna.ntlpopularmoviesapp.model.TrailerModel;
import com.example.merna.ntlpopularmoviesapp.service.MovieService;
import com.example.merna.ntlpopularmoviesapp.view.details.IDetailsView;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsPresenter implements IDetailsPresenter {

    private MovieService movieService;
    private IDetailsView detailsView;

    public DetailsPresenter(IDetailsView detailsView) {
        this.movieService = new MovieService();
        this.detailsView = detailsView;
    }

    @Override
    public void getReviewsById(String id) {
        movieService.getAPI().getReviewsByMovieId(id).enqueue(new Callback<ReviewModel>() {
            @Override
            public void onResponse(Call<ReviewModel> call, Response<ReviewModel> response) {
                ReviewModel data = response.body();
                if (data != null && data.getResult() != null) {
                    Log.d("reviews**", data.getResult().toString());
                    detailsView.setReviewRecyclerView(data.getResult());
                }
            }

            @Override
            public void onFailure(Call<ReviewModel> call, Throwable t) {
                Log.d("reviews**", "fail");
            }
        });
    }

    @Override
    public void getTrailersById(String id) {
        movieService.getAPI().getTrailersByMovieId(id).enqueue(new Callback<TrailerModel>() {
            @Override
            public void onResponse(Call<TrailerModel> call, Response<TrailerModel> response) {
                TrailerModel data = response.body();
                if (data != null && data.getResult() != null) {
                    Log.d("Trailers**", data.getResult().toString());
                    detailsView.setTrailerRecyclerView(data.getResult());
                }
            }

            @Override
            public void onFailure(Call<TrailerModel> call, Throwable t) {

            }
        });
    }

    @Override
    public void deleteFavoriteMovie(final Movie movie) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                MovieDB.getInstance(detailsView.getViewContext()).movieDao().deleteMovie(movie);
            }
        });
    }

    @Override
    public void insertFavoriteMovie(final Movie movie) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                MovieDB.getInstance(detailsView.getViewContext()).movieDao().insertMovie(movie);
            }
        });
    }

    @Override
    public void setupFavBtn(String id) {
        LiveData<Movie> movie = MovieDB.getInstance(detailsView.getViewContext()).movieDao().
                getMovieById(id);
        movie.observe((LifecycleOwner) detailsView, new Observer<Movie>() {
            @Override
            public void onChanged(Movie movie) {
                if (movie != null) {
                    detailsView.setFavButton();
                    detailsView.setFavorite(true);
                }
            }
        });
    }
}

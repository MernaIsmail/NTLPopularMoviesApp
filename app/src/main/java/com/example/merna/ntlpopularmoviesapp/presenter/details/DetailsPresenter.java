package com.example.merna.ntlpopularmoviesapp.presenter.details;

import android.util.Log;

import com.example.merna.ntlpopularmoviesapp.model.ReviewModel;
import com.example.merna.ntlpopularmoviesapp.model.TrailerModel;
import com.example.merna.ntlpopularmoviesapp.service.MovieService;
import com.example.merna.ntlpopularmoviesapp.view.details.IDetailsView;

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
}

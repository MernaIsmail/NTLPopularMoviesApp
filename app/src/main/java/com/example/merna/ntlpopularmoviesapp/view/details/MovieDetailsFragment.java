package com.example.merna.ntlpopularmoviesapp.view.details;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.merna.ntlpopularmoviesapp.R;
import com.example.merna.ntlpopularmoviesapp.model.Movie;
import com.example.merna.ntlpopularmoviesapp.model.Reviews;
import com.example.merna.ntlpopularmoviesapp.model.Trailer;
import com.example.merna.ntlpopularmoviesapp.presenter.details.DetailsPresenter;
import com.example.merna.ntlpopularmoviesapp.presenter.details.IDetailsPresenter;
import com.example.merna.ntlpopularmoviesapp.presenter.details.ReviewAdapter;
import com.example.merna.ntlpopularmoviesapp.presenter.details.TrailerAdapter;
import com.example.merna.ntlpopularmoviesapp.utils.ImageUploaderUtil;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MovieDetailsFragment extends Fragment implements IDetailsView {

    private static final String ARG_MOVIE = "ARG_MOVIE";

    private IDetailsPresenter presenter;

    private Movie movie;
    private ImageView movieImageView;
    private TextView titleTextView;
    private TextView overviewTextView;
    private TextView voteAverageTextView;
    private ImageButton favBtn;
    private TextView releaseDateTextView;
    private RecyclerView trailerRecyclerView;
    private RecyclerView reviewsRecyclerView;
    private boolean isFavorite;

    public MovieDetailsFragment() {
        presenter = new DetailsPresenter(this);
    }

    public static MovieDetailsFragment newInstance(Movie movie) {
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_MOVIE, movie);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            movie = getArguments().getParcelable(ARG_MOVIE);
            Log.d("data**", movie.getOriginalTitle());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_details, container, false);
        initUI(rootView);
        setContent();
        presenter.setupFavBtn(movie.getId());
        presenter.getReviewsById(movie.getId());
        presenter.getTrailersById(movie.getId());
        favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFavorite) {
                    favBtn.setBackgroundResource(R.drawable.unfav);
                    presenter.deleteFavoriteMovie(movie);
                    isFavorite = false;
                } else {
                    favBtn.setBackgroundResource(R.drawable.fav);
                    presenter.insertFavoriteMovie(movie);
                    isFavorite = true;
                }
            }
        });
        return rootView;
    }

    private void setContent() {
        ImageUploaderUtil.setImage(movie.getPosterPath(), movieImageView);
        titleTextView.setText(movie.getOriginalTitle());
        releaseDateTextView.setText(movie.getReleaseDate());
        overviewTextView.setText(movie.getOverview());
        voteAverageTextView.setText(String.valueOf(movie.getVoteAverage()));
    }

    void initUI(View rootView) {
        movieImageView = rootView.findViewById(R.id.movieDetails_imageView);
        titleTextView = rootView.findViewById(R.id.movieDetails_title);
        overviewTextView = rootView.findViewById(R.id.movieDetails_overview);
        voteAverageTextView = rootView.findViewById(R.id.movieDetails_voteAverage);
        favBtn = rootView.findViewById(R.id.movieDetails_favBtn);
        releaseDateTextView = rootView.findViewById(R.id.movieDetails_releaseDate);
        reviewsRecyclerView = rootView.findViewById(R.id.movieDetails_reviews_recyclerView);
        trailerRecyclerView = rootView.findViewById(R.id.movieDetails_trailer_recyclerView);
    }

    public void setTrailerRecyclerView(List<Trailer> trailers) {
        if (trailers != null && trailers.size() > 0) {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
                    LinearLayoutManager.HORIZONTAL,
                    false);
            trailerRecyclerView.setLayoutManager(layoutManager);
            trailerRecyclerView.setHasFixedSize(true);
            trailerRecyclerView.setAdapter(new TrailerAdapter(trailers));
            trailerRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    public void setReviewRecyclerView(List<Reviews> reviews) {
        if (reviews != null && reviews.size() > 0) {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
                    RecyclerView.VERTICAL,
                    false);
            reviewsRecyclerView.setLayoutManager(layoutManager);
            reviewsRecyclerView.setHasFixedSize(true);
            reviewsRecyclerView.setAdapter(new ReviewAdapter(reviews));
            reviewsRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public Context getViewContext() {
        return getContext().getApplicationContext();
    }

    @Override
    public void setFavButton() {
        favBtn.setBackgroundResource(R.drawable.fav);
    }

    @Override
    public void setFavorite(boolean b) {
        isFavorite = b;
    }
}

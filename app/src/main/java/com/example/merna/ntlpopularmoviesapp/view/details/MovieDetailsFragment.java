package com.example.merna.ntlpopularmoviesapp.view.details;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.merna.ntlpopularmoviesapp.R;
import com.example.merna.ntlpopularmoviesapp.model.Movie;
import com.example.merna.ntlpopularmoviesapp.utils.ImageUploaderUtil;

public class MovieDetailsFragment extends Fragment {

    private static final String ARG_MOVIE = "ARG_MOVIE";

    private Movie movie;
    private ImageView movieImageView;
    private TextView titleTextView;
    private TextView overviewTextView;
    private TextView voteAverageTextView;
    private TextView releaseDateTextView;

    public MovieDetailsFragment() {
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
        releaseDateTextView = rootView.findViewById(R.id.movieDetails_releaseDate);
    }
}

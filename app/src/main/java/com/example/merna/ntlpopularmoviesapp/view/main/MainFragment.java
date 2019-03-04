package com.example.merna.ntlpopularmoviesapp.view.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.merna.ntlpopularmoviesapp.R;
import com.example.merna.ntlpopularmoviesapp.model.Movie;
import com.example.merna.ntlpopularmoviesapp.model.MoviesModel;
import com.example.merna.ntlpopularmoviesapp.presenter.main.IMainPresenter;
import com.example.merna.ntlpopularmoviesapp.presenter.main.MainPresenter;
import com.example.merna.ntlpopularmoviesapp.view.details.MovieDetailsFragment;

import java.util.List;

public class MainFragment extends Fragment implements IMainView, MovieAdapter.ItemClickListener {

    private RecyclerView moviesRecyclerView;
    private ProgressBar progressBar;
    private IMainPresenter mainPresenter;
    MoviesModel moviesModel;

    public MainFragment() {
        mainPresenter = new MainPresenter(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort_by_top_rated:
                mainPresenter.getTopRatedMovies();
                item.setChecked(true);
                return true;
            case R.id.sort_by_most_popular:
                mainPresenter.getPopularMovies();
                item.setChecked(true);
                return true;
            case R.id.fav_movies:
                mainPresenter.getFavoritesMovies();
                item.setChecked(true);
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        moviesRecyclerView = rootView.findViewById(R.id.movies_recyclerView);
        progressBar = rootView.findViewById(R.id.main_progress_bar);
        mainPresenter.getPopularMovies();
        return rootView;
    }

    @Override
    public void updateView(MoviesModel moviesModel) {
        this.moviesModel = moviesModel;
        setMoviesRecyclerView(moviesModel.getResult());
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        moviesRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    private void setMoviesRecyclerView(List<Movie> movies) {
        moviesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        moviesRecyclerView.setAdapter(new MovieAdapter(getContext(), movies, this));
        moviesRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemClick(View view, int position) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().
                beginTransaction();
        fragmentTransaction.replace(R.id.frameContainer,
                MovieDetailsFragment.newInstance(moviesModel.getResult().get(position))).
                addToBackStack(null).commit();
    }
}

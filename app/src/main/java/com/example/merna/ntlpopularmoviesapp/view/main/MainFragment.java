package com.example.merna.ntlpopularmoviesapp.view.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.merna.ntlpopularmoviesapp.R;
import com.example.merna.ntlpopularmoviesapp.database.MovieViewModel;
import com.example.merna.ntlpopularmoviesapp.model.Movie;
import com.example.merna.ntlpopularmoviesapp.presenter.main.IMainPresenter;
import com.example.merna.ntlpopularmoviesapp.presenter.main.MainPresenter;
import com.example.merna.ntlpopularmoviesapp.view.details.MovieDetailsFragment;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainFragment extends Fragment implements IMainView, MovieAdapter.ItemClickListener {

    private RecyclerView moviesRecyclerView;
    private ProgressBar progressBar;
    private IMainPresenter mainPresenter;
    List<Movie> moviesModel;
    MovieViewModel viewModel;

    public MainFragment() {
        mainPresenter = new MainPresenter(this);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if ((moviesRecyclerView.getLayoutManager()) != null) {
            int index = ((GridLayoutManager) moviesRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();
            if (index != -1) viewModel.setAdapterPosition(index);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
        if (viewModel.getCurrentSelectedSort() != -1) {
            MenuItem selected = menu.findItem(viewModel.getCurrentSelectedSort());
            selected.setChecked(true);
            switch (selected.getItemId()) {
                case R.id.sort_by_top_rated:
                case R.id.sort_by_most_popular:
                    updateView(viewModel.getMovies());
                    break;
                case R.id.fav_movies:
                    setupFavoritesMovies();
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort_by_top_rated:
                viewModel.setCurrentSelectedSort(item.getItemId());
                mainPresenter.getTopRatedMovies();
                item.setChecked(true);
                return true;
            case R.id.sort_by_most_popular:
                viewModel.setCurrentSelectedSort(item.getItemId());
                mainPresenter.getPopularMovies();
                item.setChecked(true);
                return true;
            case R.id.fav_movies:
                viewModel.setCurrentSelectedSort(item.getItemId());
                setupFavoritesMovies();
                item.setChecked(true);
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupFavoritesMovies() {
        viewModel.getFavMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                if (viewModel.getCurrentSelectedSort() == R.id.fav_movies) {
                    updateView(movies);
                }
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        viewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        moviesRecyclerView = rootView.findViewById(R.id.movies_recyclerView);
        progressBar = rootView.findViewById(R.id.main_progress_bar);
        if (viewModel.getCurrentSelectedSort() == -1)
            mainPresenter.getPopularMovies();
        return rootView;
    }

    @Override
    public void updateView(List<Movie> moviesModel) {
        viewModel.setMovies(moviesModel);
        this.moviesModel = moviesModel;
        setMoviesRecyclerView(moviesModel);
        reArrangeRecycleView();
        hideLoading();

    }

    private void reArrangeRecycleView() {
        if (moviesRecyclerView != null && viewModel.getAdapterPosition() != -1) {
            moviesRecyclerView.scrollToPosition(viewModel.getAdapterPosition());
            viewModel.setAdapterPosition(-1);
        }
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

    @Override
    public Context getViewContext() {
        return getContext().getApplicationContext();
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
                MovieDetailsFragment.newInstance(moviesModel.get(position))).
                addToBackStack(null).commit();
    }
}

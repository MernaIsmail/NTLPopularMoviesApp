package com.example.merna.ntlpopularmoviesapp.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.merna.ntlpopularmoviesapp.R;
import com.example.merna.ntlpopularmoviesapp.model.Movie;
import com.example.merna.ntlpopularmoviesapp.model.MoviesModel;
import com.example.merna.ntlpopularmoviesapp.presenter.IMainPresenter;
import com.example.merna.ntlpopularmoviesapp.presenter.MainPresenter;

import java.util.List;

public class MainFragment extends Fragment implements IMainView {

    private RecyclerView moviesRecyclerView;
    private IMainPresenter mainPresenter;

    public MainFragment() {
        mainPresenter = new MainPresenter(this);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        moviesRecyclerView = rootView.findViewById(R.id.movies_recyclerView);
        mainPresenter.getPopularMoves();

        return rootView;
    }

    @Override
    public void updateView(MoviesModel moviesModel) {
        setMoviesRecyclerView(moviesModel.getResult());
    }

    private void setMoviesRecyclerView(List<Movie> movies) {
        moviesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        moviesRecyclerView.setAdapter(new MovieAdapter(getContext(), movies));
    }
}

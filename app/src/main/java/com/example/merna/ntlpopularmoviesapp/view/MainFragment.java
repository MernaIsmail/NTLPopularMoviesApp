package com.example.merna.ntlpopularmoviesapp.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.merna.ntlpopularmoviesapp.R;
import com.example.merna.ntlpopularmoviesapp.model.MoviesModel;
import com.example.merna.ntlpopularmoviesapp.presenter.IMainPresenter;
import com.example.merna.ntlpopularmoviesapp.presenter.MainPresenter;

public class MainFragment extends Fragment implements IMainView {

    TextView text;
    IMainPresenter mainPresenter;

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
        text = rootView.findViewById(R.id.text);
        mainPresenter.getPopularMoves();

        return rootView;
    }

    @Override
    public void updateView(MoviesModel moviesModel) {
        text.setText(moviesModel.getResult().get(0).getOriginalTitle());
    }
}

package com.example.merna.ntlpopularmoviesapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesModel {

    @SerializedName("results")
    private List<Movie> result;

    public List<Movie> getResult() {
        return result;
    }
}

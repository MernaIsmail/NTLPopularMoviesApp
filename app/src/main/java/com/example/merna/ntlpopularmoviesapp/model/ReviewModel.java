package com.example.merna.ntlpopularmoviesapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewModel {

    @SerializedName("results")
    private List<Reviews> result;

    public List<Reviews> getResult() {
        return result;
    }
}

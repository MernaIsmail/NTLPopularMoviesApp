package com.example.merna.ntlpopularmoviesapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrailerModel {

    @SerializedName("results")
    private List<Trailer> result;

    public List<Trailer> getResult() {
        return result;
    }
}

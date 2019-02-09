package com.example.merna.ntlpopularmoviesapp.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieService {
    private Retrofit retrofit = null;


    public MovieAPIInterface getAPI() {
        String BASE_URL = "https://api.themoviedb.org/3/";

        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(MovieAPIInterface.class);
    }
}

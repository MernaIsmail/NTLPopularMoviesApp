package com.example.merna.ntlpopularmoviesapp.utils;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageUploaderUtil {

    private static final String BASE_URL = "http://image.tmdb.org/t/p";
    private static final String YOUTUBE_URL = "http://img.youtube.com/vi/%s/mqdefault.jpg";


    public static void setImage(String url, ImageView imageView) {
        Picasso.get().load(String.format("%s/w342/%s", BASE_URL, url)).into(imageView);
    }

    public static void setImageFromYoutube(String videoKey, ImageView imageView) {
        Picasso.get().load(String.format(YOUTUBE_URL, videoKey)).into(imageView);
    }
}

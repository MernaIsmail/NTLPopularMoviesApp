package com.example.merna.ntlpopularmoviesapp.view.main;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.merna.ntlpopularmoviesapp.R;
import com.example.merna.ntlpopularmoviesapp.model.Movie;
import com.example.merna.ntlpopularmoviesapp.utils.ImageUploaderUtil;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context context;
    private List<Movie> moviesList;
    private ItemClickListener itemClickListener;

    public MovieAdapter(Context context, List<Movie> moviesList, ItemClickListener itemClickListener) {
        this.context = context;
        this.moviesList = moviesList;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.cell_movie, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
        ImageUploaderUtil.setImage(moviesList.get(i).getPosterPath(), movieViewHolder.movieImage);
    }


    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    protected class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView movieImage;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.movie_imageView);
            movieImage.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (itemClickListener != null) itemClickListener.onItemClick(v, getAdapterPosition());

        }
    }
}

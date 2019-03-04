package com.example.merna.ntlpopularmoviesapp.presenter.details;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.merna.ntlpopularmoviesapp.R;
import com.example.merna.ntlpopularmoviesapp.model.Reviews;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private final List<Reviews> reviews;

    public ReviewAdapter(List<Reviews> reviews) {
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        return new ReviewViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.cell_review, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Reviews review = reviews.get(position);
        holder.author.setText(review.getReviewer());
        holder.review.setText(review.getReviewContent());
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    class ReviewViewHolder extends RecyclerView.ViewHolder {

        TextView author;
        TextView review;

        ReviewViewHolder(View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.review_author);
            review = itemView.findViewById(R.id.review_content);
        }
    }
}
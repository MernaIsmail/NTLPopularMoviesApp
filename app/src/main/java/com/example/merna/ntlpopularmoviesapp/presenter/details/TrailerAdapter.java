package com.example.merna.ntlpopularmoviesapp.presenter.details;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.merna.ntlpopularmoviesapp.R;
import com.example.merna.ntlpopularmoviesapp.model.Trailer;
import com.example.merna.ntlpopularmoviesapp.utils.ImageUploaderUtil;

import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder> {

    private Context context;
    private final List<Trailer> trailers;

    public TrailerAdapter(List<Trailer> trailers) {
        this.trailers = trailers;
    }

    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        return new TrailerViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.cell_trailer, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder holder, int position) {
        final Trailer trailer = trailers.get(position);

        ImageUploaderUtil.setImageFromYoutube(trailer.getVideoKey(), holder.trailerImage);
        holder.trailerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("vnd.youtube://" + trailer.getVideoKey())));
            }
        });
    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    class TrailerViewHolder extends RecyclerView.ViewHolder {

        ImageView trailerImage;
        RelativeLayout trailerLayout;

        TrailerViewHolder(View itemView) {
            super(itemView);
            trailerImage = itemView.findViewById(R.id.trailer_imageView);
            trailerLayout = itemView.findViewById(R.id.trailer_layout);
        }
    }
}
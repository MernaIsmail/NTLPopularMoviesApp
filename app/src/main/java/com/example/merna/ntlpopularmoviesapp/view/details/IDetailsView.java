package com.example.merna.ntlpopularmoviesapp.view.details;

import com.example.merna.ntlpopularmoviesapp.model.Reviews;
import com.example.merna.ntlpopularmoviesapp.model.Trailer;

import java.util.List;

public interface IDetailsView {
    void setTrailerRecyclerView(List<Trailer> trailers);

    void setReviewRecyclerView(List<Reviews> reviews);
}

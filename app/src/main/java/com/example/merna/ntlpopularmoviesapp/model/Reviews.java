package com.example.merna.ntlpopularmoviesapp.model;

import com.google.gson.annotations.SerializedName;

public class Reviews {

    @SerializedName("author")
    private String reviewer;

    @SerializedName("content")
    private String reviewContent;

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }
}

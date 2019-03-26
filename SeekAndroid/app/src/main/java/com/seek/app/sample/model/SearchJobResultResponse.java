package com.seek.app.sample.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchJobResultResponse {

    @SerializedName("title")
    public String title;
    @SerializedName("data")
    public List<Job> jobs;

    public static class Job {
        @SerializedName("id")
        public String jobId;
        @SerializedName("listingDate")
        public String listingDate;
        @SerializedName("advertiser")
        public Advertiser advertiser;
    }

    private static class Advertiser {
        @SerializedName("id")
        public String advertiserId;
        @SerializedName("description")
        public String description;
    }
}

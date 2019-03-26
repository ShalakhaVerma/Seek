package com.seek.app.sample.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;


@AutoValue
public abstract class JobItem implements Parcelable {

    public abstract String searchId();

    public abstract String listingDate();

    public abstract String title();

    public abstract String teaser();

    public abstract JobAdvertiser advertiser();

    public static JobItem create(String id, String listingDate, String title, String teaser, SearchJobResultResponse.Advertiser advertiser) {

           JobAdvertiser jobAdvertiser = JobAdvertiser.create(advertiser.advertiserId, advertiser.description);

        return new AutoValue_JobItem(id, listingDate, title, teaser, jobAdvertiser);
    }
}

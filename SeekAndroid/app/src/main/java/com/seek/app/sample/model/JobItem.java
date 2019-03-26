package com.seek.app.sample.model;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class JobItem {

    public abstract String searchId();

    public abstract String listingDate();

    public static JobItem create(String id, String listingDate) {
        return new AutoValue_JobItem(id, listingDate);
    }
}

package com.seek.app.sample.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class JobAdvertiser implements Parcelable {

    public abstract String id();

    public abstract String description();

    public static JobAdvertiser create(String id, String description) {
        return new AutoValue_JobAdvertiser(id, description);
    }
}

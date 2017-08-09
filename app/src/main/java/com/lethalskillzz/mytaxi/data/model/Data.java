package com.lethalskillzz.mytaxi.data.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.ryanharter.auto.value.parcel.ParcelAdapter;

import java.util.List;

/**
 * Created by ibrahimabdulkadir on 05/08/2017.
 */

@AutoValue
public abstract class Data implements Parcelable {

    @ParcelAdapter(Placemark.ListTypeAdapter.class)
    public abstract List<Placemark> placemarks();

    public static Builder builder() {
        return new AutoValue_Data.Builder();
    }

    public static TypeAdapter<Data> typeAdapter(Gson gson) {
        return new AutoValue_Data.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder placemarks(List<Placemark> placemarks);

        public abstract Data build();
    }
}

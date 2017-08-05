package com.lethalskillzz.mytaxi.data.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;

/**
 * Created by ibrahimabdulkadir on 05/08/2017.
 */

@AutoValue
public abstract class Response {

    public abstract List<Placemark> placemarks();

    public static Builder builder() {
        return new AutoValue_Group.Builder();
    }

    public static TypeAdapter<Response> typeAdapter(Gson gson) {
        return new AutoValue_Group.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder placemarks(List<Placemark> placemarks);

        public abstract Response build();
    }
}

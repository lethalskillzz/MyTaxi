package com.lethalskillzz.mytaxi.data.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by ibrahimabdulkadir on 05/08/2017.
 */

@AutoValue
public abstract class Placemark {

    public abstract Integer id();
    public abstract String name();
    public abstract Double lat();
    public abstract Double lon();
    public abstract Boolean repinned();
    public abstract String address_1();
    public abstract String city();
    public abstract String country();
    public abstract String localized_country_name();


    public static Builder builder() {
        return new AutoValue_Venue.Builder();
    }

    public static TypeAdapter<Placemark> typeAdapter(Gson gson) {
        return new AutoValue_Venue.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(Integer id);
        public abstract Builder name(String name);
        public abstract Builder lat(Double lat);
        public abstract Builder lon(Double lon);
        public abstract Builder repinned(Boolean repinned);
        public abstract Builder address_1(String address_1);
        public abstract Builder city(String city);
        public abstract Builder country(String country);
        public abstract Builder localized_country_name(String localized_country_name);

        public abstract Placemark build();
    }


}

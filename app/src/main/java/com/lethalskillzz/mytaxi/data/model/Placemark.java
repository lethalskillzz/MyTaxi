package com.lethalskillzz.mytaxi.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;

/**
 * Created by ibrahimabdulkadir on 05/08/2017.
 */

@AutoValue
public abstract class Placemark implements Parcelable {

    public abstract String address();
    public abstract List<String> coordinates();
    public abstract String engineType();
    public abstract String exterior();
    public abstract Integer fuel();
    public abstract String interior();
    public abstract String name();
    public abstract String vin();


    public static Builder builder() {
        return new AutoValue_Placemark.Builder();
    }

    public static TypeAdapter<Placemark> typeAdapter(Gson gson) {
        return new AutoValue_Placemark.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder address(String address);
        public abstract Builder coordinates(List<String> coordinates);
        public abstract Builder engineType(String engineType);
        public abstract Builder exterior(String exterior);
        public abstract Builder fuel(Integer fuel);
        public abstract Builder interior(String interior);
        public abstract Builder name(String name);
        public abstract Builder vin(String vin);

        public abstract Placemark build();
    }


    public static final class ListTypeAdapter implements com.ryanharter.auto.value.parcel.TypeAdapter<List<Placemark>> {
        @Override
        public List<Placemark> fromParcel(Parcel in) {
            return in.createTypedArrayList(new Creator<Placemark>() {
                @Override
                public Placemark createFromParcel(Parcel source) {
                    return AutoValue_Placemark.CREATOR.createFromParcel(source);
                }

                @Override
                public Placemark[] newArray(int size) {
                    return new Placemark[size];
                }
            });
        }

        @Override
        public void toParcel(List<Placemark> value, Parcel dest) {
            dest.writeTypedList(value);
        }
    }
}

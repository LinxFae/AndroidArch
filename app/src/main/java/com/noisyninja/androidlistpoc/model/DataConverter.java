package com.noisyninja.androidlistpoc.model;

import android.arch.persistence.room.TypeConverter;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.noisyninja.androidlistpoc.R;
import com.squareup.picasso.Picasso;

/**
 * data binding converters
 * Created by sudiptadutta on 12/05/18.
 */

public class DataConverter {

    @BindingAdapter("thumbnail")
    public static void loadImage(final ImageView view, final String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round)
                .fit()
                .into(view);

    }

    @TypeConverter
    public String fromName(Name name) {
        if (name == null) {
            return (null);
        }
        return name.toString();
    }

    @TypeConverter
    public Name toName(String nameString) {
        if (nameString == null) {
            return (null);
        }

        Gson gson = new Gson();
        return gson.fromJson(nameString, Name.class);
    }

    @TypeConverter
    public String fromPicture(Picture picture) {
        if (picture == null) {
            return (null);
        }
        return picture.toString();
    }

    @TypeConverter
    public Picture toPicture(String pictureString) {
        if (pictureString == null) {
            return (null);
        }
        Gson gson = new Gson();
        return gson.fromJson(pictureString, Picture.class);
    }

    @TypeConverter
    public String fromId(Id id) {
        if (id == null) {
            return (null);
        }
        return id.toString();
    }

    @TypeConverter
    public Id toId(String idString) {
        if (idString == null) {
            return (null);
        }
        Gson gson = new Gson();
        return gson.fromJson(idString, Id.class);
    }

    @TypeConverter
    public String fromLocation(Location location) {
        if (location == null) {
            return (null);
        }
        return location.toString();
    }

    @TypeConverter
    public Location toLocation(String locationString) {
        if (locationString == null) {
            return (null);
        }
        Gson gson = new Gson();
        return gson.fromJson(locationString, Location.class);
    }
}

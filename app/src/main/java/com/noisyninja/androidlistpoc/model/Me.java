package com.noisyninja.androidlistpoc.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;


@Entity(tableName = "me")
public class Me extends BaseDTO {

    @PrimaryKey()
    @NonNull
    @TypeConverters(DataConverter.class)
    private Name name;

    @NonNull
    @TypeConverters(DataConverter.class)
    private Picture picture;

    private String email;
    private String dob;
    private String phone;
    private String cell;
    private String gender;

    public Me(@NonNull Name name) {
        this.name = name;
    }

    @NonNull
    public Name getName() {
        return name;
    }

    public void setName(@NonNull Name name) {
        this.name = name;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

}

package com.noisyninja.androidlistpoc.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.noisyninja.androidlistpoc.layers.Utils;

import okhttp3.internal.Util;


@Entity(tableName = "me")
public class Me extends BaseDTO {

    public static final String MALE = "male";
    @PrimaryKey()
    @NonNull
    @TypeConverters(DataConverter.class)
    private Name name;

    @TypeConverters(DataConverter.class)
    private Picture picture;

    @TypeConverters(DataConverter.class)
    private Id id;

    @TypeConverters(DataConverter.class)
    private Location location;

    private String email;
    private String dob;
    private String phone;
    private String cell;
    private String gender;
    private String nat;

    public Me(@NonNull Name name) {
        this.name = name;
    }

    public boolean isMale() {
        return gender.equals(MALE);
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

    public void setPicture(@NonNull Picture picture) {
        this.picture = picture;
    }

    public Id getId() {
        return id;
    }

    public void setId(@NonNull Id id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNat() {
        return nat;
    }

    public void setNat(String nat) {
        this.nat = nat;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}

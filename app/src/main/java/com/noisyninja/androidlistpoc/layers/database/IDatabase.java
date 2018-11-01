package com.noisyninja.androidlistpoc.layers.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.noisyninja.androidlistpoc.model.Me;

/**
 * dao abstract class
 * Created by sudiptadutta on 30/04/18.
 */

@Database(entities = {Me.class,}, version = 1)
public abstract class IDatabase extends RoomDatabase {
    public abstract DatabaseDao databaseDao();
}

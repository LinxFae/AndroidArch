package com.noisyninja.androidlistpoc.layers.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.noisyninja.androidlistpoc.model.Me;

import java.util.List;

/**
 * Database data access object
 * Created by sudiptadutta on 30/04/18.
 */

@Dao
public interface DatabaseDao {

    @Query("SELECT * FROM me")
    LiveData<List<Me>> getAll();


    @Query("SELECT * FROM me where name LIKE  :name")
    LiveData<Me> findById(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Me customer);

    @Delete
    void delete(Me customer);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Me> customers);

}
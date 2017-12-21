package com.carapp.gobi.carapp.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.carapp.gobi.carapp.domain.Insurance;

import java.util.List;

/**
 * Created by gobi on 11/25/2017.
 */

@Dao
public interface InsuranceDao {

    @Query("SELECT * FROM insurance")
    List<Insurance> getAll();

    @Insert
    void insertAll(Insurance... insurances);

    @Delete
    void delete(Insurance insurance);
}

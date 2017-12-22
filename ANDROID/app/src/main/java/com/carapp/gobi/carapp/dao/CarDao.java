package com.carapp.gobi.carapp.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.carapp.gobi.carapp.domain.Car;

import java.util.List;

/**
 * Created by gobi on 11/25/2017.
 */

@Dao
public interface CarDao {

    @Query("SELECT * FROM car")
    List<Car> getAll();

    @Insert
    void insertAll(Car... cars);

    @Delete
    void delete(Car car);

    @Update
    void update(Car... cars);

    @Query("SELECT * FROM car WHERE chassisCode = :chassisCode")
    Car findByChassisCode(String chassisCode);
}

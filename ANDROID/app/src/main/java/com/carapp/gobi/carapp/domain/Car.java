package com.carapp.gobi.carapp.domain;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gobi on 11/9/2017.
 */
@Entity
public class Car implements Serializable{

    @PrimaryKey
    @NonNull
    private String chassisCode;

    @ColumnInfo(name = "model")
    private String model;

    @ColumnInfo(name = "make")
    private String make;

    @ColumnInfo(name = "cubic_capacity")
    private int cubicCapacity;

    @ColumnInfo(name = "model_year")
    private int modelYear;

    @Ignore
    private volatile boolean isNew;

    @Ignore
     public Car(@NonNull String chassisCode, String model, String make, int cubicCapacity, int modelYear) {
        this.chassisCode = chassisCode;
        this.model = model;
        this.make = make;
        this.cubicCapacity = cubicCapacity;
        this.modelYear = modelYear;
        this.isNew = false;
    }

    public Car() {
    }

    public String getChassisCode() {
        return chassisCode;
    }

    public void setChassisCode(String chassisCode) {
        this.chassisCode = chassisCode;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public int getCubicCapacity() {
        return cubicCapacity;
    }

    public void setCubicCapacity(int cubicCapacity) {
        this.cubicCapacity = cubicCapacity;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }


    @Override
    public String toString() {
        return "Car{" +
                "chassisCode='" + chassisCode + '\'' +
                ", model='" + model + '\'' +
                ", make='" + make + '\'' +
                ", cubicCapacity=" + cubicCapacity +
                ", modelYear=" + modelYear +
                '}';
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}

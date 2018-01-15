package com.carapp.gobi.carapp.domain;

import java.io.Serializable;


/**
 * Created by gobi on 11/9/2017.
 */
public class Car implements Serializable {

    private String id;

    private String chassisCode;

    private String model;

    private String make;

    private int cubicCapacity;

    private int modelYear;

    private volatile boolean isNew;

    public Car(String id, String chassisCode, String model, String make, int cubicCapacity, int modelYear) {
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

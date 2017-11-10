package com.carapp.gobi.carapp.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gobi on 11/9/2017.
 */

public class Car implements Serializable{

    private String chassisCode;

    private String model;

    private String make;

    private int cubicCapacity;

    private int modelYear;

    private List<Insurance> insuranceList;

    public Car(String chassisCode, String model, String make, int cubicCapacity, int modelYear) {
        this.chassisCode = chassisCode;
        this.model = model;
        this.make = make;
        this.cubicCapacity = cubicCapacity;
        this.modelYear = modelYear;
        this.insuranceList = new ArrayList<>();
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

    public List<Insurance> getInsuranceList() {
        return insuranceList;
    }

    public void setInsuranceList(List<Insurance> insuranceList) {
        this.insuranceList = insuranceList;
    }
}

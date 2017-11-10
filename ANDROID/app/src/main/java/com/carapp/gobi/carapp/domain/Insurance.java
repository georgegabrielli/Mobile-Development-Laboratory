package com.carapp.gobi.carapp.domain;

import java.util.Date;

/**
 * Created by gobi on 11/9/2017.
 */

public class Insurance {

    private Car car;

    private Date startDate;

    private Date endDate;

    private String insuranceCompany;

    private float price;

    public Insurance(Car car, Date startDate, Date endDate, String insuranceCompany, float price) {
        this.car = car;
        this.startDate = startDate;
        this.endDate = endDate;
        this.insuranceCompany = insuranceCompany;
        this.price = price;
    }

    public Insurance() {
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}

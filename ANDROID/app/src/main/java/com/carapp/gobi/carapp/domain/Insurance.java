package com.carapp.gobi.carapp.domain;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

/**
 * Created by gobi on 11/9/2017.
 */

@Entity(foreignKeys = @ForeignKey(entity = Car.class,
        parentColumns = "chassisCode",
        childColumns = "car_chassis_code"))
public class Insurance {

    @PrimaryKey
    private int insuranceID;

    @ColumnInfo(name = "start_date")
    @TypeConverters(value = Converters.class)
    private Date startDate;

    @ColumnInfo(name = "end_date")
    @TypeConverters(value = Converters.class)
    private Date endDate;

    @ColumnInfo(name = "insurance_company")
    private String insuranceCompany;

    @ColumnInfo(name = "price")
    private float price;

    @ColumnInfo(name = "car_chassis_code")
    private String carChassisCode;

    public Insurance(int insuranceID, Date startDate, Date endDate, String insuranceCompany, float price) {
        this.insuranceID = insuranceID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.insuranceCompany = insuranceCompany;
        this.price = price;
    }

    public Insurance() {
    }

    public int getInsuranceID() {
        return insuranceID;
    }

    public void setCar(int car) {
        this.insuranceID = insuranceID;
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

    public String getCarChassisCode() {
        return carChassisCode;
    }

    public void setCarChassisCode(String carChassisCode) {
        this.carChassisCode = carChassisCode;
    }

    public void setInsuranceID(int insuranceID){
        this.insuranceID = insuranceID;
    }

    public static class Converters {

        @TypeConverter
        public Date fromTimestamp(Long value){
            return value == null ? null : new Date(value);
        }

        @TypeConverter
        public Long dateToTimestamp(Date date){
            return date == null ? null : date.getTime();
        }

    }
}

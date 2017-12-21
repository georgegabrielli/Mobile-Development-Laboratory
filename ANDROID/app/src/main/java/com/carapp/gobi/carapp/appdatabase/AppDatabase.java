package com.carapp.gobi.carapp.appdatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.carapp.gobi.carapp.dao.CarDao;
import com.carapp.gobi.carapp.dao.InsuranceDao;
import com.carapp.gobi.carapp.domain.Car;
import com.carapp.gobi.carapp.domain.Insurance;

/**
 * Created by gobi on 11/25/2017.
 */
@Database(entities = {Car.class, Insurance.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase database;

    public AppDatabase() {

    }

    public abstract CarDao carDao();

    public abstract InsuranceDao insuranceDao();


    public static AppDatabase getInstance(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "userCars").build();
        }

        return database;
    }
}

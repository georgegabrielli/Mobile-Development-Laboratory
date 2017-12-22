package com.carapp.gobi.carapp.appdatabase;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;

import com.carapp.gobi.carapp.dao.CarDao;
import com.carapp.gobi.carapp.dao.InsuranceDao;
import com.carapp.gobi.carapp.domain.Car;
import com.carapp.gobi.carapp.domain.Insurance;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;

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

    @SuppressLint("StaticFieldLeak")
    public static void addCar(final Car newCar, Context context) {
        getInstance(context);

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                database.carDao().insertAll(newCar);
                return null;
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public static void removeCar(final Car newCar, Context context) {
        getInstance(context);
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                database.carDao().delete(newCar);
                return null;
            }
        }.execute();


    }

    @SuppressLint("StaticFieldLeak")
    public static void updateCar(final Car newCar, Context context) {
        getInstance(context);
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                database.carDao().update(newCar);
                return null;
            }
        }.execute();

    }

    @SuppressLint("StaticFieldLeak")
    public static void getCar(final String chassisCode, Context context) {
        getInstance(context);
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                database.carDao().findByChassisCode(chassisCode);
                return null;
            }
        }.execute();


    }

    public static Collection<? extends Car> getAll(Context applicationContext) throws ExecutionException, InterruptedException {
        getInstance(applicationContext);
      return new AsyncTask<Void, Void, List<Car>>() {

            @Override
            protected List<Car> doInBackground(Void... voids) {
                return database.carDao().getAll();
            }
        }.execute().get();
    }
}

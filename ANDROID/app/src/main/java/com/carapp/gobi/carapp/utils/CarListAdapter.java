package com.carapp.gobi.carapp.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.carapp.gobi.carapp.R;
import com.carapp.gobi.carapp.domain.Car;

import java.util.List;

/**
 * Created by gobi on 11/9/2017.
 */

public class CarListAdapter extends BaseAdapter {
    List<Car> cars;

    LayoutInflater inflater;

    public CarListAdapter(List<Car> cars, LayoutInflater inflater) {
        this.cars = cars;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return cars.size();
    }

    @Override
    public Object getItem(int i) {
        return cars.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.car_item_layout, null);

        TextView descriptionView = (TextView) view.findViewById(R.id.carItem);

        descriptionView.setText(cars.get(i).getModelYear()+"" + " " + cars.get(i).getModel() + " " + cars.get(i).getMake());

        return view;
    }
}

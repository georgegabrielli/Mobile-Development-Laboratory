package com.carapp.gobi.carapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.carapp.gobi.carapp.appdatabase.AppDatabase;
import com.carapp.gobi.carapp.domain.Car;
import com.carapp.gobi.carapp.utils.CarListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListActivity extends AppCompatActivity {

    private static final int SECOND_ACTIVITY_RESULT_CODE = 0;

    List<Car> cars = new ArrayList<>();

    private CarListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        populateList();

        ListView listView = (ListView) findViewById(R.id.carList);

        adapter = new CarListAdapter(cars, getLayoutInflater());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(ListActivity.this, DetailsActivity.class);
                intent.putExtra("car", cars.get(position));
                startActivityForResult(intent, SECOND_ACTIVITY_RESULT_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check that it is the SecondActivity with an OK result
        if (requestCode == SECOND_ACTIVITY_RESULT_CODE) {
            if (resultCode == RESULT_OK) {

                // get String data from Intent
                Car p = (Car) data.getSerializableExtra("result");

                for(Car pr : cars){
                    if(Objects.equals(pr.getChassisCode(), p.getChassisCode())){
                       pr.setMake(p.getMake());
                       pr.setModel(p.getModel());
                       pr.setCubicCapacity(p.getCubicCapacity());
                       pr.setModelYear(p.getModelYear());
                    }
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    public void populateList(){
        AppDatabase database = AppDatabase.getInstance(getApplicationContext());
        cars.add(new Car("sdfghjhgfds1", "1er", "BEMWEU", 1993,
                2011));
        cars.add(new Car("sdfghjhgfds2", "1er", "BEMWEU", 1993,
                2011));
        cars.add(new Car("sdfghjhgfds3", "1er", "BEMWEU", 1993,
                2011));
        cars.add(new Car("sdfghjhgfds4", "1er", "BEMWEU", 1993,
                2011));
        cars.add(new Car("sdfghjhgfds5", "1er", "BEMWEU", 1993,
                2011));
        cars.add(new Car("sdfghjhgfds6", "1er", "BEMWEU", 1993,
                2011));
        cars.add(new Car("sdfghjhgfds7", "1er", "BEMWEU", 1993,
                2011));
        cars.add(new Car("sdfghjhgfds8", "1er", "BEMWEU", 1993,
                2011));
        cars.add(new Car("sdfghjhgfds9", "1er", "BEMWEU", 1993,
                2011));
        cars.add(new Car("sdfghjhgfds10", "1er", "BEMWEU", 1993,
                2011));
        cars.add(new Car("sdfghjhgfds11", "1er", "BEMWEU", 1993,
                2011));
        cars.add(new Car("sdfghjhgfds0", "1er", "BEMWEU", 1993,
                2011));

        database.carDao().insertAll(new Car("sdfghjhgfds1", "1er", "BEMWEU", 1993,
                2011), new Car("sdfghjhgfds2", "1er", "BEMWEU", 1993,
                2011));
    }
}

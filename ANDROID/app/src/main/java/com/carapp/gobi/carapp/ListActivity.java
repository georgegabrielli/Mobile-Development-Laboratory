package com.carapp.gobi.carapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.carapp.gobi.carapp.domain.Car;
import com.carapp.gobi.carapp.utils.CarListAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private static final int SECOND_ACTIVITY_RESULT_CODE = 0;

    List<Car> cars = new ArrayList<>();
    private ListView listView;
    private CarListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        populateList();

        listView = (ListView) findViewById(R.id.carList);

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
                    if(pr.getChassisCode()== p.getChassisCode()){
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
        cars.add(new Car("sdfghjhgfds", "1er", "BEMWEU", 1993, 2011));
        cars.add(new Car("sdfghjhgfds", "1er", "BEMWEU", 1993, 2011));
        cars.add(new Car("sdfghjhgfds", "1er", "BEMWEU", 1993, 2011));
        cars.add(new Car("sdfghjhgfds", "1er", "BEMWEU", 1993, 2011));
        cars.add(new Car("sdfghjhgfds", "1er", "BEMWEU", 1993, 2011));
        cars.add(new Car("sdfghjhgfds", "1er", "BEMWEU", 1993, 2011));
        cars.add(new Car("sdfghjhgfds", "1er", "BEMWEU", 1993, 2011));
        cars.add(new Car("sdfghjhgfds", "1er", "BEMWEU", 1993, 2011));
        cars.add(new Car("sdfghjhgfds", "1er", "BEMWEU", 1993, 2011));
        cars.add(new Car("sdfghjhgfds", "1er", "BEMWEU", 1993, 2011));
        cars.add(new Car("sdfghjhgfds", "1er", "BEMWEU", 1993, 2011));
        cars.add(new Car("sdfghjhgfds", "1er", "BEMWEU", 1993, 2011));
    }
}

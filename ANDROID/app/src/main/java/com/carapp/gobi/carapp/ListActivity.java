package com.carapp.gobi.carapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
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
import java.util.concurrent.ExecutionException;

public class ListActivity extends AppCompatActivity {

    private static final int SECOND_ACTIVITY_RESULT_CODE = 0;

    List<Car> cars = new ArrayList<>();

    private CarListAdapter adapter;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        listView = findViewById(R.id.carList);

        adapter = new CarListAdapter(cars, getLayoutInflater());
        listView.setAdapter(adapter);

        populateList();

        addListeners(listView);
    }

    private void addListeners(ListView listView) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(ListActivity.this, DetailsActivity.class);
                intent.putExtra("car", cars.get(position));
                startActivityForResult(intent, SECOND_ACTIVITY_RESULT_CODE);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

                                                @Override
                                                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                                                    AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);

                                                    builder.setMessage("??????").setTitle("YOU SURE YOU WANT DELETE?");

                                                    final Car car = cars.get(i);

                                                    builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            AppDatabase.removeCar(car, getApplicationContext());
                                                            populateList();
                                                            dialogInterface.dismiss();
                                                        }
                                                    });


                                                    builder.setNegativeButton("NONONO", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            dialogInterface.dismiss();
                                                        }
                                                    });

                                                    builder.create().show();

                                                    return true;
                                                }
                                            }
        );

        FloatingActionButton addButton = findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this, EditCar.class);
                Car car = new Car();
                car.setNew(true);
                intent.putExtra("car", car);
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
                populateList();
                // get String data from Intent
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    public void populateList() {
        try {
            cars.clear();
            cars.addAll(AppDatabase.getAll(getApplicationContext()));
            adapter.notifyDataSetChanged();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

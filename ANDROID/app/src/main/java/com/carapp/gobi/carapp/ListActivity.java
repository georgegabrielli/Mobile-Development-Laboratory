package com.carapp.gobi.carapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.carapp.gobi.carapp.domain.Car;
import com.carapp.gobi.carapp.utils.CarListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class ListActivity extends AppCompatActivity {

    private static final int SECOND_ACTIVITY_RESULT_CODE = 0;

    List<Car> cars = new ArrayList<>();

    private CarListAdapter adapter;

    private ListView listView;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    startActivity(new Intent(ListActivity.this, LoginActivity.class));
                }
            }
        };


        databaseReference = FirebaseDatabase.getInstance().getReference("cars");

        mAuth.addAuthStateListener(mAuthListener);

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
                if (mAuth.getCurrentUser().getEmail().equals("gabrielli.george@gmail.com")) {
                    Intent intent = new Intent(ListActivity.this, DetailsActivity.class);
                    intent.putExtra("car", cars.get(position));
                    startActivityForResult(intent, SECOND_ACTIVITY_RESULT_CODE);
                }
                else{
                    Toast.makeText(ListActivity.this, "Operation not allowed on this paygrade", Toast.LENGTH_LONG).show();
                }
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

                                                @Override
                                                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                                                    if(mAuth.getCurrentUser().getEmail().equals("gabrielli.george@gmail.com")){
                                                    AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);

                                                    builder.setMessage("??????").setTitle("YOU SURE YOU WANT DELETE?");

                                                    final Car car = cars.get(i);

                                                    builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            databaseReference.child(car.getId()).removeValue();
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
                                                else{
                                                        Toast.makeText(ListActivity.this, "Operation not allowed on this paygrade", Toast.LENGTH_LONG).show();
                                                        return true;
                                                    }
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

        FloatingActionButton logOutButton = findViewById(R.id.logOutButton);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
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
//        try {
        cars.clear();
//            cars.addAll(AppDatabase.getAll(getApplicationContext()));
        adapter.notifyDataSetChanged();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                cars.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Car car = postSnapshot.getValue(Car.class);
                    cars.add(car);
                }
                listView.setAdapter(adapter);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

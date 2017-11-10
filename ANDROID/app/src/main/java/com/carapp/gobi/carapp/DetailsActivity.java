package com.carapp.gobi.carapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.carapp.gobi.carapp.domain.Car;

public class DetailsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent i = getIntent();

        Car car = (Car)i.getSerializableExtra("car");
        TextView description = findViewById(R.id.carDescription);

        description.setText(car.getModelYear() + " " + car.getMake() + " " + car.getModel());


        ((Button)findViewById(R.id.editButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsActivity.this, EditCar.class);
                startActivityForResult(intent, 0);
            }
        }

    );

    }
}

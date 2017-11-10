package com.carapp.gobi.carapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.carapp.gobi.carapp.domain.Car;

public class DetailsActivity extends AppCompatActivity {


    private Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent i = getIntent();

        this.car = (Car)i.getSerializableExtra("car");
        TextView description = findViewById(R.id.carDescription);

        description.setText(car.getModelYear() + " " + car.getMake() + " " + car.getModel());


        ((Button)findViewById(R.id.editButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsActivity.this, EditCar.class);
                intent.putExtra("car", car);
                startActivityForResult(intent, 0);
            }
        }

    );

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check that it is the SecondActivity with an OK result
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {

                // get String data from Intent
                car = (Car) data.getSerializableExtra("result");

            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}

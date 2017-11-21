package com.carapp.gobi.carapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.carapp.gobi.carapp.domain.Car;

import org.w3c.dom.Text;

public class DetailsActivity extends AppCompatActivity {


    private Car car;
    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent i = getIntent();

        this.car = (Car)i.getSerializableExtra("car");
        this.description = findViewById(R.id.carDescription);

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
            if (resultCode == Activity.RESULT_OK) {

                // get String data from Intent
                car = (Car) data.getSerializableExtra("result");
                objectChanged();

                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", car);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        }
    }

    private void objectChanged() {
        description.setText(car.getModelYear() + " " + car.getMake() + " " + car.getModel());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}

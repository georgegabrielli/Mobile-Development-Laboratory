package com.carapp.gobi.carapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.carapp.gobi.carapp.domain.Car;

public class EditCar extends AppCompatActivity {

    private Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_car);

        Intent i = getIntent();

        this.car = (Car) i.getSerializableExtra("car");
        final EditText textChassisCode = findViewById(R.id.editTextChassisCode);
        textChassisCode.setEnabled(false);
        textChassisCode.setText(car.getChassisCode());

        final EditText textMake = findViewById(R.id.editTextMake);
        textMake.setText(car.getMake(), TextView.BufferType.EDITABLE);

        final EditText textModel = findViewById(R.id.editTextModel);
        textModel.setText(car.getModel(), TextView.BufferType.EDITABLE);

        final EditText textCubicCapacity = findViewById(R.id.editTextCubicCapacity);
        textCubicCapacity.setText(car.getCubicCapacity() + "", TextView.BufferType.EDITABLE);

        final EditText textModelYear = findViewById(R.id.editTextModelYear);
        textModelYear.setText(car.getModelYear() + "", TextView.BufferType.EDITABLE);

        findViewById(R.id.buttonSave).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                if (!textChassisCode.getText().toString().equals("")) {
                    car.setChassisCode(textChassisCode.getText().toString());
                }
                if (!textCubicCapacity.getText().toString().equals("")) {
                    car.setCubicCapacity(Integer.parseInt(textCubicCapacity.getText().toString()));
                }
                if (!textMake.getText().toString().equals("")) {
                    car.setMake(textMake.getText().toString());
                }
                if (!textModel.getText().toString().equals("")) {
                    car.setModel(textModel.getText().toString());
                }
                if (!textModelYear.getText().toString().equals("")) {
                    car.setModelYear(Integer.parseInt(textModelYear.getText().toString()));
                }

                sendEmail(car);

                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", car);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }


    private void sendEmail(Car p2){
        String s="";
        s+=p2.toString();
        s+="\n\n was updated to:  \n\n";
        s+=car.toString();

        String[] TO = {"gabrielli.george@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");

        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your car was updated!");
        emailIntent.putExtra(Intent.EXTRA_TEXT, s);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(EditCar.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}

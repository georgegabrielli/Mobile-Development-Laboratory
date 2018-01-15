package com.carapp.gobi.carapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.carapp.gobi.carapp.domain.Car;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class EditCar extends AppCompatActivity {

    private Car car;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_car);

        databaseReference = FirebaseDatabase.getInstance().getReference("cars");

        Intent i = getIntent();

        this.car = (Car) i.getSerializableExtra("car");
        final EditText textChassisCode = findViewById(R.id.editTextChassisCode);
        textChassisCode.setEnabled(car.isNew());
        textChassisCode.setText(car.getChassisCode());

        final Spinner textMake = findViewById(R.id.editTextMake);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.brands_array, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        textMake.setAdapter(adapter);

        textMake.setSelection(adapter.getPosition(car.getMake()));

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
                if (!textMake.getSelectedItem().toString().equals("")) {
                    car.setMake(textMake.getSelectedItem().toString());
                }
                if (!textModel.getText().toString().equals("")) {
                    car.setModel(textModel.getText().toString());
                }
                if (!textModelYear.getText().toString().equals("")) {
                    car.setModelYear(Integer.parseInt(textModelYear.getText().toString()));
                }

                sendEmail(car);

                performSave(car);

                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", car);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }

    private void performSave(Car car) {
        if(car.isNew()){
            String rid = databaseReference.push().getKey();
            car.setId(rid);
            car.setNew(false);
            databaseReference.child(car.getId()).setValue(car);
        }
        else{
            databaseReference.child(car.getId()).setValue(car);
        }
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

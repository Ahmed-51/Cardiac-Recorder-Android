package com.example.cardiacrecord;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {

    Button saveButton;
    String date,time,systolic,diastolic,bloodPressure,comment;
    EditText edtx1,edtx2,edtx3,edtx4,edtx5,edtx6;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ModelClass modelclass;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        saveButton=findViewById(R.id.save_btn);
        edtx1=findViewById(R.id.add_measurement_date_value);
        edtx2=findViewById(R.id.add_systolic_value);
        edtx3= findViewById(R.id.add_diastolic_value);
        edtx4 =findViewById(R.id.add_heart_rate_value);
        edtx5=findViewById(R.id.add_measurement_time_value);
        edtx6=findViewById(R.id.add_comment_value);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputFormat();

            }
        });
    }

    private void inputFormat() {
        if ((Integer.parseInt(edtx2.getText().toString()) >= 0) && (Integer.parseInt(edtx2.getText().toString()) <= 200)) {
            if ((Integer.parseInt(edtx3.getText().toString()) >= 0) && (Integer.parseInt(edtx3.getText().toString()) <= 150)) {
                if ((Integer.parseInt(edtx4.getText().toString()) >= 0) && (Integer.parseInt(edtx4.getText().toString())<=150)) {
                    date= edtx1.getText().toString();
                    time=edtx5.getText().toString();
                    systolic=edtx2.getText().toString();
                    diastolic=edtx3.getText().toString();
                    bloodPressure =edtx4.getText().toString();
                    comment= edtx6.getText().toString();
                    modelclass = new ModelClass(date,time,systolic,diastolic,bloodPressure,comment);
                    new Records().addRecord(modelclass);
                    PreferenceManager.getDefaultSharedPreferences(AddActivity.this).edit().clear().commit();
                    saveData();
                    MainActivity.adapter.notifyDataSetChanged();
                    Toast.makeText(AddActivity.this,"Data Insertion Successful",Toast.LENGTH_LONG).show();
                    finish();

                }
                else{
                    Toast.makeText(AddActivity.this,"Invalid data format added",Toast.LENGTH_LONG).show();

                }

            }
            else {
                Toast.makeText(AddActivity.this,"Invalid data format added",Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(AddActivity.this,"Invalid data format added",Toast.LENGTH_LONG).show();
        }
    }


    private void saveData()
    {
        sharedPreferences = getSharedPreferences("share",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gson = new Gson();
        String jsonString = gson.toJson(Records.mcl);
        editor.putString("share",jsonString);
        editor.apply();
    }
}
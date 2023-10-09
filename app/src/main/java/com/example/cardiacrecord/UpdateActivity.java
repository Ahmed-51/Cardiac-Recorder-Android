package com.example.cardiacrecord;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.gson.Gson;


public class UpdateActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Gson gson;
    ModelClass modelClass;
    EditText dateET, timeET, systolicET, diastolicET, heartRateET, commentET;
    String date, time, systolic, diastolic, bloodPressure, comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Intent intent = getIntent();
        int index = intent.getIntExtra("index", 0);
        dateET = findViewById(R.id.update_measurement_date_value);
        timeET = findViewById(R.id.update_measurement_time_value);
        systolicET = findViewById(R.id.update_systolic_value);
        diastolicET = findViewById(R.id.update_diastolic_value);
        heartRateET = findViewById(R.id.update_heart_rate_value);
        commentET = findViewById(R.id.update_comment_value);
        Button updateButton = findViewById(R.id.update_btn);
        modelClass = Records.mcl.get(index);

        dateET.setText(modelClass.getDate());
        timeET.setText(modelClass.getTime());
        systolicET.setText(modelClass.getSystolic());
        diastolicET.setText(modelClass.getDiastolic());
        heartRateET.setText(modelClass.getBloodPressure());
        commentET.setText(modelClass.getComment());

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date = dateET.getText().toString();
                time = timeET.getText().toString();
                systolic = systolicET.getText().toString();
                diastolic = diastolicET.getText().toString();
                bloodPressure = heartRateET.getText().toString();
                comment = commentET.getText().toString();
                modelClass = new ModelClass(date, time, systolic, diastolic, bloodPressure, comment);
                Records.mcl.set(index, modelClass);
                PreferenceManager.getDefaultSharedPreferences(UpdateActivity.this).edit().clear().commit();
                saveData();
                Records.mcl.set(index, modelClass);
                MainActivity.adapter.notifyDataSetChanged();
                Toast.makeText(UpdateActivity.this, "Update successful", Toast.LENGTH_SHORT).show();
                finish();
            }

        });


    }


    private void saveData() {
        sharedPreferences = getSharedPreferences("share", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gson = new Gson();
        String jsonString = gson.toJson(Records.mcl);
        editor.putString("share", jsonString);
        editor.apply();
    }
}
package com.example.cardiacrecord;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;


public class DetailsActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Gson gson;
    ArrayList<ModelClass> mcl = Records.mcl;
    ModelClass modelClass;
    TextView dateT,timeT,systolicT,diastolicT,heartRateT,commentT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();
        int index = intent.getIntExtra("index",0);
        dateT= findViewById(R.id.details_measurement_date_value);
        timeT = findViewById(R.id.details_measurement_time_value);
        systolicT = findViewById(R.id.details_systolic_value);
        diastolicT = findViewById(R.id.details_diastolic_value);
        heartRateT = findViewById(R.id.details_heart_rate_value);
        commentT = findViewById(R.id.details_comment_value);
        Button updateButton = findViewById( R.id.update_btn);


        retrieveData();


        modelClass = mcl.get(index);
        dateT.setText(modelClass.getDate());
        timeT.setText(modelClass.getTime());
        systolicT.setText(modelClass.getSystolic());
        diastolicT.setText(modelClass.getDiastolic());
        heartRateT.setText(modelClass.getBloodPressure());
        commentT.setText(modelClass.getComment());
    }


    private void retrieveData()
    {
        sharedPreferences = getSharedPreferences("share",MODE_PRIVATE);
        gson = new Gson();
        String jsonString = sharedPreferences.getString("share",null);
        Type type = new TypeToken<ArrayList<ModelClass>>(){}.getType();
        mcl = gson.fromJson(jsonString,type);
        if(mcl ==null)
        {
            mcl = new ArrayList<>();
        }
    }
}
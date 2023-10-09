package com.example.cardiacrecord;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView1;
    public static TaskAdapter adapter;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ModelClass modelclass;
    Gson gson;



    Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.add_btn);
        retrieveData();




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        recyclerView1=findViewById(R.id.recyclerView);
        adapter = new TaskAdapter(MainActivity.this, Records.mcl);
        recyclerView1.setAdapter(adapter);
        adapter.setClickListener(new TaskAdapter.ClickListener() {
            @Override
            public void customOnClick(int position, View v) {

            }

            @Override
            public void customOnLongClick(int position, View v) {

            }


            @Override
            public void onDeleteClick(int position) {
                //Records.mcl.remove(position);
                new Records().deleteRecord(position);
                adapter.notifyItemRemoved(position);
                saveData();
                Toast.makeText(MainActivity.this,"Record Successfully Deleted",Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onEditClick(int position) {
                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                intent.putExtra("index",position);
                startActivity(intent);
            }


            @Override
            public void DetailClick(int position) {
                Intent intent1 = new Intent(MainActivity.this, DetailsActivity.class);
                intent1.putExtra("index",position);
                startActivity(intent1);
            }

        });

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


    private void retrieveData()
    {
        sharedPreferences = getSharedPreferences("share",MODE_PRIVATE);
        gson = new Gson();
        String jsonString = sharedPreferences.getString("share",null);
        Type type = new TypeToken<ArrayList<ModelClass>>(){}.getType();
        Records.mcl = gson.fromJson(jsonString,type);
        if(Records.mcl ==null)
        {
            Records.mcl = new ArrayList<>();
        }
    }
}
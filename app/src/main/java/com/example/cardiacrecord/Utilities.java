package com.example.cardiacrecord;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class Utilities {
    public  void saveData(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("share",context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String jsonString = gson.toJson(Records.mcl);
        editor.putString("share",jsonString);
        editor.apply();
    }
}

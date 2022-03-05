package com.example.jodadavelha;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jodadavelha.offline2playersClasses.InputNameActivity;
import com.example.jodadavelha.online2playersClasses.AuthActivity;

public class OptionalFirstActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optional_first);


    }

    public void openScreenOption1(View view){
        Intent intent = new Intent(getApplicationContext(), InputNameActivity.class);
        startActivity(intent);
    }
    public void openScreenOption2(View view){
        Intent intent = new Intent(getApplicationContext(), AuthActivity.class);
        startActivity(intent);
    }



}
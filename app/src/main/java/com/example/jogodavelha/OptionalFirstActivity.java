package com.example.jogodavelha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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

}
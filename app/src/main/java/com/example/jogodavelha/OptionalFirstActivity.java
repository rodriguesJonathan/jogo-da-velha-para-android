package com.example.jogodavelha;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OptionalFirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optional_first);
        /*
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.child("Second child").setValue("Hello Word");
         */
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
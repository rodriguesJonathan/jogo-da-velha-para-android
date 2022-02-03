package com.example.jogodavelha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OptionalFirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optional_first);
        //Teste Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.child("First child").setValue("Hello Word");
    }

    public void openScreenOption1(View view){
        Intent intent = new Intent(getApplicationContext(), InputNameActivity.class);
        startActivity(intent);
    }

}
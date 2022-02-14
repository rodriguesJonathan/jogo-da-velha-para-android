package com.example.jogodavelha;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;



public class ChooseRoomActivity extends AppCompatActivity {
    Random random = new Random();
    ArrayList<String> salas = new ArrayList<>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    ValueEventListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_room);

        salas.add(null);
        myRef.child("Rooms").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                salas.add(snapshot.getKey());
                Log.i("Info onChildAdded", snapshot.getKey());

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                salas.remove(snapshot.getKey());
                Log.i("Info onChildRemoved", snapshot.getKey());
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void adicionarSala(View view){


        int numeroSala;
        String numText;
        do{
            numeroSala = random.nextInt(9999)+1;
            numText = Integer.toString(numeroSala);
            for (int a = numText.length(); a < 4; a++){
                numText = "0"+numText;
            }
        }while (salas.contains(numText));

        myRef.child("Rooms").child(numText).child("Jogador11").setValue("Nome do jogador 1");
        myRef.child("Rooms").child(numText).child("Jogador22").setValue("Nome do jogador 2");
        myRef.child("Rooms").child(numText).child("Tabuleiro").child("tab11").setValue("-");
        myRef.child("Rooms").child(numText).child("Tabuleiro").child("tab12").setValue("-");
        myRef.child("Rooms").child(numText).child("Tabuleiro").child("tab13").setValue("-");
        myRef.child("Rooms").child(numText).child("Tabuleiro").child("tab21").setValue("-");
        myRef.child("Rooms").child(numText).child("Tabuleiro").child("tab22").setValue("-");
        myRef.child("Rooms").child(numText).child("Tabuleiro").child("tab23").setValue("-");
        myRef.child("Rooms").child(numText).child("Tabuleiro").child("tab31").setValue("-");
        myRef.child("Rooms").child(numText).child("Tabuleiro").child("tab32").setValue("-");
        myRef.child("Rooms").child(numText).child("Tabuleiro").child("tab33").setValue("-");

        TextView textRoom = findViewById(R.id.textView8);
        ImageButton copyButton = findViewById(R.id.imageButton);
        textRoom.setText(numText);
        textRoom.setVisibility( View.VISIBLE);
        copyButton.setVisibility(View.VISIBLE);


    }
}
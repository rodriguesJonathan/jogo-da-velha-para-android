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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Random;

public class CreateRoomActivity extends AppCompatActivity {
    private Random random = new Random();
    private ArrayList<String> salas = new ArrayList<>();
    private String salaCriada;

    private FirebaseUser user;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);

        mAuth = FirebaseAuth.getInstance();
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

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser) ;
        if (currentUser != null){
            Log.i("Login", "Está logado");
        }else {
            Log.i("Login", "Não está logado");
        }
    }

    public void addRoom(View view){
        if(salaCriada == null) {
            int numeroSala;
            String numText;
            do {
                numeroSala = random.nextInt(9999) + 1;
                numText = Integer.toString(numeroSala);
                for (int a = numText.length(); a < 4; a++) {
                    numText = "0" + numText;
                }
            } while (salas.contains(numText));

            myRef.child("Rooms").child(numText).child("Jogador1").setValue("-");
            myRef.child("Rooms").child(numText).child("Jogador2").setValue("-");
            myRef.child("Rooms").child(numText).child("Tabuleiro").child("tab11").setValue("-");
            myRef.child("Rooms").child(numText).child("Tabuleiro").child("tab12").setValue("-");
            myRef.child("Rooms").child(numText).child("Tabuleiro").child("tab13").setValue("-");
            myRef.child("Rooms").child(numText).child("Tabuleiro").child("tab21").setValue("-");
            myRef.child("Rooms").child(numText).child("Tabuleiro").child("tab22").setValue("-");
            myRef.child("Rooms").child(numText).child("Tabuleiro").child("tab23").setValue("-");
            myRef.child("Rooms").child(numText).child("Tabuleiro").child("tab31").setValue("-");
            myRef.child("Rooms").child(numText).child("Tabuleiro").child("tab32").setValue("-");
            myRef.child("Rooms").child(numText).child("Tabuleiro").child("tab33").setValue("-");

            TextView roomCode = findViewById(R.id.textView6);
            ImageButton copyCode = findViewById(R.id.imageButton);
            ImageView imageLoading = findViewById(R.id.imageView4);
            TextView textLoading = findViewById(R.id.textView8);

            roomCode.setText(numText);


            roomCode.setVisibility(View.VISIBLE);
            copyCode.setVisibility(View.VISIBLE);
            imageLoading.setVisibility(View.VISIBLE);
            textLoading.setVisibility(View.VISIBLE);

            salaCriada = numText;
        }else{
            Toast.makeText(getApplicationContext(), "A sala já foi criada", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUI(FirebaseUser user) {
        this.user = user;
    }
}
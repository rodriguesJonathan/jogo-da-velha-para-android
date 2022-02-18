package com.example.jogodavelha;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ChooseRoomActivity extends AppCompatActivity {

    ArrayList<String> salas = new ArrayList<>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    EditText editNumeroSala;
    private FirebaseAuth mAuth;

    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_room);

        mAuth = FirebaseAuth.getInstance();
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

        editNumeroSala = findViewById(R.id.editTextNumber);
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



    public void entrarSala(View view){
        if (salas.contains(editNumeroSala.getText().toString())){
            DatabaseReference salaReferencia = myRef.child("Rooms").child(editNumeroSala.getText().toString());
            String[] nomeCompletoJogador =  user.getDisplayName().split(" ");
            String nomeJogador = nomeCompletoJogador[0]+" "+nomeCompletoJogador[nomeCompletoJogador.length - 1];

            salaReferencia.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Log.i("Info Data", nomeJogador);
                    Player player = new Player(nomeJogador);

                    String namePlayer1inFirebase = snapshot.child("Jogador1").getValue().toString();
                    String namePlayer2inFirebase = snapshot.child("Jogador2").getValue().toString();

                    if(namePlayer1inFirebase.equals("-")){
                        myRef.child("Rooms").child(editNumeroSala.getText().toString()).child("Jogador1").setValue(nomeJogador);
                    }else if(namePlayer2inFirebase.equals("-")){
                        myRef.child("Rooms").child(editNumeroSala.getText().toString()).child("Jogador2").setValue(nomeJogador);
                    }else{
                        Toast.makeText(getApplicationContext(), "A sala está cheia", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    /*
                    Intent intent = new Intent(getApplicationContext(), Option2.class);
                    intent.putExtra("player", player);
                    startActivity(intent);
                     */


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }else{
            Toast.makeText(getApplicationContext(), "A sala não existe", Toast.LENGTH_SHORT).show();
        }
    }

    public void goToNextActivy(View view){
        Intent intent = new Intent(ChooseRoomActivity.this, CreateRoomActivity.class);

        startActivity(intent);
    }
    private void updateUI(FirebaseUser user) {
        this.user = user;
    }
}
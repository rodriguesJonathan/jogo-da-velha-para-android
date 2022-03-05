package com.example.jodadavelha.online2playersClasses;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jodadavelha.Player;
import com.example.jodadavelha.R;
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

            String digitedNumberRoom = editNumeroSala.getText().toString();
            DatabaseReference roomReference = myRef.child("Rooms").child(digitedNumberRoom);
            String[] nomeCompletoJogador =  user.getDisplayName().split(" ");
            String playerName = nomeCompletoJogador[0]+" "+nomeCompletoJogador[nomeCompletoJogador.length - 1];

            roomReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Log.i("Info Data", playerName);
                    Player newPlayer = new Player(playerName);

                    Player player1inFirebase = snapshot.child("player1").getValue(Player.class);
                    Player player2inFirebase = snapshot.child("player2").getValue(Player.class);

                    Intent intent = new Intent(getApplicationContext(), Option2.class);

                    if(player2inFirebase.getName().equals("-")){
                        newPlayer.setUsedSymbol(player2inFirebase.getUsedSymbol());
                        newPlayer.setUserCode(player2inFirebase.getUserCode());
                        roomReference.child("player2").setValue(newPlayer);
                        intent.putExtra("myNodePlayer", "player2");
                        intent.putExtra("opponentNodePlayer", "player1");
                    }else if(player1inFirebase.getName().equals("-")){
                        newPlayer.setUsedSymbol(player1inFirebase.getUsedSymbol());
                        newPlayer.setUserCode(player1inFirebase.getUserCode());
                        roomReference.child("player1").setValue(newPlayer);
                        intent.putExtra("myNodePlayer", "player1");
                        intent.putExtra("opponentNodePlayer", "player2");
                    }else{
                        Toast.makeText(getApplicationContext(), "A sala está cheia", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    //Sala validada

                    intent.putExtra("roomNumber", digitedNumberRoom);

                    startActivity(intent);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }else{
            Toast.makeText(getApplicationContext(), "A sala não encontrada", Toast.LENGTH_SHORT).show();
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
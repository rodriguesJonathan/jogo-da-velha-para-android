package com.example.jogodavelha;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
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
import java.util.Random;



public class ChooseRoomActivity extends AppCompatActivity {
    Random random = new Random();
    ArrayList<String> salas = new ArrayList<>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    String salaCriada;
    EditText editNumeroSala;
    String nomeJogador1;
    String nomeJogador2;
    private FirebaseAuth mAuth;

    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_room);

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

    public void adicionarSala(View view){
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

            TextView textRoom = findViewById(R.id.textView8);
            ImageButton copyButton = findViewById(R.id.imageButton);

            editNumeroSala.setText(numText);
            textRoom.setText(numText);

            textRoom.setVisibility(View.VISIBLE);
            copyButton.setVisibility(View.VISIBLE);
            salaCriada = numText;
        }else{
            Toast.makeText(getApplicationContext(), "A sala já foi criada", Toast.LENGTH_SHORT).show();
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
                    Log.i("Info Data", nomeJogador2);
                    Jogador jogador = new Jogador(nomeJogador);

                    if(nomeJogador1.equals("-")){
                        myRef.child("Rooms").child(editNumeroSala.getText().toString()).child("Jogador1").setValue(nomeJogador);
                        jogador.setSimboloUsado('x');
                    }else if(nomeJogador2.equals("-")){
                        myRef.child("Rooms").child(editNumeroSala.getText().toString()).child("Jogador2").setValue(nomeJogador);
                        jogador.setSimboloUsado('o');
                    }else{
                        Toast.makeText(getApplicationContext(), "A sala está cheia", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Intent intent = new Intent(getApplicationContext(), Option2.class);
                    intent.putExtra("jogador", jogador);
                    startActivity(intent);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });




        }else{
            Toast.makeText(getApplicationContext(), "A sala não existe", Toast.LENGTH_SHORT).show();
        }
    }
    private void updateUI(FirebaseUser user) {
        this.user = user;
    }
}
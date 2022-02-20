package com.example.jogodavelha;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
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

public class CreateRoomActivity extends AppCompatActivity {
    private Random random = new Random();
    private ArrayList<String> salas = new ArrayList<>();
    private String salaCriada;
    private String namePlayer2Firebase;
    private ValueEventListener valueEventListenerAddRoom;

    private FirebaseUser user;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();

    private Switch switchSetting;
    private RadioButton radioXplayer;
    private RadioButton radioOplayer;
    private RadioButton radioMe;
    private RadioButton radioOpponent;

    private RadioGroup radioGroup1;
    private RadioGroup radioGroup2;



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


        switchSetting = findViewById(R.id.switchSettings);

        radioXplayer = findViewById(R.id.radioXplayer);
        radioOplayer = findViewById(R.id.radioOplayer);

        radioMe = findViewById(R.id.radioMe);
        radioOpponent = findViewById(R.id.radioOpponent);

        radioGroup1 = findViewById(R.id.RadioGroup1);
        radioGroup2 = findViewById(R.id.RadioGroup2);
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

    public void showSwitchSettings(View view){
        if (switchSetting.isChecked()){
            radioGroup1.setVisibility(View.GONE);
            radioGroup2.setVisibility(View.GONE);
        }else{
            radioGroup1.setVisibility(View.VISIBLE);
            radioGroup2.setVisibility(View.VISIBLE);
        }
    }

    public void addRoom(View view){
        if(salaCriada == null) {

            String numText;
            do {
                int numeroSala = random.nextInt(9999) + 1;
                numText = Integer.toString(numeroSala);
                for (int a = numText.length(); a < 4; a++) {
                    numText = "0" + numText;
                }
            } while (salas.contains(numText));

            salaCriada = numText;

            String[] nomeCompletoJogador =  user.getDisplayName().split(" ");
            String nomeJogador = nomeCompletoJogador[0]+" "+nomeCompletoJogador[nomeCompletoJogador.length - 1];
            Player player1 = new Player(nomeJogador);
            Player player2 = new Player();
            Grid grid = new Grid();


            if (switchSetting.isChecked()){
                Random random = new Random();
                int numRandomSymbol = random.nextInt(2);
                if(numRandomSymbol == 0){
                    player1.setUsedSymbol("X");
                    player2.setUsedSymbol("O");
                }else if(numRandomSymbol == 1){
                    player1.setUsedSymbol("O");
                    player2.setUsedSymbol("X");
                }
                int numRandomFirstPlayer = random.nextInt(2);
                if (numRandomFirstPlayer == 0){
                    grid.setFirstPlayer("player1");
                    grid.setCurrentPlayer("player1");
                }else if(numRandomFirstPlayer == 1){
                    grid.setFirstPlayer("player2");
                    grid.setCurrentPlayer("player2");
                }


            }else{
                if(radioXplayer.isChecked()){
                    player1.setUsedSymbol("X");
                    player2.setUsedSymbol("O");
                }else if(radioOplayer.isChecked()){
                    player1.setUsedSymbol("O");
                    player2.setUsedSymbol("X");
                }

                if (radioMe.isChecked()){
                    grid.setFirstPlayer("player1");
                    grid.setCurrentPlayer("player1");
                }else if(radioOpponent.isChecked()){
                    grid.setFirstPlayer("player2");
                    grid.setCurrentPlayer("player2");
                }


            }




            myRef.child("Rooms").child(numText).child("player1").setValue(player1);


            myRef.child("Rooms").child(numText).child("player2").setValue(player2);



            myRef.child("Rooms").child(numText).child("grid").setValue(grid);


            valueEventListenerAddRoom = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Log.i("info jog2",snapshot.getValue().toString());
                    namePlayer2Firebase = snapshot.getValue().toString();
                    if (!namePlayer2Firebase.equals("-")){
                        Toast.makeText(getApplicationContext(), "Ir para proxima activity", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            };

            myRef.child("Rooms").child(numText).child("player2").child("name").addValueEventListener(valueEventListenerAddRoom);

            TextView roomCode = findViewById(R.id.textViewRoomCode);
            ImageButton copyCode = findViewById(R.id.copyButton);
            ProgressBar progressBar = findViewById(R.id.progressBar);
            TextView textLoading = findViewById(R.id.textView8);

            roomCode.setText("Código da sala: "+salaCriada);


            roomCode.setVisibility(View.VISIBLE);
            copyCode.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            textLoading.setVisibility(View.VISIBLE);

            switchSetting.setEnabled(false);
            radioXplayer.setEnabled(false);
            radioOplayer.setEnabled(false);
            radioMe.setEnabled(false);
            radioOpponent.setEnabled(false);


        }else{
            Toast.makeText(getApplicationContext(), "A sala já foi criada", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUI(FirebaseUser user) {
        this.user = user;
    }

    public void copyCode(View view){
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("textView", salaCriada);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(getApplicationContext(), "Código da sala copia da área de transferência", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onDestroy() {
        if (salaCriada != null){
            myRef.child("Rooms").child(salaCriada).child("player2").child("name").removeEventListener(valueEventListenerAddRoom);
            myRef.child("Rooms").child(salaCriada).removeValue();
        }
        super.onDestroy();
    }

}
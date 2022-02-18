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

    private FirebaseUser user;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();

    private Switch switchSetting;
    private RadioButton radioXplayer;
    private RadioButton radioOplayer;
    private RadioButton radioXfirst;
    private RadioButton radioOfirst;

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

        radioXfirst = findViewById(R.id.radioXfirst);
        radioOfirst = findViewById(R.id.radioOfirst);

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

            myRef.child("Rooms").child(numText).child("Jogador1").setValue("Jogador teste");
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


            myRef.child("Rooms").child(numText).child("Jogador2").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Log.i("info jog2",snapshot.getValue().toString());
                    if (!snapshot.getValue().toString().equals("-")){
                        Toast.makeText(getApplicationContext(), "Ir para proxima activity", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

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
            radioXfirst.setEnabled(false);
            radioOfirst.setEnabled(false);


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
}
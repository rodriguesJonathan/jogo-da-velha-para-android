package com.example.jogodavelha;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Option2  extends GameActivity{

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();
    private Bundle extra;
    private String currentPlayerCode;
    private String roomNumber;
    private Player mySelfPlayer;
    private Player opponentPlayer;
    private Grid grid;
    private SquareXorOView image;
    private int currentImageId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        extra = getIntent().getExtras();
        roomNumber = extra.getString("roomNumber");
        String myNodePlayer = extra.getString("myNodePlayer");
        String opponentNodePlayer = extra.getString("opponentNodePlayer");

        myRef.child("Rooms").child(roomNumber).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mySelfPlayer = snapshot.child(myNodePlayer).getValue(Player.class);
                opponentPlayer = snapshot.child(opponentNodePlayer).getValue(Player.class);
                currentPlayerCode = snapshot.child("grid").child("currentPlayerCode").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        myRef.child("Rooms").child(roomNumber).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                grid = snapshot.child("grid").getValue(Grid.class);
                currentPlayerCode = snapshot.child("grid").child("currentPlayerCode").getValue().toString();
                if (mySelfPlayer.getUserCode().equals(currentPlayerCode)){
                    textVezDoJogador.setText("É a tua vez.");
                }
                else{
                    textVezDoJogador.setText("É a vez de "+ opponentPlayer.getName()+".");
                }
                if(!snapshot.child("currentImageId").getValue().toString().equals("-")){
                    currentImageId = Integer.parseInt(snapshot.child("currentImageId").getValue().toString());
                    image = findViewById(currentImageId);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        myRef.child("Rooms").child(roomNumber).child("grid").addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String key = snapshot.getKey();
                String rowColomn = key.substring(3,4) + key.substring(11);

                if (snapshot.getValue().toString().equals("O")){
                    image.setImageResource(R.drawable.ic_o_letter_svg);
                }else if(snapshot.getValue().toString().equals("X")){
                    image.setImageResource(R.drawable.ic_x_letter_svg);
                }


            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

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
    public void inserirXO(View view){
        if(currentPlayerCode.equals(mySelfPlayer.getUserCode())){
            currentImageId = view.getId();
            myRef.child("Rooms").child(roomNumber).child("currentImageId").setValue(currentImageId);


            String transitionName = view.getTransitionName();
            int row = Integer.parseInt(transitionName.substring(0,1));
            int column = Integer.parseInt(transitionName.substring(1,2));
            grid.setInRowAndColumn(mySelfPlayer.getUsedSymbol(), row, column);
            myRef.child("Rooms").child(roomNumber).child("grid").setValue(grid);
            currentPlayerCode = (currentPlayerCode.equals("1") ? "2" : "1");
            myRef.child("Rooms").child(roomNumber).child("grid").child("currentPlayerCode").setValue(currentPlayerCode);
        }else{
            Toast.makeText(this, "Espere teu oponente jogar", Toast.LENGTH_SHORT).show();
        }
    }

}

package com.example.jogodavelha.online2playersClasses;

import android.app.UiModeManager;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.jogodavelha.GameActivity;
import com.example.jogodavelha.Grid;
import com.example.jogodavelha.Player;
import com.example.jogodavelha.SquareXorOView;
import com.example.jogodavelha.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Option2  extends GameActivity {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();
    private Bundle extra;
    private String currentPlayerCode;
    private String roomNumber;
    private Player mySelfPlayer;
    private Player opponentPlayer;
    private SquareXorOView image;
    private String myNodePlayer;
    private String opponentNodePlayer;
    private ValueEventListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        extra = getIntent().getExtras();
        roomNumber = extra.getString("roomNumber");
        myNodePlayer = extra.getString("myNodePlayer");
        opponentNodePlayer = extra.getString("opponentNodePlayer");
        textVezDoJogador = findViewById(R.id.textVezDoJogador);


        listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Log.i("info", "EventListener");

                grid = snapshot.child("grid").getValue(Grid.class);
                mySelfPlayer = snapshot.child(myNodePlayer).getValue(Player.class);
                opponentPlayer = snapshot.child(opponentNodePlayer).getValue(Player.class);






                if(grid.getAddedSimblesLength() > 0){
                    image = findViewById(grid.getCurrentImageId());
                    int row = Integer.parseInt(image.getTransitionName().substring(0, 1));
                    int column = Integer.parseInt(image.getTransitionName().substring(1, 2));
                    Log.i("info", "row: "+row+"column: "+column);
                    Log.i("info", grid.getInRowAndColumn(row, column));
                    if (grid.getInRowAndColumn(row, column).equals("X"))
                        image.setImageResource(R.drawable.ic_x_letter_svg);
                    else if (grid.getInRowAndColumn(row, column).equals("O"))
                        image.setImageResource(R.drawable.ic_o_letter_svg);
                }

                if (!someoneWon)
                    checkWinner(grid);

                if(someoneWon && !currentPlayerCode.equals("-")){
                    grid.setCurrentPlayerCode("-");
                }
                currentPlayerCode = grid.getCurrentPlayerCode();


                if (mySelfPlayer.getUserCode().equals(currentPlayerCode)) {
                    textVezDoJogador.setText("É a tua vez.");
                } else if(opponentPlayer.getUserCode().equals(currentPlayerCode)) {
                    textVezDoJogador.setText("É a vez de " + opponentPlayer.getName() + ".");
                }

                String myRestartInfo = snapshot.child("restart").child(myNodePlayer).getValue().toString();
                String opponentRestartInfo = snapshot.child("restart").child(opponentNodePlayer).getValue().toString();

                if (myRestartInfo.equals("-") && opponentRestartInfo.equals("y")) {
                    AlertDialog.Builder build = new AlertDialog.Builder(Option2.this);
                    build.setTitle(opponentPlayer.getName() + ", pediu para reiniciar o jogo");
                    build.setMessage("Você deseja reiniciar o jogo?");
                    build.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            grid.restart();
                            HashMap<String, String> subMap = new HashMap<>();

                            subMap.put(myNodePlayer, "y");
                            subMap.put(opponentNodePlayer, opponentRestartInfo);

                            HashMap<String, Object> map = new HashMap<>();

                            map.put("grid", grid);
                            map.put(myNodePlayer, mySelfPlayer);
                            map.put(opponentNodePlayer, opponentPlayer);
                            map.put("restart", subMap);
                            map.put("winner", "-");


                            myRef.child("Rooms").child(roomNumber).setValue(map);
                            grid.setCurrentPlayerCode(grid.getFirstPlayerCode());
                        }
                    });
                    build.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            myRef.child("Rooms").child(roomNumber).child("restart").child(myNodePlayer).setValue("n");
                        }
                    });
                    AlertDialog dialog = build.create();
                    dialog.show();
                } else if (myRestartInfo.equals("y") && opponentRestartInfo.equals("n")) {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("player1", "-");
                    map.put("player2", "-");
                    myRef.child("Rooms").child(roomNumber).child("restart").setValue(map);
                    AlertDialog.Builder build = new AlertDialog.Builder(Option2.this);
                    build.setTitle(opponentPlayer.getName() + ", Recusou a solicitação");
                    build.setPositiveButton("Certo", null);
                    AlertDialog dialog = build.create();
                    dialog.show();

                } else if (myRestartInfo.equals("y") && opponentRestartInfo.equals("y")) {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("player1", "-");
                    map.put("player2", "-");

                    myRef.child("Rooms").child(roomNumber).child("restart").setValue(map);


                    ((SquareXorOView) findViewById(R.id.tabela11)).setImageResource(R.drawable.vazio);
                    ((SquareXorOView) findViewById(R.id.tabela12)).setImageResource(R.drawable.vazio);
                    ((SquareXorOView) findViewById(R.id.tabela13)).setImageResource(R.drawable.vazio);
                    ((SquareXorOView) findViewById(R.id.tabela21)).setImageResource(R.drawable.vazio);
                    ((SquareXorOView) findViewById(R.id.tabela22)).setImageResource(R.drawable.vazio);
                    ((SquareXorOView) findViewById(R.id.tabela23)).setImageResource(R.drawable.vazio);
                    ((SquareXorOView) findViewById(R.id.tabela31)).setImageResource(R.drawable.vazio);
                    ((SquareXorOView) findViewById(R.id.tabela32)).setImageResource(R.drawable.vazio);
                    ((SquareXorOView) findViewById(R.id.tabela33)).setImageResource(R.drawable.vazio);

                    if (imagemVermelhas[0] != null){
                        for(SquareXorOView imagemVermelha : imagemVermelhas){
                            if(uiModeManager.getNightMode() == UiModeManager.MODE_NIGHT_YES){
                                imagemVermelha.setColorFilter(Color.WHITE);
                            } else if(uiModeManager.getNightMode() == UiModeManager.MODE_NIGHT_NO){
                                imagemVermelha.setColorFilter(Color.BLACK);
                            }
                        }
                    }
                    someoneWon = false;


                    AlertDialog.Builder build = new AlertDialog.Builder(Option2.this);
                    build.setTitle("O jogo foi reiniciado");
                    build.setPositiveButton("Certo", null);
                    AlertDialog dialog = build.create();
                    dialog.show();
                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        myRef.child("Rooms").child(roomNumber).addValueEventListener(listener);


    }



    @Override
    public void insertXO(View view){
        if(currentPlayerCode.equals(mySelfPlayer.getUserCode())){
            grid.setCurrentImageId(view.getId());

            String transitionName = view.getTransitionName();
            int row = Integer.parseInt(transitionName.substring(0,1));
            int column = Integer.parseInt(transitionName.substring(1,2));
            if (grid.getInRowAndColumn(row, column).equals("-")){
                grid.setInRowAndColumn(mySelfPlayer.getUsedSymbol(), row, column);

                currentPlayerCode = (currentPlayerCode.equals("1") ? "2" : "1");
                grid.setCurrentPlayerCode(currentPlayerCode);

                myRef.child("Rooms").child(roomNumber).child("grid").setValue(grid);

            }else{
                Toast.makeText(this, "Posicão inválida!!", Toast.LENGTH_SHORT).show();
            }

        }else if(currentPlayerCode.equals(opponentPlayer.getUserCode())){
            Toast.makeText(this, "Espere teu oponente jogar", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "O jogo terminou", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void restartGame(View view){
        myRef.child("Rooms").child(roomNumber).child("restart").child(myNodePlayer).setValue("y");
        Toast.makeText(this, "Solicitação enviada", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void informarVencedor(){
        AlertDialog.Builder build = new AlertDialog.Builder(Option2.this);
        build.setTitle("Temos um vencedor(a)");

        if(!grid.getCurrentPlayerCode().equals(mySelfPlayer.getUserCode())){
            build.setMessage("Meus parabéns, "+mySelfPlayer.getName()+", pela tua vitória!!");
        }else{
            build.setMessage("Você perdeu!\n"+opponentPlayer.getName()+" ganhou.");
        }
        build.setPositiveButton("Certo", null);
        AlertDialog dialog = build.create();
        dialog.show();
    }

    @Override
    protected void informarEmpate(){
        AlertDialog.Builder build = new AlertDialog.Builder(Option2.this);
        build.setTitle("Deu velha!!");
        build.setMessage("Vocês devem ter o mesmo QI.");
        build.setPositiveButton("Certo", null);
        AlertDialog dialog = build.create();
        dialog.show();
    }

    @Override
    public void onDestroy() {
        myRef.child("Rooms").child(roomNumber).removeEventListener(listener);
        if (opponentPlayer.getName().equals("-") && !mySelfPlayer.getName().equals("-")){
            myRef.child("Rooms").child(roomNumber).removeValue();
        }else if(!opponentPlayer.getName().equals("-") && !mySelfPlayer.getName().equals("-")) {
            mySelfPlayer.setName("-");
            //mySelfPlayer.setName("##player_leave##");
            myRef.child("Rooms").child(roomNumber).child(myNodePlayer).setValue(mySelfPlayer);
        }
        super.onDestroy();
    }

}

package com.example.jogodavelha.online2playersClasses;

import android.app.UiModeManager;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
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
    private String roomNumber;
    private Player mySelfPlayer;
    private Player opponentPlayer;
    private SquareXorOView image;
    private String myNodePlayer;
    private String opponentNodePlayer;
    private ValueEventListener listener;
    private int SimblesLengthLocal = 0;
    private boolean leftGame = false;

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


                if(opponentPlayer.getName().equals("-")){
                    leftGame = true;
                }

                if(leftGame && !opponentPlayer.getName().equals("-")){
                    recreate();
                }



                if (opponentPlayer.getName().equals("-")){
                    gameStopped = true;
                    AlertDialog.Builder build = new AlertDialog.Builder(Option2.this);
                    build.setTitle("O Oponente saiu do jogo.\nEstamos aguardando o pr??ximo\nSala: "+roomNumber);
                    build.setCancelable(false);
                    build.setNegativeButton("Sair", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            leftGame = false;
                            finish();
                        }
                    });
                    ProgressBar progressBar = new ProgressBar(Option2.this);
                    build.setView(progressBar);
                    AlertDialog dialog = build.create();
                    dialog.show();
                    return;
                }
                if(grid.getAddedSimblesLength() > 0 && grid.getAddedSimblesLength() != SimblesLengthLocal){
                    image = findViewById(grid.getCurrentImageId());
                    int row = Integer.parseInt(image.getTransitionName().substring(0, 1));
                    int column = Integer.parseInt(image.getTransitionName().substring(1, 2));
                    Log.i("info", "row: "+row+"column: "+column);
                    Log.i("info", grid.getInRowAndColumn(row, column));
                    if (grid.getInRowAndColumn(row, column).equals("X")) {
                        image.setImageResource(R.drawable.ic_x_letter_svg);
                        SimblesLengthLocal += 1;
                    }
                    else if (grid.getInRowAndColumn(row, column).equals("O")) {
                        image.setImageResource(R.drawable.ic_o_letter_svg);
                        SimblesLengthLocal += 1;
                    }
                }

                if (!gameStopped)
                    checkWinner(grid);

                if(gameStopped && !grid.getCurrentPlayerCode().equals("-")){
                    grid.setCurrentPlayerCode("-");
                }


                if (mySelfPlayer.getUserCode().equals(grid.getCurrentPlayerCode())) {
                    textVezDoJogador.setText("?? a tua vez.");
                } else if(opponentPlayer.getUserCode().equals(grid.getCurrentPlayerCode())) {
                    textVezDoJogador.setText("?? a vez de " + opponentPlayer.getName() + ".");
                }

                String myRestartInfo = snapshot.child("restart").child(myNodePlayer).getValue().toString();
                String opponentRestartInfo = snapshot.child("restart").child(opponentNodePlayer).getValue().toString();

                if (myRestartInfo.equals("-") && opponentRestartInfo.equals("y")) {
                    AlertDialog.Builder build = new AlertDialog.Builder(Option2.this);
                    build.setTitle(opponentPlayer.getName() + ", pediu para reiniciar o jogo");
                    build.setMessage("Voc?? deseja reiniciar o jogo?");
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
                    build.setNegativeButton("N??o", new DialogInterface.OnClickListener() {
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
                    build.setTitle(opponentPlayer.getName() + ", Recusou a solicita????o");
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
                    SimblesLengthLocal = 0;
                    if (imagemVermelhas[0] != null){
                        for(SquareXorOView imagemVermelha : imagemVermelhas){
                            if(uiModeManager.getNightMode() == UiModeManager.MODE_NIGHT_YES){
                                imagemVermelha.setColorFilter(Color.WHITE);
                            } else if(uiModeManager.getNightMode() == UiModeManager.MODE_NIGHT_NO){
                                imagemVermelha.setColorFilter(Color.BLACK);
                            }
                        }
                    }
                    gameStopped = false;


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
        if(grid.getCurrentPlayerCode().equals(mySelfPlayer.getUserCode())){
            grid.setCurrentImageId(view.getId());

            String transitionName = view.getTransitionName();
            int row = Integer.parseInt(transitionName.substring(0,1));
            int column = Integer.parseInt(transitionName.substring(1,2));
            if (grid.getInRowAndColumn(row, column).equals("-")){
                grid.setInRowAndColumn(mySelfPlayer.getUsedSymbol(), row, column);

                grid.setCurrentPlayerCode(grid.getCurrentPlayerCode().equals("1") ? "2" : "1");

                myRef.child("Rooms").child(roomNumber).child("grid").setValue(grid);

            }else{
                Toast.makeText(this, "Posic??o inv??lida!!", Toast.LENGTH_SHORT).show();
            }

        }else if(grid.getCurrentPlayerCode().equals(opponentPlayer.getUserCode())){
            Toast.makeText(this, "Espere teu oponente jogar", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "O jogo terminou", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void restartGame(View view){
        myRef.child("Rooms").child(roomNumber).child("restart").child(myNodePlayer).setValue("y");
        Toast.makeText(this, "Solicita????o enviada", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void informarVencedor(){
        AlertDialog.Builder build = new AlertDialog.Builder(Option2.this);
        build.setTitle("Temos um vencedor(a)");

        if(!grid.getCurrentPlayerCode().equals(mySelfPlayer.getUserCode())){
            build.setMessage("Meus parab??ns, "+mySelfPlayer.getName()+", pela tua vit??ria!!");
        }else{
            build.setMessage("Voc?? perdeu!\n"+opponentPlayer.getName()+" ganhou.");
        }
        build.setPositiveButton("Certo", null);
        AlertDialog dialog = build.create();
        dialog.show();
    }

    @Override
    protected void informarEmpate(){
        AlertDialog.Builder build = new AlertDialog.Builder(Option2.this);
        build.setTitle("Deu velha!!");
        build.setMessage("Voc??s devem ter o mesmo QI.");
        build.setPositiveButton("Certo", null);
        AlertDialog dialog = build.create();
        dialog.show();
    }

    @Override
    public void onDestroy() {
        myRef.child("Rooms").child(roomNumber).removeEventListener(listener);
        if(!leftGame){
            if (opponentPlayer.getName().equals("-") && !mySelfPlayer.getName().equals("-")){
                myRef.child("Rooms").child(roomNumber).removeValue();
            }else if(!opponentPlayer.getName().equals("-") && !mySelfPlayer.getName().equals("-")) {
                mySelfPlayer.setName("-");
                grid.restart();
                HashMap<String, Object> map = new HashMap<>();
                map.put("grid", grid);
                map.put(myNodePlayer, mySelfPlayer);
                myRef.child("Rooms").child(roomNumber).updateChildren(map);
            }
        }


        super.onDestroy();
    }

}

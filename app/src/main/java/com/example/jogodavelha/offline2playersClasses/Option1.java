package com.example.jogodavelha.offline2playersClasses;

import android.app.UiModeManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.example.jogodavelha.GameActivity;
import com.example.jogodavelha.Grid;
import com.example.jogodavelha.Player;
import com.example.jogodavelha.R;
import com.example.jogodavelha.SquareXorOView;

public class Option1 extends GameActivity {
    private static String letraGlobalAtual;
    private Bundle extra;
    private Player player1, player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        grid = new Grid();
        extra = getIntent().getExtras();
        this.letraGlobalAtual = extra.getString("simboloInicial","E");//'E' representa um erro
        player1 = (Player) extra.getSerializable("player1");
        player2 = (Player) extra.getSerializable("player2");
        if (player1.getUsedSymbol().equals(letraGlobalAtual)){
            this.textVezDoJogador.setText("É a vez de "+ player1.getName()+".");
        }
        else{
            this.textVezDoJogador.setText("É a vez de "+ player2.getName()+".");
        }

    }


    @Override
    public void insertXO(View view){
        grid.setCurrentImageId(view.getId());
        SquareXorOView image = findViewById(view.getId());

        String transitionName = view.getTransitionName();
        int row = Integer.parseInt(transitionName.substring(0,1));
        int column = Integer.parseInt(transitionName.substring(1,2));

        if (grid.getInRowAndColumn(row, column).equals("-") && !someoneWon){
            if (this.letraGlobalAtual.equals("x")){
                image.setImageResource(R.drawable.ic_x_letter_svg);
            }else if(this.letraGlobalAtual.equals("o")){
                image.setImageResource(R.drawable.ic_o_letter_svg);
            }
            grid.setInRowAndColumn(this.letraGlobalAtual, row, column);
            this.letraGlobalAtual = this.letraGlobalAtual.equals("x") ? "o" : "x";
            textVezDoJogador.setText("É a vez de "+  (this.letraGlobalAtual.equals(player1.getUsedSymbol()) ? player1.getName() :  player2.getName())+".");
            checkWinner(grid);//Sempre está na última linha do if
        }
    }


    @Override
    public void restartGame(View view){
        someoneWon = false;
        this.letraGlobalAtual = extra.getString("simboloInicial");
        if (player1.getUsedSymbol().equals(letraGlobalAtual)){
            textVezDoJogador.setText("É a vez de "+ player1.getName()+".");
        }
        else{
            textVezDoJogador.setText("É a vez de "+ player2.getName()+".");
        }

        if (grid.getAddedSimblesLength() > 0 && imagemVermelhas[0] != null){
            for(SquareXorOView imagemVermelha : imagemVermelhas){
                if(uiModeManager.getNightMode() == UiModeManager.MODE_NIGHT_YES){
                    imagemVermelha.setColorFilter(Color.WHITE);
                } else if(uiModeManager.getNightMode() == UiModeManager.MODE_NIGHT_NO){
                    imagemVermelha.setColorFilter(Color.BLACK);
                }
            }
        }
        zerarVezesPreenchidas();
    }

    @Override
    protected void informarVencedor(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Temos um vencedor(a)");
        dialog.setMessage("Meus parabéns, "+( ! player1.getUsedSymbol().equals(letraGlobalAtual) ? player1.getName() : player2.getName())+", pela tua vitória!!");
        dialog.setPositiveButton("Certo", null);
        dialog.create();
        dialog.show();
    }

    @Override
    protected void informarEmpate(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle("Deu velha!!");
        dialog.setMessage("Vocês devem ter o mesmo QI.");

        dialog.setPositiveButton("Certo", null);

        dialog.create();
        dialog.show();
    }

    public void zerarVezesPreenchidas (){

        for (int row = 0; row < 3; row++){
            for (int column = 0; column < 3; column++){
                camposTabela[row][column].setImageResource(R.drawable.vazio);
            }
        }
       grid.restart();
    }
}

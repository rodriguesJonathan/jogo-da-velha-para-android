package com.example.jogodavelha;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

public class Option1 extends GameActivity{
    private static String letraGlobalAtual;
    private Bundle extra;
    private Player player1, player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





        zerarVezesPreenchidas();
        extra = getIntent().getExtras();
        vezesPreenchidas = 0;
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
    public void inserirXO(View view){
        SquareXorOView image = findViewById(view.getId());

        if (image.getLetraAtual().equals("_") && !alguemGanhou){
            if (this.letraGlobalAtual.equals("x")){
                image.setImageResource(R.drawable.ic_x_letter_svg);
            }else if(this.letraGlobalAtual.equals("o")){
                image.setImageResource(R.drawable.ic_o_letter_svg);
            }
            image.setLetraAtual(this.letraGlobalAtual);
            this.vezesPreenchidas += 1;
            this.letraGlobalAtual = this.letraGlobalAtual.equals("x") ? "o" : "x";
            textVezDoJogador.setText("É a vez de "+  (this.letraGlobalAtual.equals(player1.getUsedSymbol()) ? player1.getName() :  player2.getName())+".");
            verificarVencedor(image);//Sempre está na última linha do if
        }
    }


    @Override
    public void reiniciarJogo (View view){
        alguemGanhou = false;
        this.letraGlobalAtual = extra.getString("simboloInicial");
        if (player1.getUsedSymbol().equals(letraGlobalAtual)){
            textVezDoJogador.setText("É a vez de "+ player1.getName()+".");
        }
        else{
            textVezDoJogador.setText("É a vez de "+ player2.getName()+".");
        }

        if (this.vezesPreenchidas > 0 && imagemVermelhas[0] != null){
            for(SquareXorOView imagemVermelha : imagemVermelhas){
                imagemVermelha.setColorFilter(Color.BLACK);
            }
        }
        zerarVezesPreenchidas();
    }

    @Override
    protected void informarVencedor(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Temos um vencedor(a)");
        dialog.setMessage("Meus parabéns, "+( ! player1.getUsedSymbol().equals(letraGlobalAtual) ? player1.getName() : player2.getName())+", pela tua vitória!!");
        dialog.setPositiveButton("Avançar", null);
        dialog.create();
        dialog.show();
    }

    @Override
    protected void informarEmpate(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle("Deu velha!!");
        dialog.setMessage("Vocês devem ter o mesmo QI.");

        dialog.setPositiveButton("Avançar", null);

        dialog.create();
        dialog.show();
    }

    public void zerarVezesPreenchidas (){

        for (SquareXorOView campo : camposTabela) {
            campo.setImageResource(R.drawable.vazio);
            campo.setLetraAtual("_");
        }
        vezesPreenchidas = 0;
    }
}

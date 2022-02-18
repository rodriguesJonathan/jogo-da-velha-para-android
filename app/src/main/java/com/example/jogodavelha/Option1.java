package com.example.jogodavelha;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

public class Option1 extends GameActivity{
    private static char letraGlobalAtual;
    private Bundle extra;
    private Player player1, player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





        zerarVezesPreenchidas();
        extra = getIntent().getExtras();
        vezesPreenchidas = 0;
        this.letraGlobalAtual = extra.getChar("simboloInicial",'E');//'E' representa um erro
        player1 = (Player) extra.getSerializable("player1");
        player2 = (Player) extra.getSerializable("player2");
        if (player1.getSimboloUsado() == letraGlobalAtual){
            this.textVezDoJogador.setText("É a vez de "+ player1.getNome()+".");
        }
        else{
            this.textVezDoJogador.setText("É a vez de "+ player2.getNome()+".");
        }

    }


    @Override
    public void inserirXO(View view){
        SquareXorOView image = findViewById(view.getId());

        if (image.getLetraAtual() == '_' && !alguemGanhou){
            if (this.letraGlobalAtual == 'x'){
                image.setImageResource(R.drawable.ic_x_letter_svg);
            }else if(this.letraGlobalAtual == 'o'){
                image.setImageResource(R.drawable.ic_o_letter_svg);
            }
            image.setLetraAtual(this.letraGlobalAtual);
            this.vezesPreenchidas += 1;
            this.letraGlobalAtual = this.letraGlobalAtual == 'x' ? 'o' : 'x';
            textVezDoJogador.setText("É a vez de "+  (this.letraGlobalAtual == player1.getSimboloUsado() ? player1.getNome() :  player2.getNome())+".");
            verificarVencedor(image);//Sempre está na última linha do if
        }
    }


    @Override
    public void reiniciarJogo (View view){
        alguemGanhou = false;
        this.letraGlobalAtual = extra.getChar("simboloInicial");
        if (player1.getSimboloUsado() == letraGlobalAtual){
            textVezDoJogador.setText("É a vez de "+ player1.getNome()+".");
        }
        else{
            textVezDoJogador.setText("É a vez de "+ player2.getNome()+".");
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
        dialog.setMessage("Meus parabéns, "+(player1.getSimboloUsado()!=letraGlobalAtual ? player1.getNome() : player2.getNome())+", pela tua vitória!!");
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
            campo.setLetraAtual('_');
        }
        vezesPreenchidas = 0;
    }
}

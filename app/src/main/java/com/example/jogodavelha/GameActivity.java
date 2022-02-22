package com.example.jogodavelha;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {
    protected SquareXorOView[] imagemVermelhas;
    protected boolean alguemGanhou;
    protected int vezesPreenchidas = 0;;


    protected TextView textVezDoJogador;



    protected SquareXorOView
            tabela11, tabela12, tabela13,
    tabela21, tabela22, tabela23,
    tabela31, tabela32, tabela33;
    protected SquareXorOView[]  camposTabela;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        tabela11 = findViewById(R.id.tabela11); tabela12 = findViewById(R.id.tabela12); tabela13 = findViewById(R.id.tabela13);
        tabela21 = findViewById(R.id.tabela21); tabela22 = findViewById(R.id.tabela22); tabela23 = findViewById(R.id.tabela23);
        tabela31 = findViewById(R.id.tabela31); tabela32 = findViewById(R.id.tabela32); tabela33 = findViewById(R.id.tabela33);
        camposTabela = new SquareXorOView[]{tabela11, tabela12, tabela13, tabela21,
                tabela22, tabela23, tabela31, tabela32, tabela33};
        textVezDoJogador = findViewById(R.id.textVezDoJogador);
    }


    public void inserirXO(View view){

    }

    public void reiniciarJogo (View view){

    }


    public void verificarVencedor(SquareXorOView obj) {
        imagemVermelhas = new SquareXorOView[3];
        if (obj.getId() == R.id.tabela11){//Linha 1 coluna 1 (ponta 1)
            if (coluna1EstaIgual()){
                imagemVermelhas[0] = tabela11;
                imagemVermelhas[1] = tabela21;
                imagemVermelhas[2] = tabela31;
            }else if(linha1EstaIgual()){
                imagemVermelhas[0] = tabela11;
                imagemVermelhas[1] = tabela12;
                imagemVermelhas[2] = tabela13;
            }else if(diagonal1EstaIgual()){
                imagemVermelhas[0] = tabela11;
                imagemVermelhas[1] = tabela22;
                imagemVermelhas[2] = tabela33;
            }
        }else if(obj.getId() == R.id.tabela12){//Linha 1 coluna 2
            if(coluna2EstaIgual()){
                imagemVermelhas[0] = tabela12;
                imagemVermelhas[1] = tabela22;
                imagemVermelhas[2] = tabela32;
            }else if(linha1EstaIgual()){
                imagemVermelhas[0] = tabela11;
                imagemVermelhas[1] = tabela12;
                imagemVermelhas[2] = tabela13;
            }
        }else if(obj.getId() == R.id.tabela13){//Linha 1 coluna 3 (ponta 2)
            if(linha1EstaIgual()) {
                imagemVermelhas[0] = tabela11;
                imagemVermelhas[1] = tabela12;
                imagemVermelhas[2] = tabela13;
            }else if(coluna3EstaIgual()){
                imagemVermelhas[0] = tabela13;
                imagemVermelhas[1] = tabela23;
                imagemVermelhas[2] = tabela33;
            }else if(diagonal2EstaIgual()){
                imagemVermelhas[0] = tabela13;
                imagemVermelhas[1] = tabela22;
                imagemVermelhas[2] = tabela31;
            }
        }else if(obj.getId() == R.id.tabela21) {//Linha 2 coluna 1
            if (coluna1EstaIgual()){
                imagemVermelhas[0] = tabela11;
                imagemVermelhas[1] = tabela21;
                imagemVermelhas[2] = tabela31;
            }else if(linha2EstaIgual()){
                imagemVermelhas[0] = tabela21;
                imagemVermelhas[1] = tabela22;
                imagemVermelhas[2] = tabela23;
            }
        }else if(obj.getId() == R.id.tabela22) {//Linha 2 coluna 2 (meio)
            if(linha2EstaIgual()){
                imagemVermelhas[0] = tabela21;
                imagemVermelhas[1] = tabela22;
                imagemVermelhas[2] = tabela23;
            }else if(coluna2EstaIgual()){
                imagemVermelhas[0] = tabela12;
                imagemVermelhas[1] = tabela22;
                imagemVermelhas[2] = tabela32;
            }else if(diagonal1EstaIgual()){
                imagemVermelhas[0] = tabela11;
                imagemVermelhas[1] = tabela22;
                imagemVermelhas[2] = tabela33;
            }else if(diagonal2EstaIgual()){
                imagemVermelhas[0] = tabela13;
                imagemVermelhas[1] = tabela22;
                imagemVermelhas[2] = tabela31;
            }
        }else if(obj.getId() == R.id.tabela23) {//Linha 2 coluna 3
                if(linha2EstaIgual()) {
                    imagemVermelhas[0] = tabela21;
                    imagemVermelhas[1] = tabela22;
                    imagemVermelhas[2] = tabela23;
                }else if(coluna3EstaIgual()){
                    imagemVermelhas[0] = tabela13;
                    imagemVermelhas[1] = tabela23;
                    imagemVermelhas[2] = tabela33;
                }
        }else if(obj.getId() == R.id.tabela31) {//Linha 3 coluna 1 (ponta 3)
                if (coluna1EstaIgual()){
                    imagemVermelhas[0] = tabela11;
                    imagemVermelhas[1] = tabela21;
                    imagemVermelhas[2] = tabela31;
                }else if(linha3EstaIgual()){
                    imagemVermelhas[0] = tabela31;
                    imagemVermelhas[1] = tabela32;
                    imagemVermelhas[2] = tabela33;
                }else if(diagonal2EstaIgual()){
                    imagemVermelhas[0] = tabela13;
                    imagemVermelhas[1] = tabela22;
                    imagemVermelhas[2] = tabela31;
                }
        }else if(obj.getId() == R.id.tabela32) {//Linha 3 coluna 2
                if(linha3EstaIgual()){
                    imagemVermelhas[0] = tabela31;
                    imagemVermelhas[1] = tabela32;
                    imagemVermelhas[2] = tabela33;
                }else if(coluna2EstaIgual()){
                    imagemVermelhas[0] = tabela12;
                    imagemVermelhas[1] = tabela22;
                    imagemVermelhas[2] = tabela32;
                }
        }else if(obj.getId() == R.id.tabela33) {//Linha 3 coluna 3 (ponta 4)
                if(linha3EstaIgual()){
                    imagemVermelhas[0] = tabela31;
                    imagemVermelhas[1] = tabela32;
                    imagemVermelhas[2] = tabela33;
                }else if(coluna3EstaIgual()){
                    imagemVermelhas[0] = tabela13;
                    imagemVermelhas[1] = tabela23;
                    imagemVermelhas[2] = tabela33;
                }else if(diagonal1EstaIgual()){
                    imagemVermelhas[0] = tabela11;
                    imagemVermelhas[1] = tabela22;
                    imagemVermelhas[2] = tabela33;
                }
        }

        if (imagemVermelhas[0] != null){
            textVezDoJogador.setText("Opa! Temos um vencedor");
            alguemGanhou = true;
            for(SquareXorOView imagemVermelha : imagemVermelhas){
               imagemVermelha.setColorFilter(Color.RED);
            }

            informarVencedor();
        }
        if (!alguemGanhou && this.vezesPreenchidas == 9){

            informarEmpate();

        }
    }

    protected void informarVencedor(){

    }

    protected void informarEmpate(){

    }


    public boolean coluna1EstaIgual(){
        String letra11 = this.tabela11.getLetraAtual(), letra21 = this.tabela21.getLetraAtual(), letra31 = this.tabela31.getLetraAtual();
        return letra11.equals(letra21) && letra11.equals(letra31);
    }
    public boolean coluna2EstaIgual(){
        String letra12 = this.tabela12.getLetraAtual(), letra22 = this.tabela22.getLetraAtual(), letra32 = this.tabela32.getLetraAtual();
        return letra12.equals(letra22) && letra12.equals(letra32);
    }
    public boolean coluna3EstaIgual(){
        String letra13 = this.tabela13.getLetraAtual(), letra23 = this.tabela23.getLetraAtual(), letra33 = this.tabela33.getLetraAtual();
        return letra13.equals(letra23) && letra13.equals(letra33);
    }
    public boolean linha1EstaIgual(){
        String letra11 = this.tabela11.getLetraAtual(), letra12 = this.tabela12.getLetraAtual(), letra13 = this.tabela13.getLetraAtual();
        return letra11.equals(letra12) && letra11.equals(letra13);
    }
    public boolean linha2EstaIgual(){
        String letra21 = this.tabela21.getLetraAtual(), letra22 = this.tabela22.getLetraAtual(), letra23 = this.tabela23.getLetraAtual();
        return letra21.equals(letra22) && letra21.equals(letra23);
    }
    public boolean linha3EstaIgual(){
        String letra31 = this.tabela31.getLetraAtual(), letra32 = this.tabela32.getLetraAtual(), letra33 = this.tabela33.getLetraAtual();
        return letra31.equals(letra32) && letra31.equals(letra33);
    }
    public boolean diagonal1EstaIgual(){
        String letra11 = this.tabela11.getLetraAtual(), letra22 = this.tabela22.getLetraAtual(), letra33 = this.tabela33.getLetraAtual();
        return letra11.equals(letra22) && letra11.equals(letra33);
    }
    public boolean diagonal2EstaIgual(){
        String letra13 = this.tabela13.getLetraAtual(), letra22 = this.tabela22.getLetraAtual(), letra31 = this.tabela31.getLetraAtual();
        return letra13.equals(letra22) && letra13.equals(letra31);
    }


}

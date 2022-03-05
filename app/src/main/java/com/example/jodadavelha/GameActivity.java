package com.example.jodadavelha;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {
    protected SquareXorOView[] imagemVermelhas;
    protected boolean someoneWon = false;
    protected Grid grid;

    protected TextView textVezDoJogador;



    protected SquareXorOView
            tabela11, tabela12, tabela13,
    tabela21, tabela22, tabela23,
    tabela31, tabela32, tabela33;
    protected SquareXorOView[][]  camposTabela;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        tabela11 = findViewById(R.id.tabela11); tabela12 = findViewById(R.id.tabela12); tabela13 = findViewById(R.id.tabela13);
        tabela21 = findViewById(R.id.tabela21); tabela22 = findViewById(R.id.tabela22); tabela23 = findViewById(R.id.tabela23);
        tabela31 = findViewById(R.id.tabela31); tabela32 = findViewById(R.id.tabela32); tabela33 = findViewById(R.id.tabela33);
        camposTabela = new SquareXorOView[][]{{tabela11, tabela12, tabela13},
                                              {tabela21, tabela22, tabela23},
                                              {tabela31, tabela32, tabela33}};
        textVezDoJogador = findViewById(R.id.textVezDoJogador);
    }


    public void insertXO(View view){

    }

    public void restartGame(View view){

    }


    public void checkWinner(Grid grid) {
        imagemVermelhas = new SquareXorOView[3];
        int currentImageId = grid.getCurrentImageId();

        if (currentImageId == R.id.tabela11){//Linha 1 coluna 1 (ponta 1)
            if (column1IsEven()){
                imagemVermelhas[0] = tabela11;
                imagemVermelhas[1] = tabela21;
                imagemVermelhas[2] = tabela31;
            }else if(line1IsEven()){
                imagemVermelhas[0] = tabela11;
                imagemVermelhas[1] = tabela12;
                imagemVermelhas[2] = tabela13;
            }else if(diagonal1IsEven()){
                imagemVermelhas[0] = tabela11;
                imagemVermelhas[1] = tabela22;
                imagemVermelhas[2] = tabela33;
            }
        }else if(currentImageId == R.id.tabela12){//Linha 1 coluna 2
            if(column2IsEven()){
                imagemVermelhas[0] = tabela12;
                imagemVermelhas[1] = tabela22;
                imagemVermelhas[2] = tabela32;
            }else if(line1IsEven()){
                imagemVermelhas[0] = tabela11;
                imagemVermelhas[1] = tabela12;
                imagemVermelhas[2] = tabela13;
            }
        }else if(currentImageId == R.id.tabela13){//Linha 1 coluna 3 (ponta 2)
            if(line1IsEven()) {
                imagemVermelhas[0] = tabela11;
                imagemVermelhas[1] = tabela12;
                imagemVermelhas[2] = tabela13;
            }else if(column3IsEven()){
                imagemVermelhas[0] = tabela13;
                imagemVermelhas[1] = tabela23;
                imagemVermelhas[2] = tabela33;
            }else if(diagonal2IsEven()){
                imagemVermelhas[0] = tabela13;
                imagemVermelhas[1] = tabela22;
                imagemVermelhas[2] = tabela31;
            }
        }else if(currentImageId == R.id.tabela21) {//Linha 2 coluna 1
            if (column1IsEven()){
                imagemVermelhas[0] = tabela11;
                imagemVermelhas[1] = tabela21;
                imagemVermelhas[2] = tabela31;
            }else if(line2IsEven()){
                imagemVermelhas[0] = tabela21;
                imagemVermelhas[1] = tabela22;
                imagemVermelhas[2] = tabela23;
            }
        }else if(currentImageId == R.id.tabela22) {//Linha 2 coluna 2 (meio)
            if(line2IsEven()){
                imagemVermelhas[0] = tabela21;
                imagemVermelhas[1] = tabela22;
                imagemVermelhas[2] = tabela23;
            }else if(column2IsEven()){
                imagemVermelhas[0] = tabela12;
                imagemVermelhas[1] = tabela22;
                imagemVermelhas[2] = tabela32;
            }else if(diagonal1IsEven()){
                imagemVermelhas[0] = tabela11;
                imagemVermelhas[1] = tabela22;
                imagemVermelhas[2] = tabela33;
            }else if(diagonal2IsEven()){
                imagemVermelhas[0] = tabela13;
                imagemVermelhas[1] = tabela22;
                imagemVermelhas[2] = tabela31;
            }
        }else if(currentImageId == R.id.tabela23) {//Linha 2 coluna 3
                if(line2IsEven()) {
                    imagemVermelhas[0] = tabela21;
                    imagemVermelhas[1] = tabela22;
                    imagemVermelhas[2] = tabela23;
                }else if(column3IsEven()){
                    imagemVermelhas[0] = tabela13;
                    imagemVermelhas[1] = tabela23;
                    imagemVermelhas[2] = tabela33;
                }
        }else if(currentImageId == R.id.tabela31) {//Linha 3 coluna 1 (ponta 3)
                if (column1IsEven()){
                    imagemVermelhas[0] = tabela11;
                    imagemVermelhas[1] = tabela21;
                    imagemVermelhas[2] = tabela31;
                }else if(line3IsEven()){
                    imagemVermelhas[0] = tabela31;
                    imagemVermelhas[1] = tabela32;
                    imagemVermelhas[2] = tabela33;
                }else if(diagonal2IsEven()){
                    imagemVermelhas[0] = tabela13;
                    imagemVermelhas[1] = tabela22;
                    imagemVermelhas[2] = tabela31;
                }
        }else if(currentImageId == R.id.tabela32) {//Linha 3 coluna 2
                if(line3IsEven()){
                    imagemVermelhas[0] = tabela31;
                    imagemVermelhas[1] = tabela32;
                    imagemVermelhas[2] = tabela33;
                }else if(column2IsEven()){
                    imagemVermelhas[0] = tabela12;
                    imagemVermelhas[1] = tabela22;
                    imagemVermelhas[2] = tabela32;
                }
        }else if(currentImageId == R.id.tabela33) {//Linha 3 coluna 3 (ponta 4)
                if(line3IsEven()){
                    imagemVermelhas[0] = tabela31;
                    imagemVermelhas[1] = tabela32;
                    imagemVermelhas[2] = tabela33;
                }else if(column3IsEven()){
                    imagemVermelhas[0] = tabela13;
                    imagemVermelhas[1] = tabela23;
                    imagemVermelhas[2] = tabela33;
                }else if(diagonal1IsEven()){
                    imagemVermelhas[0] = tabela11;
                    imagemVermelhas[1] = tabela22;
                    imagemVermelhas[2] = tabela33;
                }
        }

        if (imagemVermelhas[0] != null){
            textVezDoJogador.setText("Opa! Temos um vencedor");
            someoneWon = true;
            for(SquareXorOView imagemVermelha : imagemVermelhas){
               imagemVermelha.setColorFilter(Color.RED);
            }

            informarVencedor();
        }
        if (!someoneWon && grid.getAddedSimblesLength() == 9){
            someoneWon = true;
            informarEmpate();

        }
    }

    protected void informarVencedor(){

    }

    protected void informarEmpate(){

    }


    public boolean column1IsEven(){
        String grid11 = grid.getRow1column1(), grid21 = grid.getRow2column1(), grid31 = grid.getRow3column1();
        return !grid11.equals("-") && grid11.equals(grid21) && grid11.equals(grid31);
    }
    public boolean column2IsEven(){
        String grid12 = grid.getRow1column2(), grid22 = grid.getRow2column2(), grid32 = grid.getRow3column2();
        return !grid12.equals("-") && grid12.equals(grid22) && grid12.equals(grid32);
    }
    public boolean column3IsEven(){
        String grid13 = grid.getRow1column3(), grid23 = grid.getRow2column3(), grid33 = grid.getRow3column3();
        return !grid13.equals("-") && grid13.equals(grid23) && grid13.equals(grid33);
    }
    public boolean line1IsEven(){
        String grid11 = grid.getRow1column1(), grid12 = grid.getRow1column2(), grid13 = grid.getRow1column3();
        return !grid11.equals("-") && grid11.equals(grid12) && grid11.equals(grid13);
    }
    public boolean line2IsEven(){
        String grid21 = grid.getRow2column1(), grid22 = grid.getRow2column2(), grid23 = grid.getRow2column3();
        return !grid21.equals("-") && grid21.equals(grid22) && grid21.equals(grid23);
    }
    public boolean line3IsEven(){
        String grid31 = grid.getRow3column1(), grid32 = grid.getRow3column2(), grid33 = grid.getRow3column3();
        return !grid31.equals("-") && grid31.equals(grid32) && grid31.equals(grid33);
    }
    public boolean diagonal1IsEven(){
        String grid11 = grid.getRow1column1(), grid22 = grid.getRow2column2(), grid33 = grid.getRow3column3();
        return !grid11.equals("-") && grid11.equals(grid22) && grid11.equals(grid33);
    }
    public boolean diagonal2IsEven(){
        String grid13 = grid.getRow1column3(), grid22 = grid.getRow2column2(), grid31 = grid.getRow3column1();
        return !grid13.equals("-") && grid13.equals(grid22) && grid13.equals(grid31);
    }


}

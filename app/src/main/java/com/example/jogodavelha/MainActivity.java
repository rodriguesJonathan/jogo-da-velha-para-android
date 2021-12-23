package com.example.jogodavelha;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private char letraGlobalAtual;
    private SquareXorOView tabela11, tabela12, tabela13, tabela21, tabela22, tabela23, tabela31, tabela32, tabela33;
    SquareXorOView[] imagemVermelhas;
    private boolean ganhou;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        letraGlobalAtual = 'x';
        tabela11 = findViewById(R.id.tabela11); tabela12 = findViewById(R.id.tabela12); tabela13 = findViewById(R.id.tabela13);
        tabela21 = findViewById(R.id.tabela21); tabela22 = findViewById(R.id.tabela22); tabela23 = findViewById(R.id.tabela23);
        tabela31 = findViewById(R.id.tabela31); tabela32 = findViewById(R.id.tabela32); tabela33 = findViewById(R.id.tabela33);
        ganhou = false;
    }

    public void inserirXO(View view){
        SquareXorOView image = findViewById(view.getId());
        if (image.getLetraAtual() == '_' && !ganhou){
            image.setLetraAtual(this.letraGlobalAtual);
            SquareXorOView.vezesPreenchida += 1;
            if (this.letraGlobalAtual == 'x' && !ganhou){
                image.setImageResource(R.drawable.ic_x_letter_svg);
            }else if(this.letraGlobalAtual == 'o' && !ganhou){
                image.setImageResource(R.drawable.ic_o_letter_svg);
            }
            verificarVencedor(image);
            this.letraGlobalAtual = this.letraGlobalAtual == 'x' ? 'o' : 'x';
        }
    }

    public void reiniciarJogo (View view){

        SquareXorOView[] camposTabela = {this.tabela11, this.tabela12, this.tabela13, this.tabela21,
                this.tabela22, this.tabela23, this.tabela31, this.tabela32, this.tabela33};
        for (SquareXorOView campo : camposTabela) {
            campo.setImageResource(R.drawable.vazio);
            campo.setLetraAtual('_');
        }
        ganhou = false;
        this.letraGlobalAtual = 'x';


        if (SquareXorOView.vezesPreenchida > 0 && imagemVermelhas[0] != null){
            for(SquareXorOView imagemVermelha : imagemVermelhas){
                imagemVermelha.setColorFilter(Color.BLACK);
            }
        }
        SquareXorOView.vezesPreenchida = 0;
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
            ganhou = true;
            for(SquareXorOView imagemVermelha : imagemVermelhas){
               imagemVermelha.setColorFilter(Color.RED);
            }
        }
        if (SquareXorOView.vezesPreenchida == 9){

            AlertDialog.Builder dialog = new AlertDialog.Builder(this);

            dialog.setTitle("Deu velha!!");
            dialog.setMessage("Vocês devem ter o mesmo QI.");

            dialog.setPositiveButton("Avançar", null);

            dialog.create();
            dialog.show();
        }
    }

    public boolean coluna1EstaIgual(){
        char letra11 = tabela11.getLetraAtual(), letra21 = tabela21.getLetraAtual(), letra31 = tabela31.getLetraAtual();
        return letra11 == letra21 && letra11 == letra31;
    }
    public boolean coluna2EstaIgual(){
        char letra12 = tabela12.getLetraAtual(), letra22 = tabela22.getLetraAtual(), letra32 = tabela32.getLetraAtual();
        return letra12 == letra22 && letra12 == letra32;
    }
    public boolean coluna3EstaIgual(){
        char letra13 = tabela13.getLetraAtual(), letra23 = tabela23.getLetraAtual(), letra33 = tabela33.getLetraAtual();
        return letra13 == letra23 && letra13 == letra33;
    }
    public boolean linha1EstaIgual(){
        char letra11 = tabela11.getLetraAtual(), letra12 = tabela12.getLetraAtual(), letra13 = tabela13.getLetraAtual();
        return letra11 == letra12 && letra11 == letra13;
    }
    public boolean linha2EstaIgual(){
        char letra21 = tabela21.getLetraAtual(), letra22 = tabela22.getLetraAtual(), letra23 = tabela23.getLetraAtual();
        return letra21 == letra22 && letra21 == letra23;
    }
    public boolean linha3EstaIgual(){
        char letra31 = tabela31.getLetraAtual(), letra32 = tabela32.getLetraAtual(), letra33 = tabela33.getLetraAtual();
        return letra31 == letra32 && letra31 == letra33;
    }
    public boolean diagonal1EstaIgual(){
        char letra11 = tabela11.getLetraAtual(), letra22 = tabela22.getLetraAtual(), letra33 = tabela33.getLetraAtual();
        return letra11 == letra22 && letra11 == letra33;
    }
    public boolean diagonal2EstaIgual(){
        char letra13 = tabela13.getLetraAtual(), letra22 = tabela22.getLetraAtual(), letra31 = tabela31.getLetraAtual();
        return letra13 == letra22 && letra13 == letra31;
    }


}

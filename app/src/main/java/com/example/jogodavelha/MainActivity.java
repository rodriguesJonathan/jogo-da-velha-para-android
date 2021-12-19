package com.example.jogodavelha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private char letraGlobalAtual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.letraGlobalAtual = 'x';
    }

    public void inserirXO(View view){
        SquareXorOView image = findViewById(view.getId());
        if (image.getLetraAtual() == '_'){
            image.setLetraAtual(this.letraGlobalAtual);
            if (this.letraGlobalAtual == 'x'){
                image.setImageResource(R.drawable.letter_x);
            }else{
                image.setImageResource(R.drawable.letter_o);
            }
            this.letraGlobalAtual = this.letraGlobalAtual == 'x' ? 'o' : 'x';
        }
    }

    public void reiniciarJogo (View view){
        SquareXorOView tabela11 = findViewById(R.id.tabela11);
        SquareXorOView tabela12 = findViewById(R.id.tabela12);
        SquareXorOView tabela13 = findViewById(R.id.tabela13);
        SquareXorOView tabela21 = findViewById(R.id.tabela21);
        SquareXorOView tabela22 = findViewById(R.id.tabela22);
        SquareXorOView tabela23 = findViewById(R.id.tabela23);
        SquareXorOView tabela31 = findViewById(R.id.tabela31);
        SquareXorOView tabela32 = findViewById(R.id.tabela32);
        SquareXorOView tabela33 = findViewById(R.id.tabela33);

        SquareXorOView camposTabela[] = {tabela11, tabela12, tabela13, tabela21, tabela22, tabela23, tabela31, tabela32, tabela33};
        for (SquareXorOView campo : camposTabela) {
            campo.setImageResource(R.drawable.vazio);
            campo.setLetraAtual('_');
        }

        this.letraGlobalAtual = 'x';
    }

}

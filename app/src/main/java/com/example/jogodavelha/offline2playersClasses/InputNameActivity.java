package com.example.jogodavelha.offline2playersClasses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jogodavelha.Player;
import com.example.jogodavelha.R;

public class InputNameActivity extends AppCompatActivity {
    private EditText editNomeJogador1, editNomeJogador2;
    private TextView textoJogador1, textoJogador2;
    Switch switchTrocaSimbolo;
    Switch switchSimboloInicio;
    char letraGlobalAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_name);
        editNomeJogador1 = findViewById(R.id.editJogador1);
        editNomeJogador2 = findViewById(R.id.editJogador2);
        textoJogador1 = findViewById(R.id.textoJogador1);
        textoJogador2 = findViewById(R.id.textoJogador2);
        switchTrocaSimbolo = findViewById(R.id.switchTrocaSimbolo);
        switchSimboloInicio = findViewById(R.id.switchSimboloInicio);
        letraGlobalAtual = 'x';
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void avancar(View view){
        String nomeJogador1 = editNomeJogador1.getText().toString();
        String nomeJogador2 = editNomeJogador2.getText().toString();




        boolean nomeJogador1estaVazio = nomeJogador1.equals(""), nomeJogador2estaVazio = nomeJogador2.equals("");
        if (nomeJogador1estaVazio && nomeJogador2estaVazio){
            Toast.makeText(getApplicationContext(), "Informe o nome de todos os jogadores.", Toast.LENGTH_LONG).show();
        }
        else if (nomeJogador1estaVazio || nomeJogador2estaVazio){
            int numeroDoJogador = (nomeJogador1estaVazio ? 1 : 2);
            Toast.makeText(getApplicationContext(), "Informe o nome do "+numeroDoJogador+"° jogador.", Toast.LENGTH_LONG).show();

        }else{
            Intent intent = new Intent(getApplicationContext(), Option1.class);

            Player player1 = new Player(editNomeJogador1.getText().toString());
            Player player2 = new Player(editNomeJogador2.getText().toString());


            if (switchTrocaSimbolo.isChecked()){
                player1.setUsedSymbol("o");
                player2.setUsedSymbol("x");
            }else{
                player1.setUsedSymbol("x");
                player2.setUsedSymbol("o");
            }
            if (switchSimboloInicio.isChecked()){
                intent.putExtra("simboloInicial", "o");
            }else{
                intent.putExtra("simboloInicial", "x");
            }
            intent.putExtra("player1", player1);
            intent.putExtra("player2", player2);


            startActivity(intent);
        }

    }

    public void trocarSimbolo(View view){
        if (switchTrocaSimbolo.isChecked()){
            textoJogador2.setText("O Player 2 será: X");
            textoJogador1.setText("O Player 1 será: O");
        }else{
            textoJogador1.setText("O Player 1 será: X");
            textoJogador2.setText("O Player 2 será: O");
        }
    }
}
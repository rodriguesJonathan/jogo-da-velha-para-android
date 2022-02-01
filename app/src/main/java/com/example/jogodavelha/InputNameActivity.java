package com.example.jogodavelha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class InputNameActivity extends AppCompatActivity {
    private EditText editNomeJogador1, editNomeJogador2;
    private TextView textoJogador1, textoJogador2;
    public static final String ARQUIVO_PREFERENCIA = "arquivo preferencia";
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
            Intent intent = new Intent(getApplicationContext(),Option1.class);

            Jogador jogador1 = new Jogador(editNomeJogador1.getText().toString());
            Jogador jogador2 = new Jogador(editNomeJogador2.getText().toString());


            if (switchTrocaSimbolo.isChecked()){
                jogador1.setSimboloUsado('o');
                jogador2.setSimboloUsado('x');
            }else{
                jogador1.setSimboloUsado('x');
                jogador2.setSimboloUsado('o');
            }
            if (switchSimboloInicio.isChecked()){
                intent.putExtra("simboloInicial", 'o');
            }else{
                intent.putExtra("simboloInicial", 'x');
            }
            intent.putExtra("jogador1", jogador1);
            intent.putExtra("jogador2", jogador2);


            startActivity(intent);
        }

    }

    public void trocarSimbolo(View view){
        if (switchTrocaSimbolo.isChecked()){
            textoJogador2.setText("O Jogador 2 será: X");
            textoJogador1.setText("O Jogador 1 será: O");
        }else{
            textoJogador1.setText("O Jogador 1 será: X");
            textoJogador2.setText("O Jogador 2 será: O");
        }
    }
}
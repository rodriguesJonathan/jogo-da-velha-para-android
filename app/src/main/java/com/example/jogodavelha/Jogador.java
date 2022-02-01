package com.example.jogodavelha;

import java.io.Serializable;

public class Jogador implements Serializable {
    private String nome;
    private char simboloUsado;

    Jogador(String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public char getSimboloUsado() {
        return simboloUsado;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSimboloUsado(char simboloUsado) {
        this.simboloUsado = simboloUsado;
    }
}

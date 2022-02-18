package com.example.jogodavelha;

import java.io.Serializable;

public class Player implements Serializable {
    private String nome;
    private char simboloUsado;

    Player(String nome){
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

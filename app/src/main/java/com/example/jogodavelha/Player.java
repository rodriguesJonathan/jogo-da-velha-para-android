package com.example.jogodavelha;

import java.io.Serializable;

public class Player implements Serializable {
    private String name = "-";
    private String usedSymbol = "-";

    Player(){    }
    Player(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getUsedSymbol() {
        return usedSymbol;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsedSymbol(String usedSymbol) {
        this.usedSymbol = usedSymbol;
    }
}

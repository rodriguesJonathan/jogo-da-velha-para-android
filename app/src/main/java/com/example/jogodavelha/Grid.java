package com.example.jogodavelha;

public class Grid extends Object {

    private String firstPlayer = "Não definido";
    private String currentPlayer = "Não definido";
    private String row1column1 = " ";
    private String row1column2 = " ";
    private String row1column3 = " ";
    private String row2column1 = " ";
    private String row2column2 = " ";
    private String row2column3 = " ";
    private String row3column1 = " ";
    private String row3column2 = " ";
    private String row3column3 = " ";

    Grid(){    }

    public String getFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(String firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public String getRow1column1() {
        return row1column1;
    }

    public void setRow1column1(String row1column1) {
        this.row1column1 = row1column1;
    }

    public String getRow1column2() {
        return row1column2;
    }

    public void setRow1column2(String row1column2) {
        this.row1column2 = row1column2;
    }

    public String getRow1column3() {
        return row1column3;
    }

    public void setRow1column3(String row1column3) {
        this.row1column3 = row1column3;
    }

    public String getRow2column1() {
        return row2column1;
    }

    public void setRow2column1(String row2column1) {
        this.row2column1 = row2column1;
    }

    public String getRow2column2() {
        return row2column2;
    }

    public void setRow2column2(String row2column2) {
        this.row2column2 = row2column2;
    }

    public String getRow2column3() {
        return row2column3;
    }

    public void setRow2column3(String row2column3) {
        this.row2column3 = row2column3;
    }

    public String getRow3column1() {
        return row3column1;
    }

    public void setRow3column1(String row3column1) {
        this.row3column1 = row3column1;
    }

    public String getRow3column2() {
        return row3column2;
    }

    public void setRow3column2(String row3column2) {
        this.row3column2 = row3column2;
    }

    public String getRow3column3() {
        return row3column3;
    }

    public void setRow3column3(String row3column3) {
        this.row3column3 = row3column3;
    }
}

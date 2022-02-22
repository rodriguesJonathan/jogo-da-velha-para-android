package com.example.jogodavelha;

public class Grid extends Object {

    private String firstPlayerCode = "-";
    private String currentPlayerCode = "-";
    private String[][] grid =   {{"-", "-", "-"},
                                 {"-", "-", "-"},
                                 {"-", "-", "-"}};

    Grid(){    }

    public String getFirstPlayerCode() {
        return firstPlayerCode;
    }

    public void setFirstPlayerCode(String firstPlayerCode) {
        this.firstPlayerCode = firstPlayerCode;
    }

    public String getCurrentPlayerCode() {
        return currentPlayerCode;
    }

    public void setCurrentPlayerCode(String currentPlayerCode) {
        this.currentPlayerCode = currentPlayerCode;
    }

    public String getRow1column1() {
        return grid[0][0];
    }

    public void setRow1column1(String row1column1) {
        this.grid[0][0] = row1column1;
    }

    public String getRow1column2() {
        return grid[0][1];
    }

    public void setRow1column2(String row1column2) {
        this.grid[0][1] = row1column2;
    }

    public String getRow1column3() {
        return grid[0][2];
    }

    public void setRow1column3(String row1column3) {
        this.grid[0][2] = row1column3;
    }

    public String getRow2column1() {
        return grid[1][0];
    }

    public void setRow2column1(String row2column1) {
        this.grid[1][0] = row2column1;
    }

    public String getRow2column2() {
        return grid[1][1];
    }

    public void setRow2column2(String row2column2) {
        this.grid[1][1] = row2column2;
    }

    public String getRow2column3() {
        return grid[1][2];
    }

    public void setRow2column3(String row2column3) {
        this.grid[1][2] = row2column3;
    }

    public String getRow3column1() {
        return grid[2][0];
    }

    public void setRow3column1(String row3column1) {
        this.grid[2][0] = row3column1;
    }

    public String getRow3column2() {
        return grid[2][1];
    }

    public void setRow3column2(String row3column2) {
        this.grid[2][1] = row3column2;
    }

    public String getRow3column3() {
        return grid[2][2];
    }

    public void setRow3column3(String row3column3) {
        this.grid[2][2] = row3column3;
    }

    public void setInRowAndColumn(String simbol, int row, int column){
        this.grid[row-1][column-1] = simbol;
    }
}

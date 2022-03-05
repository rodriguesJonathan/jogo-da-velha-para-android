package com.example.jogodavelha;

import com.example.jodadavelha.R;

public class Grid extends Object {
    private int addedSimblesLength = 0;
    private int currentImageId = 0;
    private String firstPlayerCode = "-";
    private String currentPlayerCode = "-";
    private String[][] grid =   {{"-", "-", "-"},
                                 {"-", "-", "-"},
                                 {"-", "-", "-"}};

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

    public String getRow1column2() {
        return grid[0][1];
    }

    public String getRow1column3() {
        return grid[0][2];
    }

    public String getRow2column1() {
        return grid[1][0];
    }

    public String getRow2column2() {
        return grid[1][1];
    }

    public String getRow2column3() {
        return grid[1][2];
    }

    public String getRow3column1() {
        return grid[2][0];
    }

    public String getRow3column2() {
        return grid[2][1];
    }

    public String getRow3column3() {
        return grid[2][2];
    }

    public void setInRowAndColumn(String simbol, int row, int column){
        this.grid[row-1][column-1] = simbol;
        addedSimblesLength += 1;
    }

    public String getInRowAndColumn(int row, int column){
        return grid[row-1][column-1];
    }

    public int getCurrentImageId() {
        return currentImageId;
    }

    public void setCurrentImageId(int currentImageId) {
        this.currentImageId = currentImageId;
    }

    public void restart(){
        for (int row = 1; row <= 3; row++){
            for (int column = 1; column <= 3; column++){
                setInRowAndColumn("-", row, column);
            }
        }
        addedSimblesLength = 0;
        currentPlayerCode = firstPlayerCode;
    }

    public int getAddedSimblesLength() {
        return addedSimblesLength;
    }

    public void setRow1column1(String simbol) {
        grid[0][0] = simbol;
    }

    public void setRow1column2(String simbol) {
        grid[0][1] = simbol;
    }

    public void setRow1column3(String simbol) {
        grid[0][2] = simbol;
    }

    public void setRow2column1(String simbol) {
        grid[1][0] = simbol;
    }

    public void setRow2column2(String simbol) {
        grid[1][1] = simbol;
    }

    public void setRow2column3(String simbol) {
        grid[1][2] = simbol;
    }

    public void setRow3column1(String simbol) {
        grid[2][0] = simbol;
    }

    public void setRow3column2(String simbol) {
        grid[2][1] = simbol;
    }

    public void setRow3column3(String simbol) {
        grid[2][2] = simbol;
    }

}

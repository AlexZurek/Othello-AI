package com.othello;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class Board {
    private int width;
    private int height;
    @SerializedName("max-index")
    private int maxIndex;
    private String[] squares;

    public Board(int width, int height, int maxIndex, String[] squares) {
        this.width = width;
        this.height = height;
        this.maxIndex = maxIndex;
        this.squares = squares;
    }

    //region Getters and Setters
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getMaxIndex() {
        return maxIndex;
    }

    public void setMaxIndex(int maxIndex) {
        this.maxIndex = maxIndex;
    }

    public String[] getSquares() {
        return squares;
    }

    public void setSquares(String[] squares) {
        this.squares = squares;
    }
    //endregion

    /**
     * Override of the ToString method for the Board Object.
     * @return String representation of the board
     */
    @Override
    public String toString() {
        String[] squaresFormatted = getSquares();
        for (int i = getWidth(); i < this.getMaxIndex(); i += getWidth()){
            squaresFormatted[i] = "\n\t\t " + squaresFormatted[i];
        }
        return "Board {" +
                "\n\twidth    = " + getWidth() +
                "\n\theight   = " + getHeight() +
                "\n\tmaxIndex = " + getMaxIndex() +
                "\n\tsquares  = \n\t\t" + Arrays.toString(squaresFormatted) +
                '}';
    }
}

package com.othello;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class Board {
    public int width;
    public int height;
    @SerializedName("max-index")
    public int maxIndex;
    public String[] squares;

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
        String[] squaresFormatted = this.squares;
        for (int i = this.width; i < this.getMaxIndex(); i+=width){
            squaresFormatted[i] = "\n\t\t " + squaresFormatted[i];
        }
        return "Board {" +
                "\n\twidth    = " + width +
                "\n\theight   = " + height +
                "\n\tmaxIndex = " + maxIndex +
                "\n\tsquares  = \n\t\t" + Arrays.toString(squaresFormatted) +
                '}';
    }
}

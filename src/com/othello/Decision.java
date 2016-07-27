package com.othello;

/**
 * Class that is responsible for making some serious decisions.
 */
public class Decision {
    public int maxIndex;
    public Board board;
    public String player;
    public String enemy;

    /**
     * Constructor for Decision class
     * @param info info from command line arguments
     * @param board the board object that represents the board state for the given turn
     */
    public Decision(TurnInfo info, Board board) {
        this.maxIndex = board.getMaxIndex();
        this.player = info.getPlayer();
        this.enemy = player.equals("w") ? "b" : "w";
    }

    //region Getters and Setters
    public int getMaxIndex() {
        return maxIndex;
    }

    public void setMaxIndex(int maxIndex) {
        this.maxIndex = maxIndex;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getEnemy() {
        return enemy;
    }

    public void setEnemy(String enemy) {
        this.enemy = enemy;
    }
    //endregion

    public int DecideMove(){
        for (int i = 0; i <= this.getMaxIndex(); i++){
            if (isValidMove(i)){
                return i;
            }
        }
        return 0;
    }

    /**
     * Determines if a given position on the board is a valid move
     * @param position a position on the board
     * @return whether the move is valid
     */
    public boolean isValidMove(int position){

        return false;
    }


}

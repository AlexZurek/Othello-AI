package com.othello;

/**
 *
 */
public class Decision {
    public int maxIndex;
    public Board board;
    public String player;
    public String enemy;

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

    public boolean isValidMove(int position){

        return false;
    }


}

package com.othello;

/**
 * Class that is responsible for making some serious decisions.
 */
public class Decision {
    public Board board;
    public String player;
    public String enemy;
    public int timeLeft;

    /**
     * Decision Constructor
     * @param board the Board object that represents the current state
     * @param player the player, either w or b
     * @param timeToDecide remaining time to return a valid move
     */
    public Decision(Board board, String player, int timeToDecide) {
        this.board = board;
        this.player = player;
        this.enemy = player.equals("w") ? "w" : "b";
        this.timeLeft = timeToDecide - 500;
    }

    //region Getters and Setters

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

    public int getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }
    //endregion

    public int DecideMove(){
        DetermineValidMovesForBoard();
        return 19;
    }

    private void DetermineValidMovesForBoard() {
        String[] currentBoard = board.getSquares();
        for (int i = 0; i <= board.getMaxIndex(); i++){
            if (isValidMove(i)){
                currentBoard[i] = "v";
            }
        }
        board.setSquares(currentBoard);
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

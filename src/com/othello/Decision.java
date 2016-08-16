package com.othello;

import java.util.*;

/**
 * Class that is responsible for making some serious decisions.
 */
public class Decision {
    private Board board;
    private String player;
    private String enemy;
    private int timeLeft;

    private int[] _CrucialPositions = {0, 2, 5, 7, 10, 13, 16, 17, 18, 21, 22, 23, 40, 41, 42, 45, 46, 47, 50, 53, 56, 58, 61, 63,};
    private final int INVALID_MOVE = -1;
    private final int ENEMYHASBEENPASSED = -2;
    private final int MOVE_UP = -8;
    private final int MOVE_DOWN = 8;
    private final int MOVE_LEFT = -1;
    private final int MOVE_RIGHT = 1;

    /**
     * Decision Constructor
     * @param board the Board object that represents the current state
     * @param player the player, either w or b
     * @param timeToDecide remaining time to return a valid move
     */
    public Decision(Board board, String player, int timeToDecide) {
        this.board = board;
        this.player = (player.equals("white") || player.equals("w")) ? "w" : "b";
        this.enemy = (player.equals("white") || player.equals("w")) ? "b" : "w";
        this.timeLeft = timeToDecide - 500;
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

    public int getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public int validateU(int position){
        boolean passedEnemy = false;

        for (int movement = MOVE_UP; (position + movement) >= 0; movement += MOVE_UP){
            int result = CheckDirection(position, movement, passedEnemy);
            if(result == INVALID_MOVE){
                break;
            }else if(result == ENEMYHASBEENPASSED){
                passedEnemy = true;
            }else {
                return result;
            }
        }
        return INVALID_MOVE;
    }

    public int validateD(int position){
        int height = getBoard().getHeight();
        boolean passedEnemy = false;

        for (int movement = MOVE_DOWN; (position + movement) <= getBoard().getMaxIndex(); movement += MOVE_DOWN){
            int result = CheckDirection(position, movement, passedEnemy);
            if(result == INVALID_MOVE){
                break;
            }else if(result == ENEMYHASBEENPASSED){
                passedEnemy = true;
            }else {
                return result;
            }
        }
        return INVALID_MOVE;
    }

    public int validateL(int position){
        int width = getBoard().getWidth();
        boolean passedEnemy = false;

        for (int movement = MOVE_LEFT; (position + movement) % width != 0; movement += MOVE_LEFT){
            int result = CheckDirection(position, movement, passedEnemy);
            if(result == INVALID_MOVE){
                break;
            }else if(result == ENEMYHASBEENPASSED){
                passedEnemy = true;
            }else {
                return result;
            }
        }
        return INVALID_MOVE;
    }

    public int validateR(int position){
        int width = getBoard().getWidth();
        boolean passedEnemy = false;

        for (int movement = MOVE_RIGHT; (position + movement) % width != 0; movement += MOVE_RIGHT){
            int result = CheckDirection(position, movement, passedEnemy);
            if(result == INVALID_MOVE){
                break;
            }else if(result == ENEMYHASBEENPASSED){
                passedEnemy = true;
            }else {
                return result;
            }
        }
        return INVALID_MOVE;
    }

    public int validateULD(int position){
        int width = getBoard().getWidth();
        int startingColumn = position % width;
        boolean passedEnemy = false;

        for (int movement = MOVE_UP + MOVE_LEFT; (position + movement) >= 0 &&
                     (position + movement) % width < startingColumn; movement += (MOVE_UP + MOVE_LEFT)) {
            int result = CheckDirection(position, movement, passedEnemy);
            if(result == INVALID_MOVE){
                break;
            }else if(result == ENEMYHASBEENPASSED){
                passedEnemy = true;
            }else {
                return result;
            }
        }
        return INVALID_MOVE;
    }

    public int validateURD(int position){
        int width = getBoard().getWidth();
        int startingColumn = position % width;
        boolean passedEnemy = false;

        for (int movement = MOVE_UP + MOVE_RIGHT; (position + movement) >= 0 &&
                     (position + movement) % width > startingColumn; movement += (MOVE_UP + MOVE_RIGHT)) {
            int result = CheckDirection(position, movement, passedEnemy);
            if(result == INVALID_MOVE){
                break;
            }else if(result == ENEMYHASBEENPASSED){
                passedEnemy = true;
            }else {
                return result;
            }
        }
        return INVALID_MOVE;
    }

    public int validateLLD(int position){
        int width = getBoard().getWidth();
        int startingColumn = position % width;
        boolean passedEnemy = false;

        for (int movement = MOVE_DOWN + MOVE_LEFT; (position + movement) >= 0 &&
                     (position + movement) % width < startingColumn; movement += (MOVE_DOWN + MOVE_LEFT)) {
            int result = CheckDirection(position, movement, passedEnemy);
            if(result == INVALID_MOVE){
                break;
            }else if(result == ENEMYHASBEENPASSED){
                passedEnemy = true;
            }else {
                return result;
            }
        }
        return INVALID_MOVE;
    }

    public int validateLRD(int position){
        int width = getBoard().getWidth();
        int startingColumn = position % width;
        boolean passedEnemy = false;

        for (int movement = MOVE_DOWN + MOVE_RIGHT; (position + movement) >= 0 &&
                     (position + movement) % width > startingColumn; movement += (MOVE_DOWN + MOVE_RIGHT)) {
            int result = CheckDirection(position, movement, passedEnemy);
            if(result == INVALID_MOVE){
                break;
            }else if(result == ENEMYHASBEENPASSED){
                passedEnemy = true;
            }else {
                return result;
            }
        }
        return INVALID_MOVE;
    }

    //endregion

    public int DecideMove(){
        DetermineValidMovesForBoard();

        String[] board = getBoard().getSquares();
        ArrayList<Integer> validMoves = new ArrayList<>();

        for (int i = 0; i <= getBoard().getMaxIndex(); i++){
            if (board[i].equals("v")){
                validMoves.add(i);
            }
        }

        Collections.reverse(validMoves);
        for (Integer validMove : validMoves) {
            for (int _CrucialPosition : _CrucialPositions) {
                if (_CrucialPosition == validMove) {
                    return validMove;
                }
            }
        }
        return validMoves.get(new Random().nextInt(validMoves.size()));
    }

    public void DetermineValidMovesForBoard() {
        String[] currentBoard = getBoard().getSquares();
        for (int i = 0; i <= getBoard().getMaxIndex(); i++){
            if (currentBoard[i].equals(getPlayer())) {
                currentBoard = ValidMoves(currentBoard, i);
                getBoard().setSquares(currentBoard);
            }
        }
    }

    /**
     * Determines if a given position on the board is a valid move
     * @param position a position on the board
     * @return whether the move is valid
     */
    public String[] ValidMoves(String[] board, int position){

        int move = validateU(position);
        if (move != INVALID_MOVE) { board[move] = "v"; }

        move = validateURD(position);
        if (move != INVALID_MOVE) { board[move] = "v"; }

        move = validateR(position);
        if (move != INVALID_MOVE) { board[move] = "v"; }

        move = validateLRD(position);
        if (move != INVALID_MOVE) { board[move] = "v"; }

        move = validateD(position);
        if (move != INVALID_MOVE) { board[move] = "v"; }

        move = validateLLD(position);
        if (move != INVALID_MOVE) { board[move] = "v"; }

        move = validateL(position);
        if (move != INVALID_MOVE) { board[move] = "v"; }

        move = validateULD(position);
        if (move != INVALID_MOVE) { board[move] = "v"; }

        return board;
    }

    private int CheckDirection(int position, int movement, boolean passedEnemy){
        String[] squares = getBoard().getSquares();

        if ((position + movement) < 0 || (position + movement) >= squares.length){
            return INVALID_MOVE;
        }else if(squares[position + movement].equals(getPlayer())){
            return INVALID_MOVE;
        }else if(!passedEnemy && !isTaken(position + movement)) {
            return INVALID_MOVE;
        }else if(squares[position + movement].equals("v")) {
            return INVALID_MOVE;
        }else if (!passedEnemy && squares[position + movement].equals(getEnemy())){
            return ENEMYHASBEENPASSED;
        }else if (passedEnemy && squares[position + movement].equals(getEnemy())){
            return ENEMYHASBEENPASSED;
        }else if(passedEnemy && !isTaken(position + movement)){
            return position + movement;
        }

        return INVALID_MOVE;
    }

    private boolean isTaken(int position){
        String[] squares = getBoard().getSquares();
        return !squares[position].equals("-");
    }
}

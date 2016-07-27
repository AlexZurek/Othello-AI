package com.othello;

import com.google.gson.Gson;

public class Main {

    /**
     * Main method
     * @param args command line arguments
     * @return move decision
     */
    public static int main(String[] args) {
        System.out.println("ARG Count: " + args.length);
        if (args.length != 7){
            return 1;
        }

        TurnInfo turnInfo = ValidateInput(args);

        Board board = CreateBoard("{\n" +
                "  \"width\": 8,\n" +
                "  \"height\": 8,\n" +
                "  \"max-index\": 63,\n" +
                "  \"squares\": [\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"w\",\"b\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"b\",\"w\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\"]\n" +
                "}");

        Decision decision = new Decision(turnInfo, board);
        System.out.println("Current Board:\n");
        System.out.println(board.toString());

        return decision.DecideMove();
    }

    /**
     * Creates the Board Object
     * @param board JSON representation of the board
     * @return the newly created Board Object
     */
    private static Board CreateBoard(String board) {
        Gson gson = new Gson();
        return gson.fromJson(board, Board.class);
    }

    /**
     * Validates the command line arguments
     * @param args command line arguments
     * @return the info related to the turn
     */
    private static TurnInfo ValidateInput(String[] args){
        TurnInfo turnInfo = new TurnInfo();
        String curPos, nextPos;

        for (int count = 1; count < args.length; count++){
            curPos = args[count];
            nextPos = args[count + 1];
            if (curPos.equals("-b")){
                turnInfo.json = nextPos;
            }else if (curPos.equals("-p")){
                turnInfo.player = nextPos;
            }else if (curPos.equals("-t")){
                turnInfo.time = Integer.parseInt(nextPos);
            }
        }

        return turnInfo;
    }

}

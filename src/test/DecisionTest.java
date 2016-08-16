package test;

import com.google.gson.Gson;
import com.othello.Board;
import com.othello.Decision;
import org.junit.Test;

import static org.junit.Assert.*;

public class DecisionTest extends TestBase {

    @Test
    public void TestConstructor(){
        Board board = CreateStartingBoard();
        String player = "w";
        int timeToDecide = 15000;

        Decision decision = new Decision(board, player, timeToDecide);

        assertEquals(player, decision.getPlayer());
        assertEquals("b", decision.getEnemy());
        assertEquals(board, decision.getBoard());
        assertEquals(14500, decision.getTimeLeft());
    }

    @Test
    public void TestDeterminingAllValidMovesOnStartingBoard(){
        Board board = CreateStartingBoard();
        String player = "w";
        int timeToDecide = 15000;

        Decision decision = new Decision(board, player, timeToDecide);
        decision.DetermineValidMovesForBoard();
        String expected = "Board {" +
                "\n\twidth    = 8"  +
                "\n\theight   = 8"  +
                "\n\tmaxIndex = 63" +
                "\n\tsquares  = " +
                "\n\t\t[-, -, -, -, -, -, -, -, " +
                "\n\t\t -, -, -, -, -, -, -, -, " +
                "\n\t\t -, -, -, -, v, -, -, -, " +
                "\n\t\t -, -, -, w, b, v, -, -, " +
                "\n\t\t -, -, v, b, w, -, -, -, " +
                "\n\t\t -, -, -, v, -, -, -, -, " +
                "\n\t\t -, -, -, -, -, -, -, -, " +
                "\n\t\t -, -, -, -, -, -, -, -]" + '}';

        assertEquals(expected, decision.getBoard().toString());
    }

    @Test
    public void TestDeterminingAllValidMovesForGameInProgress(){
        Board board = CreateGameInProgress();
        String player = "w";
        int timeToDecide = 15000;

        Decision decision = new Decision(board, player, timeToDecide);
        decision.DetermineValidMovesForBoard();
        String expected = "Board {" +
                "\n\twidth    = 8"  +
                "\n\theight   = 8"  +
                "\n\tmaxIndex = 63" +
                "\n\tsquares  = " +
                "\n\t\t[-, -, -, -, v, v, -, -, " +
                "\n\t\t -, v, v, v, b, b, v, -, " +
                "\n\t\t -, -, b, b, b, b, -, -, " +
                "\n\t\t -, -, v, b, b, w, -, -, " +
                "\n\t\t -, -, v, b, w, w, -, -, " +
                "\n\t\t -, -, w, w, w, w, -, -, " +
                "\n\t\t -, -, -, w, w, w, -, -, " +
                "\n\t\t -, -, -, -, -, -, -, -]" + '}';

        assertEquals(expected, decision.getBoard().toString());
    }

    @Test
    public void TestValidMoves(){
        Board board = CreateStartingBoard();
        String player = "w";
        int timeToDecide = 15000;

        Decision decision = new Decision(board, player, timeToDecide);
        String[] results = decision.ValidMoves(board.getSquares(), 2);

        assertArrayEquals(board.getSquares(), results);
    }

    @Test
    public void ItValidatesUp(){
        Board board = CreateStartingBoard();
        String player = "w";
        int timeToDecide = 15000;

        Decision decision = new Decision(board, player, timeToDecide);
        int validMove = decision.validateU(36);

        assertEquals(20, validMove);
    }

    @Test
    public void ItReturnsInvalidAfterPassingValidMoveGoingUp(){
        Board board = CreateBoardWithValidMovesSpecified();
        String player = "w";
        int timeToDecide = 15000;

        Decision decision = new Decision(board, player, timeToDecide);
        int move = decision.validateU(27);

        assertEquals(-1, move);
    }

    @Test
    public void ItValidatesUpAtBoundaryReturnsInvalid(){
        Board board = CreateStartingBoard();
        String player = "w";
        int timeToDecide = 15000;

        Decision decision = new Decision(board, player, timeToDecide);
        int validMove = decision.validateU(3);

        assertEquals(-1, validMove);
    }

    @Test
    public void ItValidatesDown(){
        Board board = CreateStartingBoard();
        String player = "w";
        int timeToDecide = 15000;

        Decision decision = new Decision(board, player, timeToDecide);
        int validMove = decision.validateD(27);

        assertEquals(43, validMove);
    }

    @Test
    public void ItValidatesDownAtBoundaryReturnsInvalid(){
        Board board = CreateStartingBoard();
        String player = "w";
        int timeToDecide = 15000;

        Decision decision = new Decision(board, player, timeToDecide);
        int validMove = decision.validateD(60);

        assertEquals(-1, validMove);
    }

    @Test
    public void ItValidatesLeft(){
        Board board = CreateStartingBoard();
        String player = "w";
        int timeToDecide = 15000;

        Decision decision = new Decision(board, player, timeToDecide);
        int validMove = decision.validateL(36);

        assertEquals(34, validMove);
    }

    @Test
    public void ItValidatesLeftAtBoundaryReturnsInvalid(){
        Board board = CreateStartingBoard();
        String player = "w";
        int timeToDecide = 15000;

        Decision decision = new Decision(board, player, timeToDecide);
        int validMove = decision.validateL(16);

        assertEquals(-1, validMove);
    }

    @Test
    public void ItValidatesRight(){
        Board board = CreateStartingBoard();
        String player = "w";
        int timeToDecide = 15000;

        Decision decision = new Decision(board, player, timeToDecide);
        int validMove = decision.validateR(27);

        assertEquals(29, validMove);
    }

    @Test
    public void ItValidatesRightAtBoundaryReturnsInvalid(){
        Board board = CreateStartingBoard();
        String player = "w";
        int timeToDecide = 15000;

        Decision decision = new Decision(board, player, timeToDecide);
        int validMove = decision.validateR(31);

        assertEquals(-1, validMove);
    }


    @Test
    public void ItValidatesRightStopsAtBoundary(){
        Board board = CreateStartingBoard();
        String player = "w";
        int timeToDecide = 15000;

        Decision decision = new Decision(board, player, timeToDecide);
        int validMove = decision.validateR(36);

        assertEquals(-1, validMove);
    }

    @Test
    public void ItValidatesUpperLeftDiagonal(){
        Board board = CreateBoardToTestEachCorner();
        String player = "w";
        int timeToDecide = 15000;

        Decision decision = new Decision(board, player, timeToDecide);
        int validMove = decision.validateULD(18);

        assertEquals(0, validMove);
    }

    @Test
    public void ItValidatesUpperLeftDiagonalAtBoundaryReturnsInvalid(){
        Board board = CreateBoardToTestEachCorner();
        String player = "b";
        int timeToDecide = 15000;

        Decision decision = new Decision(board, player, timeToDecide);
        int validMove = decision.validateULD(0);

        assertEquals(-1, validMove);
    }

    @Test
    public void ItValidatesUpperLeftDiagonalFromCornerToCorner(){
        Board board = CreateBoardToTestULD();
        String player = "w";
        int timeToDecide = 15000;

        Decision decision = new Decision(board, player, timeToDecide);
        int validMove = decision.validateULD(63);

        assertEquals(0, validMove);
    }

    @Test
    public void ItValidatesUpperRightDiagonal(){
        Board board = CreateBoardToTestEachCorner();
        String player = "w";
        int timeToDecide = 15000;

        Decision decision = new Decision(board, player, timeToDecide);
        int validMove = decision.validateURD(21);

        assertEquals(7, validMove);
    }

    @Test
    public void ItValidatesUpperRightDiagonalAtBoundaryReturnsInvalid(){
        Board board = CreateBoardToTestEachCorner();
        String player = "b";
        int timeToDecide = 15000;

        Decision decision = new Decision(board, player, timeToDecide);
        int validMove = decision.validateURD(7);

        assertEquals(-1, validMove);
    }

    @Test
    public void ItValidatesUpperRightDiagonalFromCornerToCorner(){
        Board board = CreateBoardToTestURD();
        String player = "w";
        int timeToDecide = 15000;

        Decision decision = new Decision(board, player, timeToDecide);
        int validMove = decision.validateURD(56);

        assertEquals(7, validMove);
    }

    @Test
    public void ItValidatesLowerLeftDiagonal(){
        Board board = CreateBoardToTestEachCorner();
        String player = "w";
        int timeToDecide = 15000;

        Decision decision = new Decision(board, player, timeToDecide);
        int validMove = decision.validateLLD(42);

        assertEquals(56, validMove);
    }

    @Test
    public void ItValidatesLowerLeftDiagonalAtBoundaryReturnsInvalid(){
        Board board = CreateBoardToTestEachCorner();
        String player = "b";
        int timeToDecide = 15000;

        Decision decision = new Decision(board, player, timeToDecide);
        int validMove = decision.validateLLD(56);

        assertEquals(-1, validMove);
    }

    @Test
    public void ItValidatesLowerLeftDiagonalFromCornerToCorner(){
        Board board = CreateBoardToTestLLD();
        String player = "w";
        int timeToDecide = 15000;

        Decision decision = new Decision(board, player, timeToDecide);
        int validMove = decision.validateLLD(7);

        assertEquals(56, validMove);
    }

    @Test
    public void ItValidatesLowerRightDiagonal(){
        Board board = CreateBoardToTestEachCorner();
        String player = "w";
        int timeToDecide = 15000;

        Decision decision = new Decision(board, player, timeToDecide);
        int validMove = decision.validateLRD(45);

        assertEquals(63, validMove);
    }

    @Test
    public void ItValidatesLowerRightDiagonalAtBoundaryReturnsInvalid(){
        Board board = CreateBoardToTestEachCorner();
        String player = "b";
        int timeToDecide = 15000;

        Decision decision = new Decision(board, player, timeToDecide);
        int validMove = decision.validateLRD(63);

        assertEquals(-1, validMove);
    }

    @Test
    public void ItValidatesLowerRightDiagonalFromCornerToCorner(){
        Board board = CreateBoardToTestLRD();
        String player = "w";
        int timeToDecide = 15000;

        Decision decision = new Decision(board, player, timeToDecide);
        int validMove = decision.validateLRD(0);

        assertEquals(63, validMove);
    }

    @Test
    public void ItChoosesAValidMove(){
        Board board = CreateStartingBoard();
        String player = "w";
        int timeToDecide = 15000;

        Decision decision = new Decision(board, player, timeToDecide);
        int move = decision.DecideMove();
        String[] squares = decision.getBoard().getSquares();

        assertEquals("v", squares[move]);

        Board newBoard = CreateGameInProgress();
        decision.setBoard(newBoard);

        move = decision.DecideMove();
        squares = decision.getBoard().getSquares();

        assertEquals("v", squares[move]);
    }

    @Test
    public void DoesNotListSquareTwentyFiveAsValid(){
        String json = "{\n" +
                "  \"width\": 8,\n" +
                "  \"height\": 8,\n" +
                "  \"max-index\": 63,\n" +
                "  \"squares\": [\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\"," +
                                "\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\"," +
                                "\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\"," +
                                "\"-\",\"-\",\"b\",\"b\",\"b\",\"-\",\"-\",\"-\"," +
                                "\"-\",\"-\",\"w\",\"b\",\"b\",\"-\",\"-\",\"-\"," +
                                "\"-\",\"-\",\"-\",\"-\",\"b\",\"-\",\"-\",\"-\"," +
                                "\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\"," +
                                "\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\"]\n" + "}";
        Gson gson = new Gson();

        Board board = gson.fromJson(json, Board.class);
        String player = "w";
        int timeToDecide = 15000;

        Decision decision = new Decision(board, player, timeToDecide);

        decision.DetermineValidMovesForBoard();
        String expected = "Board {" +
                "\n\twidth    = 8"  +
                "\n\theight   = 8"  +
                "\n\tmaxIndex = 63" +
                "\n\tsquares  = " +
                "\n\t\t[-, -, -, -, -, -, -, -, " +
                "\n\t\t -, -, -, -, -, -, -, -, " +
                "\n\t\t -, -, v, -, v, -, -, -, " +
                "\n\t\t -, -, b, b, b, -, -, -, " +
                "\n\t\t -, -, w, b, b, v, -, -, " +
                "\n\t\t -, -, -, -, b, -, -, -, " +
                "\n\t\t -, -, -, -, -, -, -, -, " +
                "\n\t\t -, -, -, -, -, -, -, -]" + '}';

        assertEquals(expected, decision.getBoard().toString());
    }

    @Test
    public void DoesNotListSquareTwentyTwoAsValid(){
        String json = "{\n" +
                "  \"width\": 8,\n" +
                "  \"height\": 8,\n" +
                "  \"max-index\": 63,\n" +
                "  \"squares\": [\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\"," +
                                "\"-\",\"-\",\"b\",\"w\",\"-\",\"-\",\"b\",\"-\"," +
                                "\"-\",\"-\",\"b\",\"w\",\"-\",\"b\",\"-\",\"-\"," +
                                "\"-\",\"-\",\"-\",\"w\",\"b\",\"-\",\"-\",\"-\"," +
                                "\"-\",\"-\",\"b\",\"b\",\"b\",\"-\",\"-\",\"-\"," +
                                "\"-\",\"-\",\"-\",\"b\",\"w\",\"w\",\"-\",\"-\"," +
                                "\"-\",\"-\",\"b\",\"-\",\"-\",\"-\",\"-\",\"-\"," +
                                "\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\"]\n" + "}";
        Gson gson = new Gson();

        Board board = gson.fromJson(json, Board.class);
        String player = "w";
        int timeToDecide = 15000;

        Decision decision = new Decision(board, player, timeToDecide);

        decision.DetermineValidMovesForBoard();
        String expected = "Board {" +
                "\n\twidth    = 8"  +
                "\n\theight   = 8"  +
                "\n\tmaxIndex = 63" +
                "\n\tsquares  = " +
                "\n\t\t[-, v, -, -, -, -, -, -, " +
                "\n\t\t -, v, b, w, -, -, b, -, " +
                "\n\t\t -, v, b, w, v, b, -, -, " +
                "\n\t\t -, v, v, w, b, v, -, -, " +
                "\n\t\t -, -, b, b, b, v, -, -, " +
                "\n\t\t -, v, v, b, w, w, -, -, " +
                "\n\t\t -, -, b, v, -, -, -, -, " +
                "\n\t\t -, -, -, -, -, -, -, -]" + '}';

        assertEquals(expected, decision.getBoard().toString());
    }
}

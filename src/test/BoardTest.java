package test;

import com.google.gson.Gson;
import com.othello.Board;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void CreateBoardFromJson(){
        String json = "{\n" +
                "  \"width\": 8,\n" +
                "  \"height\": 8,\n" +
                "  \"max-index\": 63,\n" +
                "  \"squares\": [\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\"," +
                                "\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\"," +
                                "\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\"," +
                                "\"-\",\"-\",\"-\",\"w\",\"b\",\"-\",\"-\",\"-\"," +
                                "\"-\",\"-\",\"-\",\"b\",\"w\",\"-\",\"-\",\"-\"," +
                                "\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\"," +
                                "\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\"," +
                                "\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\"]\n" + "}";
        Gson gson = new Gson();
        Board board = gson.fromJson(json, Board.class);

        assertEquals(8, board.getWidth());
        assertEquals(8, board.getHeight());
        assertEquals(63, board.getMaxIndex());
        assertEquals(64, board.getSquares().length);
    }

    @Test
    public void BoardToStringWorks(){
        String json = "{\n" +
                "  \"width\": 8,\n" +
                "  \"height\": 8,\n" +
                "  \"max-index\": 63,\n" +
                "  \"squares\": [\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\"," +
                                "\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\"," +
                                "\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\"," +
                                "\"-\",\"-\",\"-\",\"w\",\"b\",\"-\",\"-\",\"-\"," +
                                "\"-\",\"-\",\"-\",\"b\",\"w\",\"-\",\"-\",\"-\"," +
                                "\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\"," +
                                "\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\"," +
                                "\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\",\"-\"]\n" + "}";
        Gson gson = new Gson();
        Board board = gson.fromJson(json, Board.class);

        String expected = "Board {" +
                "\n\twidth    = 8"  +
                "\n\theight   = 8"  +
                "\n\tmaxIndex = 63" +
                "\n\tsquares  = " +
                "\n\t\t[-, -, -, -, -, -, -, -, " +
                "\n\t\t -, -, -, -, -, -, -, -, " +
                "\n\t\t -, -, -, -, -, -, -, -, " +
                "\n\t\t -, -, -, w, b, -, -, -, " +
                "\n\t\t -, -, -, b, w, -, -, -, " +
                "\n\t\t -, -, -, -, -, -, -, -, " +
                "\n\t\t -, -, -, -, -, -, -, -, " +
                "\n\t\t -, -, -, -, -, -, -, -]" + '}';
        assertEquals(expected, board.toString());
    }
}

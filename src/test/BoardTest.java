package test;

import com.google.gson.Gson;
import com.othello.Board;
import com.othello.Othello;
import org.junit.Test;
import java.lang.String;

import java.util.Map;

import static org.junit.Assert.*;

public class BoardTest extends TestBase {

    @Test
    public void CreateBoardFromJson(){
        Board board = CreateStartingBoard();

        assertEquals(8, board.getWidth());
        assertEquals(8, board.getHeight());
        assertEquals(63, board.getMaxIndex());
        assertEquals(64, board.getSquares().length);
    }

    @Test
    public void BoardToStringWorks(){
        Board board = CreateStartingBoard();

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

    @Test
    public void UriQueryDecodeTest(){
        String query = "-b=%22%7B%22width%22%3A8%2C%22height%22%3A8%2C%22max-index%22%3A63%2C%22squares%22%3A%5B%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22w%22%2C%22b%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22b%22%2C%22w%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%2C%22-%22%5D%7D%22&-p=black&-t=15000";
        Map<String, String> params = Othello.queryToMap(query);

        String json = params.get("-b");
        json = json.substring(1, json.length()-1);

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

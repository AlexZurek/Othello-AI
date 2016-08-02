package com.othello;

import com.google.gson.Gson;
import com.sun.glass.ui.SystemClipboard;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.function.ToIntFunction;

import static java.net.URLDecoder.decode;

public class Othello {

    /**
     * Othello method
     * @param args command line arguments
     * @return move decision
     */
    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/othello", new OthelloHandler());
        server.setExecutor(null); // creates a default executor
        server.start();

    }

    /**
     * Http Request Handler
     *
     * Responsible for extracting parameters from the request and passing these
     * values to the Decision class. The return value from the Decision class
     * is then written into a response body and sent back to the client.
     */
    static class OthelloHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            Map<String, String> params = Othello.queryToMap(exchange.getRequestURI().getQuery());
            Board board = CreateBoard(params.get("-b"));
            String player = params.get("-p");
            int timeToDecide = Integer.parseInt(params.get("-t"));

            Decision decision = new Decision(board, player, timeToDecide);
            String response = Integer.toString(decision.DecideMove());
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
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
     * Returns a Map of the parameters passed by the http request
     * @param query the query of the uri
     * @return a Map of the parameters
     */
    public static Map<String, String> queryToMap(String query){
        Map<String, String> result = new HashMap<>();
        String decodedQuery = "";

        try{ decodedQuery = decode(query, "UTF-8"); }
        catch (UnsupportedEncodingException e){ System.out.println(e.getMessage()); }

        for (String param : decodedQuery.split("&")) {
            String pair[] = param.split("=");
            if (pair.length>1) {
                result.put(pair[0], pair[1]);
            }else{
                result.put(pair[0], "");
            }
        }
        return result;
    }

}

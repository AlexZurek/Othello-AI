package com.othello;

/**
 * Object that represents the info related to the current turn.
 */
public class TurnInfo {
    public String json;
    public String player;
    public int time;

    //region Getters and Setters
    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
    //endregion
}

package com.tictactoe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {
    private String name;
    private String symbol;
    private Integer score;
    private List<Integer> moves = new ArrayList<>();
    private final Matrix matrix = new Matrix(3);

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public void resetMoves() {
        moves = new ArrayList<>();
    }

    public boolean move(int pos) {
        moves.add(pos);
        if (moves.size() >= 3) {
            Collections.sort(moves);
            List<List<Integer>> values = matrix.getValues();
            boolean win = values.stream().anyMatch(val -> moves.containsAll(val));
            if (win) addScore(1);
            return win;
        }
        return false;
    }

    public void addScore(int s) {
        this.score += s;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}

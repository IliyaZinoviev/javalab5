package loto;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class Game {
    private Canvas canvas = new Canvas(400,400);
    private int countBarrels = 25;
    private int countPlayers = 4;
    private Thread[] arrPlayers = new Thread[countPlayers];
    private ArrayList<Integer> pouch = new ArrayList<>(countBarrels);
    private Board board = new Board(canvas.getGraphicsContext2D(), countBarrels);
    private boolean gameOver = false;

    public Game(){
        for(int i = 1; i <= countBarrels; i++)
            pouch.add(i);
        for(int i = 0; i<arrPlayers.length; i++)
            arrPlayers[i] = new Thread(new Player(new Color(Math.random(), Math.random(), Math.random(), 1),
                    i, this));
    }

    public void start() {
        for(Thread t: arrPlayers)
            t.start();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public int getCountBarrels() {
        return countBarrels;
    }

    public Board getBoard() {
        return board;
    }

    public ArrayList<Integer> getPouch() {
        return pouch;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}

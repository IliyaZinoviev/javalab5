package loto;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class Game {
    private Canvas canvas = new Canvas(400, 400);
    private int countBarrels = 25;
    private int countPlayers = 4;
    private Thread[] arrPlayers = new Thread[countPlayers];
    private ArrayList<Integer> pouch = new ArrayList<>(countBarrels);
    private Board board = new Board(canvas.getGraphicsContext2D(), countBarrels);
    private boolean gameOver = false;

    public Game() {
        for (int i = 1; i <= countBarrels; i++)
            pouch.add(i);
        for (int i = 0; i < arrPlayers.length; i++)
            arrPlayers[i] = new Thread(new Player(new Color(Math.random(), Math.random(), Math.random(), 1),
                    this));
    }

    public void start() {
        for (Thread t : arrPlayers)
            t.start();
    }

    public synchronized void getBarrel(Color color) {
        Integer barrel = getRandom(countBarrels);
        Item item;
        Integer isBarrel = barrel;
        while (!pouch.contains(barrel)) {
            if (barrel > 1) {
                barrel -= 1;
            } else
                barrel = countBarrels;
            if (barrel.equals(isBarrel))
                break;
        }
        pouch.remove(barrel);
        item = board.getArrItems()[barrel - 1];
        item.setColor(color);
        item.draw();
        System.out.println(Thread.currentThread().getName() + " достал фишку " + barrel);
        if (pouch.isEmpty() && !gameOver) {
            gameOver = true;
            System.out.println(Thread.currentThread().getName() + " достал последнюю фишку!");
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    private int getRandom(int i){
        return (int) (Math.random() * i) + 1;
    }


    public Canvas getCanvas() {
        return canvas;
    }

    public ArrayList<Integer> getPouch() {
        return pouch;
    }
}

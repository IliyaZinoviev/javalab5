package loto;

import javafx.scene.paint.Color;
import java.util.ArrayList;

public class Player implements Runnable {
    private Color color;
    private Game game;
    private int number;

    public Player(Color color, int number, Game game) {
        super();
        this.color = color;
        this.game = game;
        this.number = number;
    }

    public void run() {
        ArrayList<Integer> pouch = game.getPouch();
        while (!pouch.isEmpty()) {
            getBarrel(pouch);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private void getBarrel(ArrayList<Integer> pouch) {
        synchronized (pouch) {
            if (!pouch.isEmpty()) {
                Board board = game.getBoard();
                int countBarrels = game.getCountBarrels();
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
                if (pouch.isEmpty())
                    System.out.println(Thread.currentThread().getName() + " достал последнюю фишку!");
            }
        }
    }

    private int getRandom(int i){
        return (int) (Math.random() * i) + 1;
    }
}

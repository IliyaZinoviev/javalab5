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
        Board board = game.getBoard();
        while (!pouch.isEmpty())
            getBarrel(pouch, board);
    }

    private synchronized void getBarrel(ArrayList<Integer> pouch, Board board){
        if(game.getIsReady()[number]) {
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
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
            int i = game.getIndexOfReady();
            game.getIsReady()[i] = false;
            if (i == game.getCountPlayers() - 1)
                i = 0;
            else
                i++;
            game.setIndexOfReady(i);
            if (!pouch.isEmpty())
                game.getIsReady()[i] = true;
            else{
                System.out.println(Thread.currentThread().getName() + " достал последнюю фишку!");
            }
        }
    }

    private int getRandom(int i){
        return (int) (Math.random() * i) + 1;
    }
}

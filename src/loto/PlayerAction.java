package loto;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class PlayerAction {
    private Game game;
    public PlayerAction(Game game){
        this.game = game;
    }


    public synchronized void getBarrel(Color color){
        ArrayList<Integer> pouch = game.getPouch();
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
        if (pouch.isEmpty() && !game.isGameOver()) {
            game.setGameOver(true);
            System.out.println(Thread.currentThread().getName() + " достал последнюю фишку!");
        }
    }

    private int getRandom(int i){
        return (int) (Math.random() * i) + 1;
    }
}

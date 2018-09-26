package loto;

import javafx.scene.paint.Color;
import java.util.ArrayList;

public class Player implements Runnable {
    private Color color;
    private Game game;

    public Player(Color color, Game game) {
        super();
        this.color = color;
        this.game = game;
    }

    public void run() {
        ArrayList<Integer> pouch = game.getPouch();
        while (!pouch.isEmpty())
            game.getBarrel(color);
    }
}

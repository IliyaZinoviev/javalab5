package loto;

import javafx.scene.paint.Color;

public class Player implements Runnable {
    private Color color;
    private Game game;

    public Player(Color color, Game game) {
        super();
        this.color = color;
        this.game = game;
    }

    public void run() {
        while (!game.getPouch().isEmpty()) {
            game.getPlayerAction().getBarrel(color);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}

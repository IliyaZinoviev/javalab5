package loto;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Board {
    private int countItems;
    private Item[] arrItems;

    public Board(GraphicsContext ctx, int count){
        countItems = count;
        arrItems = new Item[countItems];
        Item item;
        for (int i = 0; i<countItems; i++) {
            item = arrItems[i] = new Item(i, Color.WHITE, culcCoords(i), ctx);
            item.draw();
        }
    }

    private int[] culcCoords(int n){
        int mod = n % 5;
        int row = 16*(n-mod);
        int column = 80*mod;
        return new int[]{column, row};
    }

    public Item[] getArrItems() {
        return arrItems;
    }
}

package loto;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Item {
    private int[] coords;
    private int count;
    private Color color;
    private GraphicsContext ctx;

    public Item(int count, Color color, int[] coords, GraphicsContext ctx){
        this.coords = coords;
        this.color = color;
        this.count = count;
        this.ctx = ctx;
    }

    public void draw(){
        int x = coords[0];
        int y = coords[1];
        ctx.beginPath();
        ctx.setFill(color);
        ctx.rect(x, y, 80, 80);
        ctx.fill();
        ctx.setFill(Color.BLACK);
        ctx.rect(x, y, 80, 80);
        ctx.stroke();
        ctx.setFont(new Font("Arial", 50));
        ctx.setTextAlign(TextAlignment.CENTER);
        ctx.fillText(Integer.toString(count+1), x+40, y+60);
        ctx.closePath();

    }

    public void setColor(Color color) {
        this.color = color;
    }
}

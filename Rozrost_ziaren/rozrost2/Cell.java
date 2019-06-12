import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Cell {
    private Paint color = Color.WHITE;
    private int value = 0;
    private double x, y;

    public Cell(){

    }
    public Paint getColor() {
        return color;
    }

    public void setColor(Paint color) {
        this.color = color;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
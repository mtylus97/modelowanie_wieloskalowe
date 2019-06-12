import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Cell {
    private Paint color = Color.WHITE;
    private int value = 0;

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


}
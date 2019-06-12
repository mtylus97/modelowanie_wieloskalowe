import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Cell {
    private Paint color = Color.WHITE;
    private int value = 0;
    private double x, y;
    private int energy =0;
    private double gestoscDyslokacji=0;
    private boolean czyZrekrystalizowany=false;

    public double getGestoscDyslokacji() {
        return gestoscDyslokacji;
    }

    public void setGestoscDyslokacji(double gestoscDyslokacji) {
        this.gestoscDyslokacji = gestoscDyslokacji;
    }

    public void zwiekszGestosc(double amount) {
        this.gestoscDyslokacji+=amount ;
    }

    public boolean isCzyZrekrystalizowany() {
        return czyZrekrystalizowany;
    }

    public void setCzyZrekrystalizowany(boolean czyZrekrystalizowany) {
        this.czyZrekrystalizowany = czyZrekrystalizowany;
    }

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

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void incrementEnergy() {
        this.energy+=1;
    }
}
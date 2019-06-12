public class Cell {
    private boolean isAlive;

    public Cell(){}

    public int isAlive() {
        return (isAlive ? 1:0);
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}

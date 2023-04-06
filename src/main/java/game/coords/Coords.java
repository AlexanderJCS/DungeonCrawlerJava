package game.coords;

public abstract class Coords {
    public float x;
    public float y;

    public Coords(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "X: " + this.x + " Y: " + this.y;
    }
}

package graphics;


import game.coords.ScreenCoords;
import graphics.texture.TexturedModel;

public class Rect {
    public float x1, y1, x2, y2;
    public float width, height;

    public Rect(Rect copy) {
        this.x1 = copy.x1;
        this.y1 = copy.y1;
        this.x2 = copy.x2;
        this.y2 = copy.y2;

        this.width = copy.width;
        this.height = copy.height;
    }

    /**
     * @param coords the coordinates of the rect
     * @param width Width
     * @param height Height
     */
    public Rect(ScreenCoords coords, float width, float height) {
        this.x1 = coords.x;
        this.y1 = coords.y;
        this.x2 = coords.x + width;
        this.y2 = coords.y + height;

        this.width = width;
        this.height = height;
    }

    public ScreenCoords getCenter() {
        return new ScreenCoords(
                (this.x1 + this.x2) / 2,
                (this.y1 + this.y2) / 2
        );
    }

    public void setCenter(float x, float y) {
        float halfWidth = (x2 - x1) / 2;
        float halfHeight = (y2 - y1) / 2;

        this.x1 = x - halfWidth;
        this.x2 = x + halfWidth;
        this.y1 = y - halfHeight;
        this.y2 = y + halfHeight;
    }

    /**
     * Sets the coordinates to the ones given.
     *
     * @param x, Top left corner x position
     * @param y, Top left corner y position
     */
    public void setPos(float x, float y) {
        this.x1 = x;
        this.y1 = y;
        this.x2 = width;
        this.y2 = height;
    }

    /**
     * Shift the rectangle by a certain amount
     * @param x X amount to shift
     * @param y Y amount to shift
     */
    public void shift(float x, float y) {
        this.x1 += x;
        this.x2 += x;
        this.y1 += y;
        this.y2 += y;
    }

    /**
     * Converts the vertices to a model. Used to display to the screen.
     * @return The model.
     */
    public Model toModel() {
        // x1 = 0
        // y1 = 0
        // x2 = 1
        // y2 = 1

        float[] vertices = {
                x1, y1,  // Top left
                x2, y1,  // Top right
                x2, y2,  // Bottom right

                x2, y2,  // Bottom right
                x1, y2,  // Bottom left
                x1, y1,  // Top left
        };

        return new Model(vertices);
    }

    public TexturedModel toTexturedModel() {
        // x1 = 0
        // y1 = 0
        // x2 = 1
        // y2 = 1

        float[] vertices = {
                x1, y1,  // Top left
                x2, y1,  // Top right
                x2, y2,  // Bottom right

                x2, y2,  // Bottom right
                x1, y2,  // Bottom left
                x1, y1,  // Top left
        };

        float[] texCoords = new float[] {
                0, 1,
                1, 1,
                1, 0,

                1, 0,
                0, 0,
                0, 1
        };

        return new TexturedModel(vertices, texCoords);
    }

    /**
     * Check if a point is inside the rect. Used for collision checking.
     *
     * @param coords The point
     * @return If it is inside the rect
     */
    public boolean pointInsideRect(ScreenCoords coords) {
        return (coords.y > this.y1 && coords.y < this.y2) &&
                (coords.x < this.x2 && coords.x > this.x1);
    }

    /**
     * Check if this rect collides if another rect
     *
     * @param other The other rect
     * @return If they are colliding
     */
    public boolean collidesWith(Rect other) {
        return this.pointInsideRect(new ScreenCoords(other.x1, other.y1)) ||   // Top left corner
                this.pointInsideRect(new ScreenCoords(other.x1, other.y2)) ||  // Bottom left corner
                this.pointInsideRect(new ScreenCoords(other.x2, other.y1)) ||  // Top right corner
                this.pointInsideRect(new ScreenCoords(other.x2, other.y2));    // Bottom right corner
    }

    @Override
    public String toString() {
        return "Rect{" +
                "x1=" + x1 +
                ", y1=" + y1 +
                ", x2=" + x2 +
                ", y2=" + y2 +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}

package game.gameobjects;

import game.coords.ScreenCoords;

public class Wall extends GameObject {
    public Wall(ScreenCoords coords, float width, float height) {
        super(coords, width, height, "white");
    }

    @Override
    public void update() {}

    @Override
    public String toString() {
        return "Wall{" + this.rect.x1 + ", " + this.rect.y1 + " | " + this.rect.x2 + ", " + this.rect.y2 + "}";
    }
}

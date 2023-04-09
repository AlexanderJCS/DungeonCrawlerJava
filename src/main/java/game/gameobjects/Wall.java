package game.gameobjects;

import game.coords.ScreenCoords;

public class Wall extends TileableGameObject {
    public Wall(ScreenCoords coords, float width, float height) {
        super(coords, width, height, "brick");
    }
}

package game.gameobjects;

import helper.coords.ScreenCoords;

public class Wall extends TileableGameObject {
    public Wall(ScreenCoords coords, float width, float height) {
        super(coords, width, height, "brick");
    }
}

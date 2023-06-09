package game.gameobjects;

import helper.coords.ScreenCoords;
import lwjgl.graphics.texture.TextureType;

public class Wall extends TileableGameObject {
    /**
     * @param coords The top left corner of the wall.
     * @param width  The width in ScreenCoords
     * @param height The height in ScreenCoords
     */
    public Wall(ScreenCoords coords, float width, float height) {
        super(coords, width, height, TextureType.BRICK);
    }
}

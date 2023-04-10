package game.gameobjects;

import graphics.texture.TextureMap;
import helper.coords.ScreenCoords;
import graphics.Rect;

/**
 * An image that can be displayed to the screen.
 */
public class Image {
    protected Rect rect;
    /** The texture name. A list of texture names can be found in the TextureMap object. */
    public String textureName;

    public Image(ScreenCoords coords, float width, float height, String textureName) {
        this.rect = new Rect(coords, width, height);
        this.textureName = textureName;
    }

    /**
     * Draws the image (including its texture) to the screen.
     */
    public void draw() {
        TextureMap.get(this.textureName).bind();
        this.rect.drawModel();
    }

    /**
     * @return A reference of the image's rect.
     */
    public Rect getRect() {
        return this.rect;
    }
}

package game.gameobjects;

import helper.coords.ScreenCoords;
import helper.interfaces.Drawable;
import lwjgl.graphics.Rect;
import lwjgl.graphics.texture.TextureMap;
import lwjgl.graphics.texture.TextureType;

/**
 * An image that can be displayed to the screen.
 */
public class Image implements Drawable {
    protected final Rect rect;
    /**
     * The texture name. A list of texture names can be found in the graphics.texture.TextureMap object.
     */
    public TextureType textureType;

    /**
     * @param coords      The bottom left coordinate of the image
     * @param width       The width of the image in ScreenCoords
     * @param height      The height of the image in ScreenCoords
     * @param textureType The texture's type. See graphics.texture.TextureMap and graphics.texture.TextureType.
     */
    public Image(ScreenCoords coords, float width, float height, TextureType textureType) {
        this.rect = new Rect(coords, width, height);
        this.textureType = textureType;
    }

    /**
     * Draws the image (including its texture) to the screen.
     */
    @Override
    public void draw() {
        TextureMap.get(this.textureType).bind();
        this.rect.drawModel();
    }

    /**
     * @return A reference of the image's rect.
     */
    public Rect getRect() {
        return this.rect;
    }
}

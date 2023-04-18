package game.gameobjects;

import lwjgl.graphics.texture.TextureMap;
import helper.coords.ScreenCoords;
import helper.coords.GridCoords;
import lwjgl.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

/**
 * A TileableGameObject differs from a GameObject in that the texture given repeats every Consts.GRID_PIXELS.
 * This is useful for walls and anything else that may need tiling (such as a floor).
 */
public class TileableGameObject extends GameObject {
    /** A list of rects to render the current texture to. */
    private final List<Rect> textureRects;

    /**
     * @param coords The top left corner of the wall.
     * @param width The width in ScreenCoords
     * @param height The height in ScreenCoords
     */
    public TileableGameObject(ScreenCoords coords, float width, float height, String texture) {
        super(coords, width, height, texture);

        this.textureRects = new ArrayList<>();
        GridCoords gridCoords = coords.toGridCoords();

        // i and j are in the units of grid coordinates
        for (int i = 0; i < ScreenCoords.distXtoGridCoords(width); i++) {
            for (int j = 0; j < ScreenCoords.distYtoGridCoords(height); j++) {
                this.textureRects.add(
                        new Rect(
                                new GridCoords(gridCoords.x + i, gridCoords.y + j).toScreenCoords(),
                                GridCoords.distXToScreenDist(1), GridCoords.distYToScreenDist(1)
                        )
                );
            }
        }
    }

    @Override
    public void draw() {
        // Draw each rect manually
        for (Rect rect : textureRects) {
            TextureMap.get(this.textureName).bind();
            rect.drawModel();
        }
    }
}

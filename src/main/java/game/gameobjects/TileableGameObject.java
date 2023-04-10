package game.gameobjects;

import graphics.texture.TextureMap;
import helper.coords.ScreenCoords;
import helper.coords.GridCoords;
import graphics.Rect;

import java.util.ArrayList;
import java.util.List;

/**
 * A TileableGameObject differs from a GameObject in that the texture given repeats every Consts.GRID_PIXELS.
 * This is useful for walls.
 */
public class TileableGameObject extends GameObject {
    List<Rect> textureRects;

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
                                GridCoords.distXToScreenCoords(1), GridCoords.distYToScreenCoords(1)
                        )
                );
            }
        }
    }

    @Override
    public void draw() {
        for (Rect rect : textureRects) {
            TextureMap.get(this.textureName).bind();
            rect.drawModel();
        }
    }
}

package helper.coords;

import helper.Consts;


/**
 * Coordinates on a grid. The grid size is equal to Consts.GRID_PIXELS, where Consts.GRID_PIXELS is in the units of
 * pixels.
 */
public class GridCoords extends Coords {
    public GridCoords(float x, float y) {
        super(x, y);
    }

    public static float distToPixelCoords(float dist) {
        return dist * Consts.GRID_PIXELS;
    }

    public static float distXToScreenDist(float dist) {
        return PixelCoords.distXToScreenDist(distToPixelCoords(dist));
    }

    public static float distYToScreenDist(float dist) {
        return PixelCoords.distYToScreenDist(distToPixelCoords(dist));
    }

    public PixelCoords toPixelCoords() {
        return new PixelCoords(this.x * Consts.GRID_PIXELS, this.y * Consts.GRID_PIXELS);
    }

    public ScreenCoords toScreenCoords() {
        return toPixelCoords().toScreenCoords();
    }
}

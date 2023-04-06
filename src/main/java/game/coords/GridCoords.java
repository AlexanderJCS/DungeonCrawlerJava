package game.coords;

import helper.Consts;

public class GridCoords extends Coords {
    public GridCoords(float x, float y) {
        super(x, y);
    }

    public PixelCoords toPixelCoords() {
        return new PixelCoords(this.x * Consts.GRID_PIXELS, this.y * Consts.GRID_PIXELS);
    }

    public ScreenCoords toScreenCoords() {
        return toPixelCoords().toScreenCoords();
    }

    public static float distToPixelCoords(float dist) {
        return dist * Consts.GRID_PIXELS;
    }

    public static float distXToScreenCoords(float dist) {
        return PixelCoords.distXToScreenDist(distToPixelCoords(dist));
    }

    public static float distYToScreenCoords(float dist) {
        return PixelCoords.distYToScreenDist(distToPixelCoords(dist));
    }
}

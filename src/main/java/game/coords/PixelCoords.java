package game.coords;

import helper.Consts;


/**
 * Pixel coordinates with the origin (0, 0) at the top left.
 */
public class PixelCoords extends Coords {
    public PixelCoords(float x, float y) {
        super(x, y);
    }

    /**
     * Convert pixel coordinates to screen coordinates.
     *
     * @return The ScreenCoords equivalent.
     */
    public ScreenCoords toScreenCoords() {
        float screenX = this.x / Consts.SCREEN_WIDTH * 2 - 1;
        float screenY = this.y / Consts.SCREEN_HEIGHT * 2 - 1;

        return new ScreenCoords(screenX, screenY);
    }

    /**
     * Convert PixelCoords to GridCoords
     * @return A GridCoords object
     */
    public GridCoords toGridCoords() {
        return new GridCoords(this.x / Consts.GRID_PIXELS, this.y / Consts.GRID_PIXELS);
    }

    /**
     * Convert a pixel distance value on the X axis to screen distance (which is what LWJGL uses).
     * @param dist Distance
     * @return ScreenCoords distance (float)
     */
    public static float distXToScreenDist(float dist) {
        return dist / Consts.SCREEN_WIDTH * 2;
    }

    /**
     * Convert a pixel distance value on the Y axis to screen distance (which is what LWJGL uses).
     * @param dist Distance
     * @return ScreenCoords distance (float)
     */
    public static float distYToScreenDist(float dist) {
        return dist / Consts.SCREEN_HEIGHT * 2;
    }

    /**
     * Convert a pixel distance to a grid distance.
     * @param dist Distance
     * @return GridCoords distance (float)
     */
    public static float distToGridDist(float dist) {
        return dist / Consts.GRID_PIXELS;
    }
}

package game.coords;


import helper.Consts;

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

    public GridCoords toGridCoords() {
        return new GridCoords(this.x / Consts.GRID_PIXELS, this.y / Consts.GRID_PIXELS);
    }

    public static float distXToScreenDist(float dist) {
        return dist / Consts.SCREEN_WIDTH * 2;
    }

    public static float distYToScreenDist(float dist) {
        return dist / Consts.SCREEN_HEIGHT * 2;
    }

    public static float distToGridDist(float dist) {
        return dist / Consts.GRID_PIXELS;
    }
}

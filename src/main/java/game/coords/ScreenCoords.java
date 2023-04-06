package game.coords;

import helper.Consts;

public class ScreenCoords extends Coords {
    public ScreenCoords(float x, float y) {
        super(x, y);
    }

    public PixelCoords toPixelCoords() {
        float pixCoordsX = (Consts.SCREEN_HEIGHT) * (this.x + 1) / 2;
        float pixCoordsY = (Consts.SCREEN_HEIGHT) * (this.y + 1) / 2;

        return new PixelCoords(pixCoordsX, pixCoordsY);
    }

    public ScreenCoords toGridCoords() {
        return this.toPixelCoords().toScreenCoords();
    }
}

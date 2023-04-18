package helper;

/**
 * Constants used globally.
 */
public class Consts {
    /** This number must be divisible by 32 for the screen to render properly */
    public static final int SCREEN_WIDTH = 1600;
    public static final int SEED = 0;

    // It is not recommended to modify values under this point
    public static final int SCREEN_HEIGHT = SCREEN_WIDTH * 9 / 16;
    public static final int GRID_PIXELS = SCREEN_WIDTH / 32;
    public static final int FPS_LIMIT = 1000;
}

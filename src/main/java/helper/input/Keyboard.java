package helper.input;

import static org.lwjgl.glfw.GLFW.*;

/**
 *
 */
public class Keyboard {
    private static long window;
    private static boolean spacePressedLastFrame;

    public static void init(long window) {
        Keyboard.window = window;
        Keyboard.spacePressedLastFrame = false;
    }

    public static boolean getKeyDown(int glfwKey) {
        return glfwGetKey(window, glfwKey) == 1;
    }

    /**
     * This needs to be called in the frame after all methods calling spacePressedThisFrame() are called.
     * In other words, this method needs to be called last in conjunction with Mouse.update().
     * Otherwise, it will always return false.
     */
    public static void update() {
        spacePressedLastFrame = getKeyDown(GLFW_KEY_SPACE);
    }

    /**
     * Requires the update Keyboard.update() method to be called in order to function correctly. Note that
     * Keyboard.update() needs to be called as the last update method in the game loop (in addition to Mouse.update()),
     * otherwise this method will always return false.
     *
     * @return True if the space bar is pressed down this frame.
     *         False if the space bar is held down for more than 1 frame
     */
    public static boolean spacePressedThisFrame() {
        return (!spacePressedLastFrame && getKeyDown(GLFW_KEY_SPACE));
    }
}

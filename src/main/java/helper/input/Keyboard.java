package helper.input;

import static org.lwjgl.glfw.GLFW.*;

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

    public static boolean spacePressedThisFrame() {
        return (!spacePressedLastFrame && getKeyDown(GLFW_KEY_SPACE));
    }
}

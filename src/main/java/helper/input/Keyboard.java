package helper.input;

import static org.lwjgl.glfw.GLFW.*;

public class Keyboard {
    private static long window;

    public static void init(long window) {
        Keyboard.window = window;
    }

    public static boolean getKeyDown(int glfwKey) {
        return glfwGetKey(window, glfwKey) == 1;
    }
}

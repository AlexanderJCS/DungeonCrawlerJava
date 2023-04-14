package helper;

import static org.lwjgl.glfw.GLFW.glfwGetTime;

public class Clock {
    private static double lastTick = 0;

    /**
     * Busy waits for the necessary time for the given fps.
     *
     * @param fps The fps the program should run at.
     */
    public static void busyTick(int fps) {
        // For future reference:
        // https://stackoverflow.com/questions/11498585/how-to-suspend-a-java-thread-for-a-small-period-of-time-like-100-nanoseconds

        double interval = 1.0 / fps;

        // Wait until the current time passed interval
        while (glfwGetTime() - lastTick < interval) {

        }

        lastTick = glfwGetTime();
    }
}

package game;

import helper.glfw.Window;
import helper.time.Clock;
import helper.coords.ScreenCoords;
import game.gameobjects.Player;
import game.world.Map;
import helper.Consts;
import helper.glfw.Keyboard;
import helper.glfw.Mouse;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * The main class for running the game.
 */
public class Game {
    // These two variables need to be static because the player needs to access the map and enemies need to access
    // the player.
    public static Map map = null;
    public static Player player = null;

    public static void init() throws IllegalStateException {
        Game.reset();
    }

    public static void reset() {
        map = new Map(0);
        player = new Player(new ScreenCoords(0, 0), 9f);
    }

    private static void update() {
        player.update();
        map.update();

        // These two methods need to be called last
        Keyboard.update();
        Mouse.update();
    }

    private static void draw() {
        map.draw();
        player.draw();
    }

    /**
     * The main game loop.
     */
    public static void run() {
        while (!glfwWindowShouldClose(Window.window)) {
            glfwPollEvents();
            glClear(GL_COLOR_BUFFER_BIT);  // make the screen black

            draw();
            update();

            // Exit the current game if the player lost
            if (player.healthContainer.getHealth() <= 0) {
                return;
            }

            glfwSwapBuffers(Window.window);
            Clock.busyTick(Consts.FPS);
        }

        glfwTerminate();
    }

    /**
     * Run the game.
     */
    public static void main(String[] args) {
        Keyboard.init();
        Mouse.init();
        Window.init();
        Game.init();

        while (true) {
            Game.run();
            Game.reset();
        }

        // glfwTerminate();
    }
}

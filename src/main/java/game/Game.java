package game;

import game.gameobjects.Player;
import game.world.Map;
import helper.Consts;
import helper.coords.ScreenCoords;
import helper.time.Clock;
import lwjgl.glfw.Keyboard;
import lwjgl.glfw.Mouse;
import lwjgl.glfw.Window;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

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
        map = new Map();
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
     *
     * @return Whether the player wants to exit the application.
     */
    public static boolean run() {
        while (!glfwWindowShouldClose(Window.window)) {
            glfwPollEvents();
            glClear(GL_COLOR_BUFFER_BIT);  // make the screen black

            draw();
            update();

            // Do not exit the current game if the player lost
            if (player.healthContainer.getHealth() <= 0) {
                return false;
            }

            glfwSwapBuffers(Window.window);
            Clock.busyTick(Consts.FPS_LIMIT);
        }

        return true;
    }

    /**
     * Run the game.
     */
    public static void main(String[] args) {
        Keyboard.init();
        Mouse.init();
        Window.init();
        Game.init();

        // While the player wants to play the game
        Game.run();

        // The below code is commented out until Game.reset() clears memory effectively.
//        while (!Game.run()) {
//            Game.reset();
//        }

        glfwTerminate();
    }
}

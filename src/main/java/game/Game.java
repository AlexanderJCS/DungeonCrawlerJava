package game;

import game.coords.ScreenCoords;
import game.gameobjects.Player;
import game.world.Map;
import helper.Consts;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.opengl.GL11.*;

/**
 * The main class for running the game.
 */
public class Game {
    private final long window;
    // These two variables need to be static because the player needs to access the map and enemies need to access
    // the player.
    public static Map map = null;
    public static Player player = null;

    public Game() throws IllegalStateException {
        if (!glfwInit()) {
            throw new IllegalStateException("GLFW could not initialize");
        }

        this.window = glfwCreateWindow(
                Consts.SCREEN_WIDTH, Consts.SCREEN_HEIGHT, "Dungeon Crawler", 0, 0);
        glfwShowWindow(this.window);

        glfwSwapInterval(1);  // enable vsync
        glfwMakeContextCurrent(this.window);
        GL.createCapabilities();
        glEnable(GL_TEXTURE_2D);

        map = new Map(0);
        player = new Player(new ScreenCoords(0, 0), 0.065f, this.window);
    }

    private void update() {
        player.update();
    }

    private void draw() {
        map.draw();
        player.draw();
    }

    /**
     * The main game loop.
     */
    public void run() {
        while (!glfwWindowShouldClose(this.window)) {
            glfwPollEvents();
            glClear(GL_COLOR_BUFFER_BIT);  // make the screen black

            this.draw();
            this.update();

            glfwSwapBuffers(this.window);
        }

        glfwTerminate();
    }

    /**
     * Run the game.
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }
}

package game;

import helper.coords.ScreenCoords;
import game.gameobjects.Player;
import game.world.Map;
import helper.Consts;
import helper.input.Keyboard;
import helper.input.Mouse;
import org.lwjgl.opengl.GL;

import java.io.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.opengl.GL11.*;

/**
 * The main class for running the game.
 */
public class Game implements Serializable {
    private final long window;
    // These two variables need to be static because the player needs to access the map and enemies need to access
    // the player.
    public static Map map = null;
    public static Player player = null;

    public Game() throws IllegalStateException {
        if (!glfwInit()) {
            throw new IllegalStateException("GLFW could not initialize");
        }

        glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);  // make the window non-resizeable
        this.window = glfwCreateWindow(
                Consts.SCREEN_WIDTH, Consts.SCREEN_HEIGHT, "Dungeon Crawler", 0, 0);
        glfwShowWindow(this.window);

        glfwSwapInterval(1);  // enable vsync
        glfwMakeContextCurrent(this.window);

        GL.createCapabilities();

        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        map = new Map(0);
        player = new Player(new ScreenCoords(0, 0), 0.065f);

        Keyboard.init(this.window);
        Mouse.init(this.window);
    }

    private void update() {
        player.update();
        map.update();

        // These two methods need to be called last
        Keyboard.update();
        Mouse.update();
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
        // this.save("src/main/resources/save.dc");
    }

    /**
     * Serializes the game
     * @param filepath The file to serialize the game to
     */
    public void save(String filepath) {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filepath))) {
            output.writeObject(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deserializes the game
     * @param filepath The file to deserialize the game from
     */
    public static Game load(String filepath) {
        Game returnValue;

        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(filepath))){
            returnValue = (Game) input.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return returnValue;
    }

    /**
     * Run the game.
     */
    public static void main(String[] args) {
        Game game = new Game();
//        Game game = Game.load("test");
        game.run();
    }
}

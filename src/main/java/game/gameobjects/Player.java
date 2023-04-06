package game.gameobjects;

import graphics.Rect;
import game.coords.GridCoords;
import game.coords.ScreenCoords;
import game.coords.PixelCoords;
import graphics.texture.Texture;
import graphics.texture.TexturedModel;
import helper.Consts;
import game.Game;

import static org.lwjgl.glfw.GLFW.*;

public class Player extends GameObject {
    private final long window;
    private final float speedX;
    private final float speedY;
    private final Texture texture;

    public Player(ScreenCoords coords, float speed, long window) {
        super(coords, PixelCoords.distXToScreenDist(Consts.GRID_PIXELS),
                PixelCoords.distYToScreenDist(Consts.GRID_PIXELS));

        this.rect = new Rect(rect);

        this.window = window;
        this.speedX = GridCoords.distXToScreenCoords(speed);
        this.speedY = GridCoords.distYToScreenCoords(speed);
        this.texture = new Texture("src/main/resources/test.png");
    }

    private void move() {
        if (glfwGetKey(window, GLFW_KEY_W) == 1) {
            this.move(0, speedY, Game.map.getShownRoom().getWallRects());
        } if (glfwGetKey(window, GLFW_KEY_S) == 1) {
            this.move(0, -speedY, Game.map.getShownRoom().getWallRects());
        } if (glfwGetKey(window, GLFW_KEY_A) == 1) {
            this.move(-speedX, 0, Game.map.getShownRoom().getWallRects());
        } if (glfwGetKey(window, GLFW_KEY_D) == 1) {
            this.move(speedX, 0, Game.map.getShownRoom().getWallRects());
        }
    }

    private void moveRooms() {
        float rectX = this.rect.getCenter().x;
        float rectY = this.rect.getCenter().y;

        if (rectX < -1 || rectX > 1 || rectY < -1 || rectY > 1) {
            Game.map.moveRooms((int) rectX, (int) rectY);
            this.rect.setCenter(0f, 0f);
        }
    }

    @Override
    public void update() {
        this.move();
        this.moveRooms();
    }

    @Override
    public void draw() {
        TexturedModel model = this.rect.toTexturedModel();

        texture.bind();
        model.render();
    }
}

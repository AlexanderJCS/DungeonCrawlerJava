package game.gameobjects;

import game.coords.GridCoords;
import game.coords.ScreenCoords;
import game.coords.PixelCoords;
import graphics.Rect;
import helper.Consts;
import game.Game;

import static org.lwjgl.glfw.GLFW.*;

public class Player extends GameObject {
    private final long window;
    // SpeedX and SpeedY need to be different because one ScreenCoord in the x direction is not always the same distance
    // as one ScreenCoord in the y direction.
    private final float speedX;
    private final float speedY;

    /** Used for rendering the player direction. */
    private String lastX;
    /** Used for rendering the player direction. */
    private String lastY;

    public Player(ScreenCoords coords, float speed, long window) {
        super(coords, PixelCoords.distXToScreenDist(Consts.GRID_PIXELS),
                PixelCoords.distYToScreenDist(Consts.GRID_PIXELS),
                "playerNortheast");

        this.lastY = "North";
        this.lastX = "east";

        this.window = window;
        this.speedX = GridCoords.distXToScreenCoords(speed);
        this.speedY = GridCoords.distYToScreenCoords(speed);
    }

    private void move() {
        Rect[] wallRects = Game.map.getShownRoom().getWallRects();

        if (glfwGetKey(window, GLFW_KEY_W) == 1) {  // Move up
            this.move(0, speedY, wallRects);
            this.lastY = "North";
        } if (glfwGetKey(window, GLFW_KEY_S) == 1) {  // Move down
            this.move(0, -speedY, wallRects);
            this.lastY = "South";
        } if (glfwGetKey(window, GLFW_KEY_A) == 1) {  // Move east
            this.move(-speedX, 0, wallRects);
            this.lastX = "west";
        } if (glfwGetKey(window, GLFW_KEY_D) == 1) {  // Move west
            this.move(speedX, 0, wallRects);
            this.lastX = "east";
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
    public void draw() {
        this.textureName = "player" + this.lastY + this.lastX;
        super.draw();
    }

    @Override
    public void update() {
        this.move();
        this.moveRooms();
    }
}

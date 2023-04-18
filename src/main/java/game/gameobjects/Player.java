package game.gameobjects;

import game.inventory.items.Item;
import helper.coords.Cardinal;
import helper.coords.GridCoords;
import helper.coords.ScreenCoords;
import helper.coords.PixelCoords;
import game.inventory.items.Heart;
import game.inventory.Inventory;
import graphics.Rect;
import helper.Consts;
import game.Game;
import helper.glfw.Keyboard;

import static org.lwjgl.glfw.GLFW.*;

public class Player extends GameObject {
    // SpeedX and SpeedY need to be different because one ScreenCoord in the x direction is not always the same distance
    // as one ScreenCoord in the y direction.
    private final float speedX;
    private final float speedY;

    public HealthContainer healthContainer;
    private final Inventory inventory;

    /** Used for sword */
    private Cardinal lastDir;

    /** Used for rendering the player direction. */
    private short lastX;
    /** Used for rendering the player direction. */
    private short lastY;

    /**
     * @param coords The bottom left coords of the player when it spawns in.
     * @param speed The player's speed in the units of grid coords per second.
     */
    public Player(ScreenCoords coords, float speed) {
        super(coords, PixelCoords.distXToScreenDist(Consts.GRID_PIXELS),
                PixelCoords.distYToScreenDist(Consts.GRID_PIXELS),
                "playerNortheast");

        this.lastY = 1;
        this.lastX = 1;

        this.speedX = GridCoords.distXToScreenDist(speed);
        this.speedY = GridCoords.distYToScreenDist(speed);

        this.inventory = new Inventory();
        this.inventory.addItem(new Heart());

        this.healthContainer = new HealthContainer(10, 0.5);
    }

    /**
     * Move the player.<br>
     * Controls: W (up), S (down), E (east), W (west)
     */
    private void moveToInput() {
        Rect[] wallRects = Game.map.getShownRoom().getWallRects();

        // Check if W, A, S, or D is pressed and move accordingly
        if (Keyboard.getKeyDown(GLFW_KEY_W)) {  // Move up
            this.fixedMove(0, speedY, wallRects);
            this.lastY = 1;
            this.lastDir = Cardinal.NORTH;
        } if (Keyboard.getKeyDown(GLFW_KEY_S)) {  // Move down
            this.fixedMove(0, -speedY, wallRects);
            this.lastY = -1;
            this.lastDir = Cardinal.SOUTH;
        } if (Keyboard.getKeyDown(GLFW_KEY_A)) {  // Move east
            this.fixedMove(-speedX, 0, wallRects);
            this.lastX = -1;
            this.lastDir = Cardinal.WEST;
        } if (Keyboard.getKeyDown(GLFW_KEY_D)) {  // Move west
            this.fixedMove(speedX, 0, wallRects);
            this.lastX = 1;
            this.lastDir = Cardinal.EAST;
        }
    }

    /**
     * If the player's rect center moves offscreen, move rooms in that direction.
     * For example, if the player moves offscreen to the north, move rooms in the north direction.
     */
    private void moveRooms() {
        float rectX = this.rect.getCenter().x;
        float rectY = this.rect.getCenter().y;

        // Move rooms
        if (rectX < -1 || rectX > 1 || rectY < -1 || rectY > 1) {
            Game.map.moveRooms((int) rectX, (int) rectY);

            // The purpose of (int) -rectX (or rectY) is to put the player on the right side of
            // the screen if they are on the left, and the left side if they are on the right
            this.rect.setCenter((int) -rectX * 0.9f, (int) -rectY * 0.9f);
        }
    }

    /**
     * Add an item to the player's inventory. Used for the Chest class.
     * @param item The item to add to the inventory
     */
    public void addToInventory(Item item) {
        this.inventory.addItem(item);
    }

    /**
     * Note that the return units (in this case ScreenCoords) does not mean anything.
     * @return The direction where positive x is east and positive y is north.
     */
    public Cardinal getLastDir() {
        return this.lastDir;
    }

    @Override
    public void draw() {
        this.textureName = "player" + (this.lastY == 1 ? "North" : "South") +
                (this.lastX == 1 ? "east" : "west");

        super.draw();
        this.inventory.draw();
        this.healthContainer.draw();
    }

    @Override
    public void update() {
        this.inventory.update();
        this.healthContainer.update();

        this.moveToInput();
        this.moveRooms();
    }
}

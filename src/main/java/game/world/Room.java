package game.world;

import game.gameobjects.Enemy;
import game.gameobjects.GameObject;
import game.gameobjects.Wall;
import helper.interfaces.Drawable;
import helper.interfaces.Updatable;
import lwjgl.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

/**
 * A room that the player is in.
 */
public class Room implements Drawable, Updatable {
    public final int x;
    public final int y;

    public final boolean north;
    public final boolean south;
    public final boolean east;
    public final boolean west;
    public final List<GameObject> gameObjects;
    private final Wall[] walls;
    boolean shown;

    /**
     * @param x           x coordinate of the room (room coordinate, which is NOT the same thing as a screen coordinate used to draw).
     * @param y           y coordinate of the room (room coordinate, which is NOT the same thing as a screen coordinate used to draw).
     * @param north       If the room has a north entrance.
     * @param south       If the room has a south entrance.
     * @param east        If the room has an east entrance.
     * @param west        If the room has a west entrance.
     * @param gameObjects A list of GameObjects inside the room.
     */
    public Room(int x, int y, boolean north, boolean south, boolean east, boolean west, List<GameObject> gameObjects) {
        this.shown = false;

        this.x = x;
        this.y = y;

        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;

        this.walls = RoomGenerator.generateWalls(north, south, east, west);
        // because for some reason it's an abstract immutable list otherwise, you need to init it as an arraylist
        this.gameObjects = new ArrayList<>(gameObjects);
    }

    /**
     * @return A list of wall rects. This is useful for wall collision.
     */
    public Rect[] getWallRects() {
        Rect[] returnArray = new Rect[walls.length];

        for (int i = 0; i < returnArray.length; i++) {
            returnArray[i] = walls[i].getRect();
        }

        return returnArray;
    }

    @Override
    public void draw() {
        for (Wall wall : walls) {
            wall.draw();
        }

        for (GameObject gameObject : this.gameObjects) {
            gameObject.draw();
        }
    }

    /**
     * Update all GameObjects inside
     */
    @Override
    public void update() {
        for (int i = this.gameObjects.size() - 1; i >= 0; i--) {
            GameObject gameObject = this.gameObjects.get(i);
            gameObject.update();

            // Remove enemies if their health is below 0
            if (gameObject.getClass() == Enemy.class) {
                Enemy enemy = (Enemy) gameObject;

                if (enemy.healthContainer.getHealth() == 0) {
                    this.gameObjects.get(i).getRect().close();
                    this.gameObjects.remove(i);
                }
            }
        }
    }
}

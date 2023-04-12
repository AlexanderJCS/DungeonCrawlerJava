package game.world;

import game.gameobjects.Enemy;
import game.gameobjects.GameObject;
import graphics.Rect;
import game.gameobjects.Wall;

import java.util.List;

/**
 * A room that the player is in.
 */
public class Room {
    public final int x;
    public final int y;

    public final boolean north;
    public final boolean south;
    public final boolean east;
    public final boolean west;

    private final Wall[] walls;
    private final List<GameObject> gameObjects;
    boolean shown;

    /**
     *
     * @param x x coordinate of the room (room coordinate, which is NOT the same thing as a screen coordinate used to draw).
     * @param y y coordinate of the room (room coordinate, which is NOT the same thing as a screen coordinate used to draw).
     * @param north If the room has a north entrance.
     * @param south If the room has a south entrance.
     * @param east If the room has an east entrance.
     * @param west If the room has a west entrance.
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
        this.gameObjects = gameObjects;
    }

    public Rect[] getWallRects() {
        Rect[] returnArray = new Rect[walls.length];

        for (int i = 0; i < returnArray.length; i++) {
            returnArray[i] = walls[i].getRect();
        }

        return returnArray;
    }

    public void draw() {
        for (Wall wall : walls) {
            wall.draw();
        }

        for (GameObject gameObject : this.gameObjects) {
            gameObject.draw();
        }
    }

    public void update() {
        for (int i = this.gameObjects.size() - 1; i >= 0; i--) {
            GameObject gameObject = this.gameObjects.get(i);
            gameObject.update();

            // Remove enemies if their health is below 0
            if (gameObject.getClass() == Enemy.class) {
                Enemy enemy = (Enemy) gameObject;

                if (enemy.healthContainer.getHealth() == 0) {
                    this.gameObjects.remove(i);
                }
            }
        }
    }
}

package game.world;

import graphics.Rect;
import game.gameobjects.Wall;

public class Room {
    public final int x;
    public final int y;

    public final boolean north;
    public final boolean south;
    public final boolean east;
    public final boolean west;

    private final Wall[] walls;
    boolean shown;

    public Room(int x, int y, boolean north, boolean south, boolean east, boolean west) {
        this.shown = false;

        this.x = x;
        this.y = y;

        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;

        this.walls = RoomGenerator.generateWalls(north, south, east, west);
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
    }
}

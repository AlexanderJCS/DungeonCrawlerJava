package game.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Contains multiple rooms that the player can travel to.
 */
public class Map {
    private final List<Room> rooms;
    private Room shownRoom;
    private final Random random;

    /**
     * @param seed Random number generator seed
     */
    public Map(int seed) {
        this.random = new Random(seed);

        this.rooms = new ArrayList<>(List.of(
                new Room(0, 0, true, false, false, false,
                        RoomGenerator.generateGameObjects(this.random, 0))
        ));

        this.setShownRoom(0, 0);
    }

    /**
     * Draws the shown room.
     */
    public void draw() {
        this.shownRoom.draw();
    }

    public void update() {
        this.shownRoom.update();
    }

    /**
     * @return The currently shown room.
     */
    public Room getShownRoom() {
        return shownRoom;
    }

    /**
     * Sets the shown room.
     * @param x The room coordinate x.
     * @param y The room coordinate y.
     */
    public void setShownRoom(int x, int y) {
        if (this.shownRoom != null) {
            this.shownRoom.shown = false;
        }

        Room room = this.roomAt(x, y);

        if (room == null) {
            generateRoomAt(x, y);
            room = this.roomAt(x, y);
        }
        room.shown = true;
        this.shownRoom = room;
    }

    /**
     * Change the shown room number by a certain amount. Useful for a player moving to the next room.
     * @param deltaX The amount to change on the x direction.
     * @param deltaY The amount to change on the y direction.
     */
    public void moveRooms(int deltaX, int deltaY) {
        this.setShownRoom(
                this.shownRoom.x + deltaX,
                this.shownRoom.y + deltaY
        );
    }

    /**
     * Returns the room at the specified coordinates. Null if no room is found at those coords.
     * @param x The x coordinate of the room
     * @param y The y coordinate of the room
     * @return The room. Null if no room is found at those coordinates.
     */
    public Room roomAt(int x, int y) {
        for (Room room : rooms) {
            if (room.x == x && room.y == y) {
                return room;
            }
        }

        return null;
    }

    /**
     * Generate a room at a set of room coordinates. Entrances get exponentially rarer the farther away you
     * go from the origin.
     * @param x The x coordinate of the room to generate.
     * @param y The y coordinate of the room to generate.
     */
    private void generateRoomAt(int x, int y) {
        double dist = Math.sqrt(x * x + y * y);
        double connectorChance = 2 / dist;

        boolean north = this.random.nextDouble() < connectorChance;
        boolean south = this.random.nextDouble() < connectorChance;
        boolean east = this.random.nextDouble() < connectorChance;
        boolean west = this.random.nextDouble() < connectorChance;

        Room tempRoom = this.roomAt(x, y + 1);
        if (tempRoom != null) {
            north = tempRoom.south;
        }

        tempRoom = this.roomAt(x, y - 1);
        if (tempRoom != null) {
            south = tempRoom.north;
        }

        tempRoom = this.roomAt(x + 1, y);
        if (tempRoom != null) {
            east = tempRoom.west;
        }

        tempRoom = this.roomAt(x - 1, y);
        if (tempRoom != null) {
            west = tempRoom.east;
        }

        this.rooms.add(new Room(x, y, north, south, east, west, RoomGenerator.generateGameObjects(this.random, dist)));
    }
}

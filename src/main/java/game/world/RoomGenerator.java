package game.world;

import game.gameobjects.Wall;
import game.coords.GridCoords;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Helper class for the Room class. Used to get an array of Walls.
 */
public class RoomGenerator {
    public static Wall[] generateWalls(boolean north, boolean south, boolean east, boolean west) {
        List<Wall> walls = new ArrayList<>(Arrays.asList(
                // Northwest wall
                new Wall(new GridCoords(0, 0).toScreenCoords(), GridCoords.distXToScreenCoords(13), GridCoords.distYToScreenCoords(6)),
                // Northeast wall
                new Wall(new GridCoords(19, 0).toScreenCoords(), GridCoords.distXToScreenCoords(13), GridCoords.distYToScreenCoords(6)),
                // Southwest wall
                new Wall(new GridCoords(0, 12).toScreenCoords(), GridCoords.distXToScreenCoords(13), GridCoords.distYToScreenCoords(6)),
                // Southeast wall
                new Wall(new GridCoords(19, 12).toScreenCoords(), GridCoords.distXToScreenCoords(13), GridCoords.distYToScreenCoords(6))
        ));

        // North entrance
        if (!north) {
            walls.add(
                    new Wall(new GridCoords(13, 15).toScreenCoords(), GridCoords.distXToScreenCoords(6), GridCoords.distYToScreenCoords(4))
            );
        }

        // South entrance
        if (!south) {
            walls.add(
                    new Wall(new GridCoords(13, 0).toScreenCoords(), GridCoords.distXToScreenCoords(6), GridCoords.distYToScreenCoords(4))
            );
        }

        // East entrance
        if (!east) {
            walls.add(
                    new Wall(new GridCoords(22, 6).toScreenCoords(), GridCoords.distXToScreenCoords(10), GridCoords.distYToScreenCoords(6))
            );
        }

        // West entrance
        if (!west) {
            walls.add(
                    new Wall(new GridCoords(0, 6).toScreenCoords(), GridCoords.distXToScreenCoords(10), GridCoords.distYToScreenCoords(6))
            );
        }

        return walls.toArray(Wall[]::new);
    }
}

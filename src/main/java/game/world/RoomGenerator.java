package game.world;

import game.gameobjects.Chest;
import game.gameobjects.Enemy;
import game.gameobjects.GameObject;
import game.gameobjects.Wall;
import game.inventory.items.Heart;
import game.inventory.items.Item;
import game.inventory.items.Sword;
import helper.coords.GridCoords;
import helper.coords.ScreenCoords;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

/**
 * Helper class for the Room class. Used to get an array of Walls and a list of GameObjects.
 */
public class RoomGenerator {
    /**
     * Generate the wall classes given the north, south, east, and west entrances.
     *
     * @param north If the north entrance exists
     * @param south If the south entrance exists
     * @param east  If the east entrance exists
     * @param west  If the west entrance exists
     * @return An array of wall rects to use in the Room.
     */
    public static Wall[] generateWalls(boolean north, boolean south, boolean east, boolean west) {
        List<Wall> walls = new ArrayList<>(Arrays.asList(
                // Northwest wall
                new Wall(new GridCoords(0, 0).toScreenCoords(), GridCoords.distXToScreenDist(13), GridCoords.distYToScreenDist(6)),
                // Northeast wall
                new Wall(new GridCoords(19, 0).toScreenCoords(), GridCoords.distXToScreenDist(13), GridCoords.distYToScreenDist(6)),
                // Southwest wall
                new Wall(new GridCoords(0, 12).toScreenCoords(), GridCoords.distXToScreenDist(13), GridCoords.distYToScreenDist(6)),
                // Southeast wall
                new Wall(new GridCoords(19, 12).toScreenCoords(), GridCoords.distXToScreenDist(13), GridCoords.distYToScreenDist(6))
        ));

        // North entrance
        if (!north) {
            walls.add(
                    new Wall(new GridCoords(13, 15).toScreenCoords(), GridCoords.distXToScreenDist(6), GridCoords.distYToScreenDist(4))
            );
        }

        // South entrance
        if (!south) {
            walls.add(
                    new Wall(new GridCoords(13, 0).toScreenCoords(), GridCoords.distXToScreenDist(6), GridCoords.distYToScreenDist(3))
            );
        }

        // East entrance
        if (!east) {
            walls.add(
                    new Wall(new GridCoords(22, 6).toScreenCoords(), GridCoords.distXToScreenDist(10), GridCoords.distYToScreenDist(6))
            );
        }

        // West entrance
        if (!west) {
            walls.add(
                    new Wall(new GridCoords(0, 6).toScreenCoords(), GridCoords.distXToScreenDist(10), GridCoords.distYToScreenDist(6))
            );
        }

        return walls.toArray(Wall[]::new);
    }

    private static List<GameObject> generateChests(Random random, double dist) {
        double chestProb = 2 / dist;

        Item[] chestItems = new Item[]{
                new Heart(),
                new Sword()
        };

        if (random.nextDouble() > chestProb) {
            return new ArrayList<>(List.of(
                    new Chest(
                            new GridCoords(10, 6).toScreenCoords(),
                            chestItems[random.nextInt(chestItems.length)])
            ));
        }

        return new ArrayList<>();
    }

    private static List<GameObject> generateEnemies(Random random, double dist) {
        double enemyProb = 1 / dist;

        if (random.nextDouble() > enemyProb) {
            return new ArrayList<>(List.of(
                    new Enemy(new ScreenCoords(0, 0), 5f)
            ));
        }

        return new ArrayList<>();
    }

    /**
     * Generates game objects for a room.
     *
     * @param random The Random object
     * @param dist   The distance from 0, 0
     * @return A list of GameObjects (chests, enemies, etc) to incorporate in the room. Note this does not include walls
     */
    public static List<GameObject> generateGameObjects(Random random, double dist) {
        return Stream.concat(
                generateChests(random, dist).stream(),
                generateEnemies(random, dist).stream()
        ).toList();
    }
}

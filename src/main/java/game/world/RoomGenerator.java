package game.world;

import game.gameobjects.Chest;
import game.gameobjects.Enemy;
import game.gameobjects.GameObject;
import game.gameobjects.Wall;
import game.inventory.items.Heart;
import game.inventory.items.Item;
import helper.coords.GridCoords;
import helper.coords.ScreenCoords;

import java.util.*;
import java.util.stream.Stream;

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
                    new Wall(new GridCoords(13, 0).toScreenCoords(), GridCoords.distXToScreenCoords(6), GridCoords.distYToScreenCoords(3))
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

    private static List<GameObject> generateChests(Random random, double dist) {
        double chestProb = 2 / dist;

        Item[] chestItems = new Item[]{
                new Heart()
        };

        if (random.nextDouble() > chestProb) {
            return Collections.singletonList(
                    new Chest(
                            new GridCoords(10, 6).toScreenCoords(),
                            chestItems[random.nextInt(chestItems.length)]
            ));
        }

        return new ArrayList<>();
    }

    private static List<GameObject> generateEnemies(Random random, double dist) {
        double enemyProb = 1 / dist;

        if (random.nextDouble() > enemyProb) {
            return Collections.singletonList(
                    new Enemy(new ScreenCoords(0, 0), 0.03f)
            );
        }

        return new ArrayList<>();
    }

    public static List<GameObject> generateGameObjects(Random random, double dist) {
        // For future reference: https://stackoverflow.com/questions/189559/how-do-i-join-two-lists-in-java
        return Stream.concat(
                generateChests(random, dist).stream(),
                generateEnemies(random, dist).stream()
        ).toList();
    }
}

package game.inventory.items;

import game.Game;
import game.gameobjects.Enemy;
import game.gameobjects.GameObject;
import helper.coords.Cardinal;
import helper.coords.GridCoords;
import helper.coords.ScreenCoords;
import lwjgl.graphics.Rect;

/**
 * Damages an enemy upon hitting it.
 */
public class Sword extends UsableItem {
    public Sword() {
        super(ItemType.WEAPON, "sword", 3);
    }

    @Override
    public void use() {
        Rect playerRect = Game.player.getRect();
        ScreenCoords playerDir = Cardinal.cardinalToDir(Game.player.getLastDir());

        // Get a rect of the area that the sword will apply damage to.
        Rect damageRect = new Rect(
                new ScreenCoords(
                        playerRect.x1 + GridCoords.distXToScreenDist(playerDir.x),
                        playerRect.y1 + GridCoords.distYToScreenDist(playerDir.y)
                ),
                GridCoords.distXToScreenDist(1),
                GridCoords.distYToScreenDist(1)
        );

        // Damage all enemies that it hit
        for (GameObject gameObject : Game.map.getShownRoom().gameObjects) {
            if (gameObject.getClass() != Enemy.class) {
                continue;
            }

            Enemy enemy = (Enemy) gameObject;

            if (damageRect.otherRectInside(enemy.getRect())) {
                enemy.healthContainer.takeDamage(2);
                super.use();  // only use durability if the sword hit an enemy
            }
        }
    }
}

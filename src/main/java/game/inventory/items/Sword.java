package game.inventory.items;

import game.Game;
import game.gameobjects.Enemy;
import game.gameobjects.GameObject;
import graphics.Rect;
import helper.coords.Cardinal;
import helper.coords.GridCoords;
import helper.coords.ScreenCoords;

public class Sword extends UsableItem {
    public Sword() {
        super(ItemType.WEAPON, "sword", 3);
    }

    @Override
    public void use() {
        Rect playerRect = Game.player.getRect();
        ScreenCoords playerDir = Cardinal.cardinalToDir(Game.player.getLastDir());

        Rect damageRect = new Rect(
                new ScreenCoords(
                        playerRect.x1 + GridCoords.distXToScreenCoords(playerDir.x),
                        playerRect.y1 + GridCoords.distYToScreenCoords(playerDir.y)
                ),
                GridCoords.distXToScreenCoords(1) ,
                GridCoords.distYToScreenCoords(1)
        );

        for (GameObject gameObject : Game.map.getShownRoom().gameObjects) {
            if (gameObject.getClass() != Enemy.class) {
                continue;
            }

            Enemy enemy = (Enemy) gameObject;

            if (damageRect.collidesWith(enemy.getRect())) {
                enemy.healthContainer.takeDamage(2);
                super.use();  // only use durability if the sword hit an enemy
            }
        }
    }
}

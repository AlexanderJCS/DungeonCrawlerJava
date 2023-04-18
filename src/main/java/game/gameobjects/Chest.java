package game.gameobjects;

import game.Game;
import game.inventory.items.Item;
import helper.coords.GridCoords;
import helper.coords.ScreenCoords;

public class Chest extends GameObject {
    private Item item;

    /**
     * @param coords The bottom left coordinate of the chest.
     * @param item   The item the chest should hold
     */
    public Chest(ScreenCoords coords, Item item) {
        super(coords, GridCoords.distXToScreenDist(1), GridCoords.distYToScreenDist(1), "chestClosed");
        this.item = item;
    }

    @Override
    public void update() {
        if (this.item == null) {
            return;
        }

        GridCoords center = Game.player.getRect().getCenter().toGridCoords();
        GridCoords thisCenter = this.rect.getCenter().toGridCoords();

        // units: GridCoords
        double dist = Math.sqrt(
                Math.pow(center.x - thisCenter.x, 2) +
                        Math.pow(center.y - thisCenter.y, 2)
        );

        if (dist < 1.5) {
            Game.player.addToInventory(this.item);
            this.item = null;
            this.textureName = "chestOpen";
        }
    }
}

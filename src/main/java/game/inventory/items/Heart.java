package game.inventory.items;

import game.Game;

public class Heart extends UsableItem {
    public Heart() {
        // ItemType.WEAPON is temporary until the bug where ItemType.CONSUMABLES isn't being drawn is fixed
        super(ItemType.CONSUMABLE, "heart", 1);
    }

    @Override
    public void use() {
        super.use();
        this.durability--;
        Game.player.healthContainer.takeDamage(-2);  // health the player by 2 HP
    }
}

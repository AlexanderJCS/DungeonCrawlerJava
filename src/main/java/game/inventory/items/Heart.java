package game.inventory.items;

import game.Game;
import lwjgl.graphics.texture.TextureType;

/**
 * Heals the player when used.
 */
public class Heart extends UsableItem {
    public Heart() {
        // ItemType.WEAPON is temporary until the bug where ItemType.CONSUMABLES isn't being drawn is fixed
        super(ItemType.CONSUMABLE, TextureType.HEART, 1);
    }

    @Override
    public void use() {
        super.use();
        Game.player.healthContainer.takeDamage(-2);  // health the player by 2 HP
    }
}

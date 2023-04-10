package game.inventory;

public class Heart extends UsableItem {
    public Heart() {
        // ItemType.WEAPON is temporary until the bug where ItemType.CONSUMABLES isn't being drawn is fixed
        super(ItemType.CONSUMABLE, "heart", 1);
    }

    @Override
    public void use() {
        this.durability--;
    }
}

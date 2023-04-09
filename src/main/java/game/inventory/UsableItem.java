package game.inventory;

public class UsableItem extends Item {
    public UsableItem(ItemType type, String texture) {
        super(type, texture);
    }

    public void use() {}
}

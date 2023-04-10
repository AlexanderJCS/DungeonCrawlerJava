package game.inventory;

public abstract class UsableItem extends Item {
    protected int durability;

    public UsableItem(ItemType type, String texture, int durability) {
        super(type, texture);

        this.durability = durability;
    }

    public int getDurability() {
        return this.durability;
    }

    public abstract void use();
}

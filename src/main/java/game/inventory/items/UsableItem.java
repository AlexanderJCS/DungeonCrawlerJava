package game.inventory.items;

import game.gameobjects.Image;
import helper.coords.GridCoords;
import helper.coords.ScreenCoords;

/**
 * An Item that can has a use() method.
 */
public class UsableItem extends Item {
    private final int maxDurability;
    private final Image barImage;
    protected int durability;

    public UsableItem(ItemType type, String texture, int durability) {
        super(type, texture);

        this.maxDurability = durability;
        this.durability = durability;
        this.barImage = new Image(new ScreenCoords(-1, -1),
                GridCoords.distXToScreenDist(1), GridCoords.distYToScreenDist(0.1f), "green");
    }

    public int getDurability() {
        return this.durability;
    }

    @Override
    public void draw() {
        super.draw();
        this.barImage.draw();
    }

    @Override
    public void setPos(ScreenCoords newPos) {
        super.setPos(newPos);
        this.barImage.getRect().setPos(new ScreenCoords(newPos.x, newPos.y + GridCoords.distYToScreenDist(-0.4f)));
    }

    public void use() {
        this.durability--;
        float durabilityLevel = (float) this.durability / this.maxDurability;
        this.barImage.getRect().setWidthHeight(
                GridCoords.distXToScreenDist(durabilityLevel), this.barImage.getRect().height
        );

        if (durabilityLevel >= 0.667) {
            this.barImage.textureName = "green";
        } else if (durabilityLevel >= 0.334) {
            this.barImage.textureName = "yellow";
        } else {
            this.barImage.textureName = "red";
        }
    }
}

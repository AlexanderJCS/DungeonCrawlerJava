package game.inventory;

import game.gameobjects.Image;
import helper.coords.GridCoords;
import helper.coords.ScreenCoords;

public class UsableItem extends Item {
    protected int durability;
    private final int maxDurability;
    private final Image barImage;

    public UsableItem(ItemType type, String texture, int durability) {
        super(type, texture);

        this.maxDurability = durability;
        this.durability = durability;
        this.barImage = new Image(new ScreenCoords(-1, -1),
                GridCoords.distXToScreenCoords(1), GridCoords.distYToScreenCoords(0.1f), "green");
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
        this.barImage.getRect().setPos(new ScreenCoords(newPos.x, newPos.y + GridCoords.distYToScreenCoords(-0.4f)));
    }

    public void use() {
        float durabilityLevel = (float) this.durability / this.maxDurability;
        this.barImage.getRect().setWidthHeight(
                GridCoords.distXToScreenCoords(durabilityLevel), this.barImage.getRect().height
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

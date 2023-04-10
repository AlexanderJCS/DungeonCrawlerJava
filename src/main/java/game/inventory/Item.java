package game.inventory;

import helper.coords.GridCoords;
import helper.coords.ScreenCoords;
import game.gameobjects.Image;

public class Item extends Image {
    public final ItemType type;

    public Item(ItemType type, String texture) {
        super(new ScreenCoords(-1, -1), GridCoords.distXToScreenCoords(1), GridCoords.distYToScreenCoords(1), texture);
        this.type = type;
    }

    public void setPos(ScreenCoords newPos) {
        this.rect.setPos(newPos);
    }
}

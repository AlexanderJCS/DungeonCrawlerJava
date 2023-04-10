package game.inventory;

import game.coords.GridCoords;
import game.coords.ScreenCoords;
import game.gameobjects.Image;
import graphics.Rect;

public class Item extends Image {
    public final ItemType type;

    public Item(ItemType type, String texture) {
        super(new ScreenCoords(-1, -1), GridCoords.distXToScreenCoords(1), GridCoords.distYToScreenCoords(1), texture);
        this.type = type;
    }

    Rect getRectReference() {
        return this.rect;
    }
}

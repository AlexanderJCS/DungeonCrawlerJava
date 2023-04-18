package game.inventory.items;

import game.gameobjects.Image;
import helper.coords.GridCoords;
import helper.coords.ScreenCoords;
import lwjgl.graphics.Rect;

/**
 * The basic item class. This can be held in the inventory or be drawn to the screen.
 * This class is separate from UsableItem in case I want to add armor/other passive items in the future.
 */
public class Item {
    public final ItemType type;
    private final Image image;

    public Item(ItemType type, String texture) {
        this.image = new Image(
                new ScreenCoords(-1, -1),
                GridCoords.distXToScreenDist(1),
                GridCoords.distYToScreenDist(1),
                texture
        );

        this.type = type;
    }

    public void setPos(ScreenCoords newPos) {
        this.image.getRect().setPos(newPos);
    }

    public void draw() {
        this.image.draw();
    }

    public Rect getRect() {
        return this.image.getRect();
    }
}

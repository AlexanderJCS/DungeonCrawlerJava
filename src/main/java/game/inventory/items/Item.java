package game.inventory.items;

import lwjgl.graphics.Rect;
import helper.coords.GridCoords;
import helper.coords.ScreenCoords;
import game.gameobjects.Image;

/**
 * The basic item class. This can be held in the inventory or be drawn to the screen.
 */
public class Item {
    private final Image image;
    public final ItemType type;

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

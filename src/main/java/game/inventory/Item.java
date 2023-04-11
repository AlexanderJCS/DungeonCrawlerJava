package game.inventory;

import graphics.Rect;
import helper.coords.GridCoords;
import helper.coords.ScreenCoords;
import game.gameobjects.Image;

public class Item {
    private final Image image;
    public final ItemType type;

    public Item(ItemType type, String texture) {
        this.image = new Image(
                new ScreenCoords(-1, -1),
                GridCoords.distXToScreenCoords(1),
                GridCoords.distYToScreenCoords(1),
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

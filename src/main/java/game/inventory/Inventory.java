package game.inventory;

import game.coords.GridCoords;
import game.coords.PixelCoords;
import game.coords.ScreenCoords;
import game.gameobjects.Image;
import helper.Consts;
import helper.input.Keyboard;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;

public class Inventory {
    private static final Image transparent = new Image(new ScreenCoords(-1, -1), 2, 2,
            "transparent");

    private static final int OPEN_INVENTORY_KEY = GLFW_KEY_TAB;

    private final List<UsableItem> consumables;
    private final List<UsableItem> weapons;

    public Inventory() {
        this.consumables = new ArrayList<>();
        this.weapons = new ArrayList<>();
    }

    public void addItem(Item item) {
        try {
            if (item.type == ItemType.WEAPON) {
                this.weapons.add((UsableItem) item);
            }
        } catch (RuntimeException e) {  // if the item could not be cast to UsableItem
            System.err.println("Warning: could not cast item " + item.toString() + " to UsableItem");
            e.printStackTrace();
        }
    }

    private static void drawItemList(List<Item> items, float yOffset) {
        PixelCoords origin = new PixelCoords(
                Consts.SCREEN_WIDTH / 2f - Consts.GRID_PIXELS * 4 - Consts.GRID_PIXELS / 2f,
                Consts.SCREEN_HEIGHT / 2f + Consts.GRID_PIXELS / 2f + yOffset
        );

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);

            item.getRectReference().setPos(
                    new PixelCoords(origin.x + GridCoords.distToPixelCoords(i), origin.y).toScreenCoords()
            );

            item.draw();
        }
    }

    @SuppressWarnings("unchecked")
    public void draw() {
        if (Keyboard.getKeyDown(OPEN_INVENTORY_KEY)) {  // if inventory is open
            transparent.draw();
            drawItemList((List<Item>)(List<?>) this.consumables, -100);
            drawItemList((List<Item>)(List<?>) this.weapons, 0);
        }
    }
}

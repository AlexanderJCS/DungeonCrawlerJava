package game.inventory;

import game.inventory.items.Item;
import game.inventory.items.ItemType;
import game.inventory.items.UsableItem;
import helper.coords.GridCoords;
import helper.coords.PixelCoords;
import helper.coords.ScreenCoords;
import game.gameobjects.Image;
import helper.Consts;
import helper.input.Keyboard;
import helper.input.Mouse;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;

public class Inventory {
    private static final Image transparent = new Image(new ScreenCoords(-1, -1), 2, 2,
            "transparent");
    private static final Image selectedItemBg = new Image(new GridCoords(0.75f, 15f).toScreenCoords(),
            GridCoords.distXToScreenCoords(1.5f), GridCoords.distYToScreenCoords(2.5f), "transparent");

    private static final int OPEN_INVENTORY_KEY = GLFW_KEY_TAB;

    private final List<UsableItem> consumables;
    private final List<UsableItem> weapons;

    private UsableItem selectedItem;

    public Inventory() {
        this.consumables = new ArrayList<>();
        this.weapons = new ArrayList<>();
    }

    private List<UsableItem> getUsableItems() {
        List<UsableItem> returnList = new ArrayList<>(weapons);
        returnList.addAll(consumables);

        return returnList;
    }

    public void addItem(Item item) {
        try {
            if (item.type == ItemType.WEAPON) {
                this.weapons.add((UsableItem) item);
            } else if (item.type == ItemType.CONSUMABLE) {
                this.consumables.add((UsableItem) item);
            }
        } catch (RuntimeException e) {  // if the item could not be cast to UsableItem
            throw new IllegalArgumentException("Could not cast type Item to type UsableItem", e);
        }
    }

    private static void drawItemList(List<Item> items, float yOffset) {
        PixelCoords origin = new PixelCoords(
                Consts.SCREEN_WIDTH / 2f - Consts.GRID_PIXELS * 4 - Consts.GRID_PIXELS / 2f,
                Consts.SCREEN_HEIGHT / 2f + Consts.GRID_PIXELS / 2f + yOffset
        );

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);

            item.setPos(
                    new PixelCoords(origin.x + GridCoords.distToPixelCoords(i), origin.y).toScreenCoords()
            );

            item.draw();
        }
    }

    private void drawSelectedItem() {
        this.selectedItem.setPos(new GridCoords(1, 16).toScreenCoords());
        this.selectedItem.draw();
    }

    @SuppressWarnings("unchecked")
    public void draw() {
        if (this.selectedItem != null) {
            selectedItemBg.draw();
            this.drawSelectedItem();
        }

        if (Keyboard.getKeyDown(OPEN_INVENTORY_KEY)) {  // if inventory is open
            // draw the inventory
            transparent.draw();
            drawItemList((List<Item>)(List<?>) this.consumables, 100f);
            drawItemList((List<Item>)(List<?>) this.weapons, 0);
        }
    }

    private void updateInventoryOpen() {
        ScreenCoords mousePos = Mouse.getMousePos();

        for (UsableItem item : getUsableItems()) {
            if (!item.getRect().pointInsideRect(mousePos)) {
                continue;
            }

            if (this.selectedItem == item) {
                this.selectedItem = null;
            } else {
                this.selectedItem = item;
            }
        }
    }

    private void useItem() {
        this.selectedItem.use();

        if (this.selectedItem.getDurability() <= 0) {
            this.weapons.remove(this.selectedItem);
            this.consumables.remove(this.selectedItem);
            this.selectedItem = null;
        }
    }

    public void update() {
        // If the inventory is open and the user clicked
        if (Mouse.mouseDownThisFrame() && Keyboard.getKeyDown(OPEN_INVENTORY_KEY)) {
            this.updateInventoryOpen();
        }

        // Check if the item is used
        if (Keyboard.spacePressedThisFrame() && this.selectedItem != null) {
            this.useItem();
        }
    }
}

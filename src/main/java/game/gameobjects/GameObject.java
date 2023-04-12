package game.gameobjects;

import graphics.*;
import helper.coords.PixelCoords;
import helper.coords.ScreenCoords;

public class GameObject extends Image {
    public GameObject(ScreenCoords coords, float width, float height, String textureName) {
        super(coords, width, height, textureName);
    }

    /**
     * @param colliders Rectangles to collide with.
     * @return If this is colliding with a collider inside colliders.
     */
    public boolean collides(Rect[] colliders) {
        for (Rect collider : colliders) {
            if (collider.collidesWith(this.rect)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Move an object by a certain amount while taking into account colliders.
     * @param x The amount to shift in the x direction.
     * @param y The amount to shift in the y direction
     * @param colliders Objects to not collide with. Note that if the GameObject shifts too much, it can pass through the collider.
     */
    public void move(float x, float y, Rect[] colliders) {
        this.rect.shift(x, 0);

        // Move backwards until you are no longer colliding
        while (this.collides(colliders)) {
            float moveDist = PixelCoords.distXToScreenDist(1);
            this.rect.shift(x > 0 ? -moveDist : moveDist, 0);
        }

        this.rect.shift(0, y);

        // Move backwards until you are no longer colliding
        while (this.collides(colliders)) {
            float moveDist = PixelCoords.distYToScreenDist(1);
            this.rect.shift(0, y > 0 ? -moveDist : moveDist);
        }
    }

    /**
     * Update method that will be called once every frame. Empty by default.
     */
    public void update() {}
}

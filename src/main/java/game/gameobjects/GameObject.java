package game.gameobjects;

import helper.coords.PixelCoords;
import helper.coords.ScreenCoords;
import helper.time.Clock;
import lwjgl.graphics.Rect;

/**
 * The GameObject class, which is used to hold everything "physical" (i.e. everything except items).
 */
public class GameObject extends Image {
    /**
     * @param coords      The bottom left coordinate of the GameObject
     * @param width       The width of the game object in ScreenCoords
     * @param height      The height of the game object in ScreenCoords
     * @param textureName The texture name. This is not the same as the texture path. See graphics.texture.TextureMap.
     */
    public GameObject(ScreenCoords coords, float width, float height, String textureName) {
        super(coords, width, height, textureName);
    }

    /**
     * @param colliders Rectangles to collide with.
     * @return If this is colliding with a collider inside colliders.
     */
    public boolean collides(Rect[] colliders) {
        for (Rect collider : colliders) {
            if (collider.otherRectInside(this.rect)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Move an object by a certain amount while taking into account colliders.
     *
     * @param x         The amount to shift in the x direction.
     * @param y         The amount to shift in the y direction
     * @param colliders Objects to not collide with. Note that if the GameObject shifts too much,
     *                  it can pass through the collider.
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
     * Movement is framerate independent. In order words, an application running at 20 fps will have the same movement
     * speed as an application running at 100 fps.
     * <p>
     * Note that speed values may need to be adjusted when switching from move() to fixedMove().
     */
    public void fixedMove(float x, float y, Rect[] colliders) {
        float moveMultiplier = (float) Clock.getTimeDelta();
        this.move(x * moveMultiplier, y * moveMultiplier, colliders);
    }

    /**
     * Update method that will be called once every frame. Empty by default.
     */
    public void update() {
    }
}

package game.gameobjects;

import graphics.*;
import game.coords.PixelCoords;
import game.coords.ScreenCoords;
import graphics.texture.TextureMap;

public abstract class GameObject {
    protected Rect rect;
    protected String textureName;

    public GameObject(ScreenCoords coords, float width, float height, String textureName) {
        this.rect = new Rect(coords, width, height);
        this.textureName = textureName;
    }

    public void draw() {
        TextureMap.get(this.textureName).bind();
        this.rect.toTexturedModel().render();
    }

    public boolean collides(Rect[] colliders) {
        for (Rect collider : colliders) {
            if (collider.collidesWith(this.rect)) {
                return true;
            }
        }

        return false;
    }

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

    public Rect getRect() {
        return new Rect(this.rect);
    }

    public void update() {}
}

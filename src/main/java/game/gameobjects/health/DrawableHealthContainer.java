package game.gameobjects.health;

import game.gameobjects.Image;
import helper.coords.GridCoords;
import helper.coords.PixelCoords;
import helper.coords.ScreenCoords;

import java.util.ArrayList;
import java.util.List;

public class DrawableHealthContainer extends HealthContainer {
    private List<Image> heartImages;

    /**
     * Note that 2 health = 1 heart.
     *
     * @param maxHealth         The maximum health that can be gained
     * @param invincibilityTime The number of seconds to be invincible after being hit
     */
    public DrawableHealthContainer(int maxHealth, double invincibilityTime) {
        super(maxHealth, invincibilityTime);
        this.initDrawable();
    }

    private void initDrawable() {
        this.heartImages = new ArrayList<>();

        ScreenCoords origin = new PixelCoords(10, 10).toScreenCoords();

        for (int i = 0; i < Math.ceil(this.getHealth() / 2.0); i++) {
            ScreenCoords coords = new ScreenCoords(
                    origin.x + GridCoords.distXToScreenDist(1.1f * i), origin.y
            );

            this.heartImages.add(
                    new Image(
                            coords,
                            GridCoords.distXToScreenDist(1), GridCoords.distYToScreenDist(1),
                            "heart"
                    )
            );
        }
    }

    /**
     * Draws the health container to the bottom left of the screen.
     */
    public void draw() {
        int healthNum = this.getHealth();

        for (Image heartImage : this.heartImages) {
            // Determine if it is a full heart, half heart, etc. and draw it
            if (healthNum >= 2) {
                healthNum -= 2;
                heartImage.textureName = "heart";
            } else if (healthNum == 1) {
                healthNum -= 1;
                heartImage.textureName = "halfHeart";
            } else {  // healthNum == 0
                heartImage.textureName = "emptyHeart";
            }

            heartImage.draw();
        }
    }
}

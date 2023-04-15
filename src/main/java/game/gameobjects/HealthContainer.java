package game.gameobjects;

import helper.coords.GridCoords;
import helper.coords.PixelCoords;
import helper.coords.ScreenCoords;
import helper.time.Clock;

/**
 * Contains health and has the ability to draw it to the screen.
 */
public class HealthContainer {
    private final int MAX_HEALTH;
    private final double MAX_INVINCIBILITY_TIME;

    private double invincibilityTime;
    private int health;

    /**
     * Note that 2 health = 1 heart.
     *
     * @param maxHealth The maximum health that can be gained
     * @param invincibilityTime The number of seconds to be invincible after being hit
     */
    public HealthContainer(int maxHealth, double invincibilityTime) {
        this.MAX_HEALTH = maxHealth;
        this.health = maxHealth;

        this.MAX_INVINCIBILITY_TIME = invincibilityTime;
        this.invincibilityTime = 0;
    }

    /**
     * Take damage from the health container. Note that 2 damage = 1 heart.
     *
     * @param damage The amount of damage to take. Negative numbers heal.
     */
    public void takeDamage(int damage) {
        if (this.invincibilityTime > 0 || this.health <= 0) {
            return;
        }

        this.invincibilityTime = this.MAX_INVINCIBILITY_TIME;
        this.health -= damage;

        // Make sure health doesn't go below 0 or above this.MAX_HEALTH
        this.health = Math.min(this.health, this.MAX_HEALTH);
        this.health = Math.max(this.health, 0);
    }

    public void draw() {
        ScreenCoords origin = new PixelCoords(10, 10).toScreenCoords();

        int healthNum = this.health;
        float width = GridCoords.distXToScreenDist(1);
        float height = GridCoords.distYToScreenDist(1);

        // Draw the moving right from the origin
        for (int i = 0; i < this.MAX_HEALTH / 2; i++) {
            // The coordinates of the heart to draw
            ScreenCoords coords = new ScreenCoords(
                    origin.x + GridCoords.distXToScreenDist(1.1f * i), origin.y
            );

            // Determine if it is a full heart, half heart, etc. and draw it
            if (healthNum >= 2) {
                healthNum -= 2;
                new Image(coords, width, height, "heart").draw();
            } else if (healthNum == 1) {
                healthNum -= 1;
                new Image(coords, width, height, "halfHeart").draw();
            } else {  // healthNum == 0
                new Image(coords, width, height, "emptyHeart").draw();
            }
        }
    }

    public void update() {
        this.invincibilityTime = Math.max(
                this.invincibilityTime - Clock.getTimeDelta(), 0
        );
    }

    public int getHealth() {
        return health;
    }

    public double getInvincibilityTime() {
        return invincibilityTime;
    }
}

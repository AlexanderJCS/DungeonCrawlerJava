package game.gameobjects.health;

import helper.interfaces.Updatable;
import helper.time.Clock;

/**
 * Contains health and has the ability to draw it to the screen. Useful for GameObjects that have health, such as
 * Player and Enemy.
 */
public class HealthContainer implements Updatable {
    private final int MAX_HEALTH;
    private final double MAX_INVINCIBILITY_TIME;

    /**
     * The amount of time the health container is invincible from any other attacks. 0 when not invincible.
     */
    private double invincibilityTime;
    /**
     * 2 health = 1 heart
     */
    private int health;

    /**
     * Note that 2 health = 1 heart.
     *
     * @param maxHealth         The maximum health that can be gained
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

    @Override
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

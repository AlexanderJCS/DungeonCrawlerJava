package game.gameobjects;

import game.Game;
import graphics.Rect;
import helper.coords.GridCoords;
import helper.coords.PixelCoords;
import helper.coords.ScreenCoords;
import helper.time.Clock;

public class Enemy extends GameObject {
    /** The maximum time to move away for. */
    private static final double MOVE_AWAY_TIME = 0.3;
    private final float speedX;
    private final float speedY;
    /** How long to move away for. 0 when the enemy should not move away. */
    private double moveAwayTime;

    public final HealthContainer healthContainer;

    /**
     * @param coords The coordinates the enemy should spawn at.
     * @param speed The speed of the enemy in grid coords per frame.
     */
    public Enemy(ScreenCoords coords, float speed) {
        super(coords, GridCoords.distXToScreenDist(1), GridCoords.distYToScreenDist(1), "enemy");

        this.speedX = GridCoords.distXToScreenDist(speed);
        this.speedY = GridCoords.distYToScreenDist(speed);

        this.moveAwayTime = 0;
        this.healthContainer = new HealthContainer(6, 0.3);
    }

    /**
     * Moves towards the player.
     * Note this method can cause jumping on lower frame rates when the player is in the same plane as the enemy.
     */
    private void moveTowardsPlayer() {
        Rect playerRect = Game.player.getRect();

        float dirX = this.rect.x1 < playerRect.x1 ? 1 : -1;
        float dirY = this.rect.y1 < playerRect.y1 ? 1 : -1;

        this.fixedMove(dirX * this.speedX, dirY * this.speedY, Game.map.getShownRoom().getWallRects());
    }

    /**
     * Move away from the given rect. If this.moveAwayTime == 0, the enemy will not move due to this method.
     *
     * @param moveAwayRect The rect to move away from
     */
    private void moveAway(Rect moveAwayRect) {
        // How far the player should go in this frame
        double mag = 5000 * this.moveAwayTime;

        // Do a bit of trig to calculate the x and y components
        double adj = this.rect.x1 - moveAwayRect.x1;
        double opp = this.rect.y1 - moveAwayRect.y1;

        double hyp = Math.sqrt(Math.pow(adj, 2) + Math.pow(opp, 2));

        double sin = opp / hyp;
        double cos = adj / hyp;

        this.fixedMove(
                PixelCoords.distXToScreenDist((float) (cos * mag)),  // x direction
                PixelCoords.distYToScreenDist((float) (sin * mag)),  // y direction
                Game.map.getShownRoom().getWallRects()
        );
    }

    @Override
    public void update() {
        this.healthContainer.update();

        // Check for collision with the player
        // If that happens, then start moving away
        if (Game.player.getRect().collidesWith(this.rect)) {
            this.moveAwayTime = MOVE_AWAY_TIME;
            Game.player.healthContainer.takeDamage(1);
        }

        // Otherwise set move away frames to invincibility frames
        else if (this.healthContainer.getInvincibilityTime() > 0) {
            this.moveAwayTime = this.healthContainer.getInvincibilityTime();
        }

        if (this.moveAwayTime > 0) {  // if the enemy should move away
            this.moveAway(Game.player.getRect());
            this.moveAwayTime = Math.max(this.moveAwayTime - Clock.getTimeDelta(), 0);
        } else {  // otherwise, move towards the player
            this.moveTowardsPlayer();
        }
    }
}

package game.gameobjects;

import game.Game;
import graphics.Rect;
import helper.coords.GridCoords;
import helper.coords.PixelCoords;
import helper.coords.ScreenCoords;

public class Enemy extends GameObject {
    private static final int MOVE_AWAY_FRAMES = 40;
    private final float speedX;
    private final float speedY;
    private int moveAwayFrames;

    public final HealthContainer healthContainer;

    /**
     *
     * @param coords The coordinates the enemy should spawn at.
     * @param speed The speed of the enemy in grid coords per frame.
     */
    public Enemy(ScreenCoords coords, float speed) {
        super(coords, GridCoords.distXToScreenCoords(1), GridCoords.distYToScreenCoords(1), "enemy");

        this.speedX = GridCoords.distXToScreenCoords(speed);
        this.speedY = GridCoords.distYToScreenCoords(speed);

        this.moveAwayFrames = 0;
        this.healthContainer = new HealthContainer(6, 20);
    }

    /**
     * Move towards the player to attack the player when the player is hit.
     */
    private void moveTowardsPlayer() {
        Rect playerRect = Game.player.getRect();

        float distX = this.rect.x1 < playerRect.x1 ? 1 : -1;
        float distY = this.rect.y1 < playerRect.y1 ? 1 : -1;

        this.move(distX * this.speedX, distY * this.speedY, Game.map.getShownRoom().getWallRects());
    }

    /**
     * Move away from the given rect.<br>
     * Precondition: this.moveAwayFrames > 0
     * @param moveAwayRect The rect to move away from
     */
    private void moveAway(Rect moveAwayRect) {
        // How far the player should go in this frame
        double mag = this.moveAwayFrames / 4.0;

        // Do a bit of trig to calculate the x and y components
        double adj = this.rect.x1 - moveAwayRect.x1;
        double opp = this.rect.y1 - moveAwayRect.y1;

        double hyp = Math.sqrt(Math.pow(adj, 2) + Math.pow(opp, 2));

        double sin = opp / hyp;
        double cos = adj / hyp;

        this.move(
                PixelCoords.distXToScreenDist((float) (cos * mag)),  // x direction
                PixelCoords.distYToScreenDist((float) (sin * mag)),  // y direction
                Game.map.getShownRoom().getWallRects()
        );
    }

    public void update() {
        this.healthContainer.update();

        // Check for collision with the player
        // If that happens, then start moving away
        if (Game.player.getRect().collidesWith(this.rect)) {
            this.moveAwayFrames = MOVE_AWAY_FRAMES;
            Game.player.healthContainer.takeDamage(1);
        }

        // Otherwise set move away frames to invincibility frames
        else if (this.healthContainer.getInvincibilityFrames() > 0) {
            this.moveAwayFrames = this.healthContainer.getInvincibilityFrames();
        }

        if (this.moveAwayFrames > 0) {  // if the enemy should move away
            this.moveAway(Game.player.getRect());
            this.moveAwayFrames--;
        } else {  // otherwise, move towards the player
            this.moveTowardsPlayer();
        }
    }
}

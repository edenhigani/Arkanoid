import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Eden Higani 315791434
 * @version exercise 6
 * this class creates a Lives indicator that displays the
 * current lives of the game.
 */
public class NameIndicator implements Sprite {
    private String name;

    /**
     * this function is the constructor of this class.
     *
     * @param name1 the current lives of the player.
     */
    public NameIndicator(String name1) {
        this.name = name1;
    }

    /**
     * this function returns the value of the current counter.
     *
     * @return counter the number of lives.
     */
    public String getName() {
        return this.name;
    }

    /**
     * This function draws the sprite on the screen.
     *
     * @param d the screen which we need to draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.drawText(500, 15, "Level name: " + this.name, 15);
    }

    /**
     * This function notifies the sprite that time has passed.
     */
    public void timePassed() {

    }

    /**
     * The function adds the indicator to the game environment.
     *
     * @param game the game environment.
     */
    public void addToGame(GameLevel game) {
        game.getSprites().addSprite(this);
    }
}

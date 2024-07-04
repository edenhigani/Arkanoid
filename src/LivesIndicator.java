import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Lives indicator.
 *
 * @author Eden Higani 315791434
 * @version exercise 6
 * This class creates a Lives indicator that displays the current lives of the game.
 */
public class LivesIndicator implements Sprite {
    private Counter counter;

    /**
     * this function is the constructor of this class.
     *
     * @param counter1 the current lives of the player.
     */
    public LivesIndicator(Counter counter1) {
        this.counter = counter1;
    }

    /**
     * this function returns the value of the current counter.
     *
     * @return counter the number of lives.
     */
    public Counter getCounter() {
        return counter;
    }

    /**
     * This function draws the sprite on the screen.
     *
     * @param d the screen which we need to draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.drawText(15, 15, "Lives: " + this.counter.getValue(), 15);
    }

    /**
     * This function notifies the sprite that time has passed.
     */
    public void timePassed() {

    }


    /**
     * This function adds this indicator to the game.
     *
     * @param game the game which we add the paddle to.
     */
    public void addToGame(GameLevel game) {
        game.getSprites().addSprite(this);
    }
}

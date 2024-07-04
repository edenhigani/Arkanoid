import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Eden Higani 315791434
 * @version exercise 6
 * this class creates score indicator that displays the
 * current score of the game.
 */
public class ScoreIndicator implements Sprite {
    private Counter counter;

    /**
     * this function is the constructor of this class.
     *
     * @param count the current score of the player.
     */
    public ScoreIndicator(Counter count) {
        this.counter = count;
    }

    /**
     * this funstion is a get for the counter's value.
     *
     * @return counter the counters value.
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
        d.setColor(Color.WHITE);
        d.drawText(350, 15, "Score: " + this.getCounter().getValue(), 15);
    }

    /**
     * this function notify the sprite that time has passed.
     */
    public void timePassed() {

    }
}

import biuoop.DrawSurface;

/**
 * @author Eden Higani 315791434
 * @version exercise 6
 * this class is the interface of sprite.
 */
public interface Sprite {

    // draw the sprite to the screen

    /**
     * This function draws the sprite on the screen.
     *
     * @param d the screen which we need to draw on.
     */
    void drawOn(DrawSurface d);

    // notify the sprite that time has passed

    /**
     * This function notifies the sprite that time has passed.
     */
    void timePassed();
}
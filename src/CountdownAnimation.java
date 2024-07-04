import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * This class creates the Countdown animation.
 */
public class CountdownAnimation implements Animation {

    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private double appearanceTime;
    private Sleeper sleeper;


    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds to wait
     * @param countFrom    from where we start counting
     * @param gameScreen   the screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.sleeper = new Sleeper();
        this.appearanceTime = (this.numOfSeconds / this.countFrom);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        String s = Integer.toString(this.countFrom);

        // draw all the sprite.

        this.gameScreen.drawAllOn(d);
        // If we did not get to 0
        if (this.countFrom != 0) {
            d.setColor(Color.decode("#BA55D3"));
            d.drawText(385, d.getHeight() / 2 + 41, s, 100);
        }
        if (appearanceTime != this.numOfSeconds / this.countFrom) {
            this.sleeper.sleepFor(((long) appearanceTime / 1000 + 750));
        }
        this.countFrom--;
    }

    @Override
    public boolean shouldStop() {
        if (this.countFrom < 0) {
            return true;
        }
        return false;
    }
}

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The type End screen.
 *
 * @author Eden Higani 315791434
 * @version exercise 6
 * This class creates the end screen of the game by winning or losing it.
 */
public class EndScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter score;
    private boolean flag;

    /**
     * Instantiates a new End screen.
     *
     * @param k      the keyboard sensor
     * @param score1 the score at the end of the game
     * @param flag1  a flag for winning or losing -
     *               true if the player won the game, and false if he lost it
     */
    public EndScreen(KeyboardSensor k, Counter score1, boolean flag1) {
        this.keyboard = k;
        this.stop = false;
        this.score = score1;
        this.flag = flag1;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.flag) {
            d.setColor(Color.decode("#FF99CC"));
            d.fillRectangle(0, 0, 800, 600);
            d.setColor(Color.decode("#C71585"));
            for (int i = 0; i < 20; i++) {
                d.drawText(100 + i, 200, "Y", 90);
                d.drawText(170 + i, 200, "O", 90);
                d.drawText(250 + i, 200, "U", 90);
                //d.drawText(400 + i, 200, " ", 90);
                d.drawText(380 + i, 200, "W", 90);
                d.drawText(480 + i, 200, "I", 90);
                d.drawText(520 + i, 200, "N", 90);
                d.drawText(590 + i, 200, "!", 90);
            }
            d.drawText(100, d.getHeight() / 2, "Your score is: " + this.score.getValue(), 50);
        } else {
            d.setColor(Color.decode("#87CEFA"));
            d.fillRectangle(0, 0, 800, 600);
            d.setColor(Color.decode("#0000CD"));
            for (int i = 0; i < 20; i++) {
                d.drawText(100 + i, 200, "Y", 90);
                d.drawText(170 + i, 200, "O", 90);
                d.drawText(250 + i, 200, "U", 90);
                d.drawText(380 + i, 200, "L", 90);
                d.drawText(430 + i, 200, "O", 90);
                d.drawText(510 + i, 200, "S", 90);
                d.drawText(580 + i, 200, "E", 90);
                d.drawText(650 + i, 200, "!", 90);
            }
            d.drawText(100, d.getHeight() / 2, "Your score is: " + this.score.getValue(), 50);
        }
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
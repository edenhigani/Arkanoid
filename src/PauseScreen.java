import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;


/**
 * @author Eden Higani 315791434
 * @version exercise 6
 * This class creates the pause screen of the game.
 */
public class PauseScreen implements Animation {

    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * Instantiates a new Pause screen.
     *
     * @param k the keyboard sensor.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.decode("#C292D4"));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.black);
        for (int i = 0; i < 20; i++) {
            d.drawText(200 + i, 200, "P", 90);
            d.drawText(260 + i, 200, "A", 90);
            d.drawText(320 + i, 200, "U", 90);
            d.drawText(380 + i, 200, "S", 90);
            d.drawText(440 + i, 200, "E", 90);
            d.drawText(500 + i, 200, "D", 90);
        }
        d.fillRectangle(320, 250, 50, 120);
        d.fillRectangle(400, 250, 50, 120);

        d.setColor(Color.decode("#8B008B"));
        for (int i = 0; i < 20; i++) {
            d.drawText(200 + i, 200, "P", 80);
            d.drawText(260 + i, 200, "A", 80);
            d.drawText(320 + i, 200, "U", 80);
            d.drawText(380 + i, 200, "S", 80);
            d.drawText(440 + i, 200, "E", 80);
            d.drawText(500 + i, 200, "D", 80);
        }
        d.fillRectangle(322, 258, 40, 110);
        d.fillRectangle(402, 258, 40, 110);

        d.drawText(100, d.getHeight() / 2 + 200, "Game paused - press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }

    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
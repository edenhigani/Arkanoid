import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;


import javax.imageio.ImageIO;

/**
 * The type High scores animation.
 */
public class HighScoresAnimation implements Animation {

    private Image img = null;
    private HighScoresTable scoresTable;
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * Instantiates a new High scores animation.
     *
     * @param scores the scores
     */
    public HighScoresAnimation(HighScoresTable scores) {

        this.scoresTable = scores;
        try {
            img = ImageIO.read(new File("resources/background_images/new.jpg"));
        } catch (Exception e) {
            System.out.println("can not read file");
        }
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        ArrayList<ScoreInfo> lst = (ArrayList<ScoreInfo>) this.scoresTable.getHighScores();
        d.setColor(Color.decode("#CCFFFF"));
        d.fillRectangle(0, 0, 800, 600);

        d.drawImage(0, 0, img);
        d.setColor(Color.BLACK);
        d.drawText(200, 100, "-- High Scores -- ", 50);
        for (int i = 0; i < lst.size(); i++) {
            String name = lst.get(i).getName();
            String score = Integer.toString(lst.get(i).getScore());
            d.drawText(280, 150 + (i * 40), name, 32);
            d.drawText(430, 150 + (i * 40), score, 32);
        }
        d.drawText(170, 570, "press space to continue... ", 25);

    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
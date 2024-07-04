import biuoop.DrawSurface;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * The type Background.
 */
public class Background implements Sprite {

    private String name = null;
    private Color color = null;
    private Image image = null;

    /**
     * Instantiates a new Background.
     *
     * @param c the color
     */
    public Background(Color c) {
        this.color = c;
    }

    /**
     * Instantiates a new Background.
     *
     * @param name1 the name
     */
    public Background(String name1) {
        this.name = name1;
//        try {
//            this.image = ImageIO.read(new File(this.name));
//        } catch (Exception e) {
//            System.out.println("no image");
//        }

        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(this.name);
        try {
            this.image = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void drawOn(DrawSurface d) {
        if (this.color == null) {
            d.drawImage(0, 0, this.image);
        } else {
            d.setColor(this.color);
            d.fillRectangle(0, 0, 800, 600);
        }
    }

    @Override
    public void timePassed() {

    }
}

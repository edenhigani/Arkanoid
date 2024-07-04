import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import biuoop.DrawSurface;

import javax.imageio.ImageIO;

/**
 * The type Block.
 *
 * @author Eden Higani 315791434
 * @version exercise 6 this class creates a block.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private Rectangle block;
    private Color color;
    private int hitPoints;
    private List<HitListener> hitListeners = new ArrayList<HitListener>();
    private Image img = null;
    private boolean bool = false;
    private Color stroke = Color.black;
    private Map<Integer, String> map = new TreeMap<>();
    /**
     * Instantiates a new Block.
     *
     * @param rect       the rectangle
     * @param map1       the map of fills
     * @param hitPoints1 the hit points
     * @param stroke1    the stroke
     */
    public Block(Rectangle rect, Map<Integer, String> map1, int hitPoints1, Color stroke1) {
        this.block = rect;
        this.stroke = stroke1;
        this.map = map1;
        this.hitPoints = hitPoints1;
        if (map.get(this.hitPoints).contains("image")) {
            String string = map.get(this.hitPoints).substring(6, map.get(this.hitPoints).length() - 1);
//            try {
//                this.img = ImageIO.read(new File(string));
//                this.bool = true;
//            } catch (Exception e) {
//                System.out.println("no image");
//            }
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(string);
            try {
                this.img = ImageIO.read(is);
                this.bool = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            this.color = new ColorsParser().colorFromString(map1.get(hitPoints));
        }
    }


    /**
     * This function is a constructor of the Block class it builds a block
     * with rectangle and color.
     *
     * @param rect the rectangle of the block.
     * @param c    the color of the block.
     */
    public Block(Rectangle rect, Color c) {
        this.block = rect;
        this.color = c;
        this.hitListeners = new ArrayList<HitListener>();
        this.hitPoints = 1;
    }

    /**
     * This function is another constructor of the Block class it builds a block
     * with rectangle and color.
     *
     * @param upperLeft the upperleft point of the blocks rectangle.
     * @param width     the wifth of the block.
     * @param height    the height of the block.
     * @param c         the color of the block.
     */
    public Block(Point upperLeft, double width, double height, Color c) {
        this.block = new Rectangle(upperLeft, width, height);
        this.color = c;
        this.hitPoints = 1;
    }

    /**
     * This function sets the color of the block.
     *
     * @param color1 the color of the block that we want to set.
     */
    public void setColor(Color color1) {
        this.color = color1;
    }

    /**
     * This function returns the number of hit points on the block.
     *
     * @return hits of the block.
     */
    public int getHitPoints() {
        return this.hitPoints;
    }

    /**
     * This function sets the number of hit points on the block.
     *
     * @param hits the hit points of the block that we want to set.
     */
    public void setHitPoints(int hits) {
        this.hitPoints = hits;
    }

    /**
     * This function returns the color of the block.
     *
     * @return the color of the block.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * This function returns the "collision shape" of the object.
     *
     * @return the collision rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.block;
    }

    /**
     * This function Notify the object that we collided with
     * it at collisionPoint with a given velocity.
     *
     * @param collisionPoint  the point we have hit the paddle
     * @param currentVelocity the velocity of the ball that hit the paddle the Block class
     *                        implements hit of Collidable.
     * @param hitter          the ball that's doing the hitting.
     * @return Velocity, new velocity of the ball that hit the block
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelo = currentVelocity;

        // for hits on up\down side of the block
        if (collisionPoint.getY() == this.getCollisionRectangle().getUpperLeft().getY()
                || collisionPoint.getY() == this.getCollisionRectangle().getUpperLeft().getY()
                + this.getCollisionRectangle().getHeight()) {
            newVelo.setDy(-1 * currentVelocity.getDy());
        }
        // for hits on one of the sides of the block
        if (collisionPoint.getX() == this.getCollisionRectangle().getUpperLeft().getX()
                || collisionPoint.getX() == this.getCollisionRectangle().getUpperLeft().getX()
                + this.getCollisionRectangle().getWidth()) {
            newVelo.setDx(-1 * currentVelocity.getDx());
        }
        if (this.hitPoints > 0) {
            this.hitPoints--;
            //
            if (map.containsKey(hitPoints)) {
                if (map.get(hitPoints).contains("color")) {
                    this.setColor(new ColorsParser().colorFromString(map.get(hitPoints)));
                    this.bool = false;
                } else {
                    this.bool = true;
                    String ss = this.map.get(this.hitPoints).substring(6, this.map.get(this.hitPoints).length() - 1);
//                    try {
//                        img = ImageIO.read(new File(ss));
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                    InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(ss);
                    try {
                        this.img = ImageIO.read(is);
                        this.bool = true;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        this.notifyHit(hitter);
        return newVelo;
    }

    /**
     * This method draws the block on the surface.
     * The class implements the drawOn of Sprite interface.
     *
     * @param d the current draw surface.
     */
    public void drawOn(DrawSurface d) {
        if (!this.bool) {
            d.setColor(this.getColor());
            d.fillRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                    (int) this.getCollisionRectangle().getUpperLeft().getY(),
                    (int) this.getCollisionRectangle().getWidth(),
                    (int) this.getCollisionRectangle().getHeight());
        } else {
            d.drawImage((int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY(), img);
        }
        d.setColor(Color.BLACK);
        if (stroke != null) {
            d.setColor(stroke);
            d.drawRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                    (int) this.getCollisionRectangle().getUpperLeft().getY(),
                    (int) this.getCollisionRectangle().getWidth(),
                    (int) this.getCollisionRectangle().getHeight());
        }
        String hitString = Integer.toString(this.hitPoints);
        int xHitIndex = (int) (this.getCollisionRectangle().getUpperLeft().getX()
                + (this.getCollisionRectangle().getWidth() / 2)) - 2;
        int yHitIndex = (int) (this.getCollisionRectangle().getUpperLeft().getY()
                + (this.getCollisionRectangle().getHeight() / 2)) + 5;
    }

    /**
     * The function does nothing for this assignment.
     * Block class implements timePassed method of Sprite interface.
     */
    public void timePassed() {
    }

    /**
     * The function adds the block to the game environment.
     *
     * @param g the game environment.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);

    }

    /**
     * This function removes this block from the game.
     *
     * @param game the game which we remove the paddle from.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * this function adds hl as a listener to hit events.
     *
     * @param hl the listener the we want to add to the listeners list.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * this function removes hl from the list of listeners to hit events.
     *
     * @param hl the listener the we want to remove from the listeners list.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * this function notifies all listeners about a hit event.
     *
     * @param hitter the ball who hit the current block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}

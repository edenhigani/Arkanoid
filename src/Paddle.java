import java.awt.Color;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * @author Eden Higani 315791434
 * @version exercise 6
 * This class creates a paddle.
 */
public class Paddle implements Sprite, Collidable {

    private int paddleWidth;
    private int paddleSpeed;
    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddle;
    private Color color = Color.black;
    /**
     * The Step.
     */
    static final int STEP = 10;
    /**
     * The Guiheight.
     */
    static final int GUIHEIGHT = 600;
    /**
     * The Guiwidth.
     */
    static final int GUIWIDTH = 800;
    /**
     * The Framewidth.
     */
    static final int FRAMEWIDTH = 15;
    /**
     * The Paddleh.
     */
    static final int PADDLEH = 5;
    /**
     * The Paddlew.
     */
    static final int PADDLEW = 150;

    /**
     * This function is the constructor of the Paddle class it builds
     * a paddle with gui screen.
     *
     * @param gui    the screen.
     * @param speed1 the speed 1
     * @param width  the width
     */
    public Paddle(GUI gui, int speed1, int width) {
        this.paddleWidth = width;
        this.paddleSpeed = speed1;
        this.paddle = new Rectangle(new Point((GUIWIDTH / 2) - 50, GUIHEIGHT - FRAMEWIDTH - PADDLEH + 30),
                this.paddleWidth, PADDLEH);
        this.setColor(Color.gray);
        keyboard = gui.getKeyboardSensor();
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public int getWidth() {
        return this.paddleWidth;
    }

    /**
     * Sets color of the paddle.
     *
     * @param c the color we set
     */
    public void setColor(Color c) {
        this.color = c;
    }


    /**
     * This function moves the paddle one step to the left.
     */
    public void moveLeft() {
        if (this.paddle.getUpperLeft().getX() - STEP <= FRAMEWIDTH) {
            this.paddle.setUpperLeft(new Point(FRAMEWIDTH, this.paddle.getUpperLeft().getY()));
        } else {
            this.paddle.setUpperLeft(new Point(this.paddle.getUpperLeft().getX() - this.paddleSpeed,
                    this.paddle.getUpperLeft().getY()));
        }
    }

    /**
     * This function moves the paddle one step to the right.
     */
    public void moveRight() {
        if (this.paddle.getUpperLeft().getX() + this.paddle.getWidth() + STEP >= GUIWIDTH - FRAMEWIDTH) {
            this.paddle.setUpperLeft(new Point(GUIWIDTH - FRAMEWIDTH - this.paddle.getWidth(),
                    this.paddle.getUpperLeft().getY()));
        } else {
            this.paddle.setUpperLeft(new Point(this.paddle.getUpperLeft().getX() + this.paddleSpeed,
                    this.paddle.getUpperLeft().getY()));
        }
    }

    /**
     * the Paddle class implements timePassed of Sprite interface.
     */
    public void timePassed() {

        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * This function draws the paddle on bottom of the screen.
     *
     * @param d the current draw surface.
     *          the Paddle class implements drawOn of Sprite.
     */
    public void drawOn(DrawSurface d) {
        //d.setColor(this.color);
        d.setColor(Color.decode("#FF69B0"));
        d.fillRectangle((int) this.paddle.getUpperLeft().getX(),
                (int) this.paddle.getUpperLeft().getY(),
                (int) this.paddle.getWidth(), PADDLEH + 10);
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.paddle.getUpperLeft().getX(),
                (int) this.paddle.getUpperLeft().getY(),
                (int) this.paddle.getWidth(), PADDLEH + 10);

    }

    /**
     * the Paddle class implements getCollisionRectangle of Collidable.
     *
     * @return Rectangle , the rectangle we have hit.
     */
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    /**
     * @param collisionPoint  the point we have hit the paddle
     * @param currentVelocity the velocity of the ball that hit the paddle
     *                        the Paddle class implements hit of Collidable.
     * @param hitter          the ball that hits the paddle.
     * @return Velocity, new velocity of the ball that hit the paddle
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double region = this.getWidth() / 5;
        Velocity newVelo = currentVelocity;
        double speed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2)
                + Math.pow(currentVelocity.getDy(), 2));

        if ((collisionPoint.getX() == this.paddle.getUpperLeft().getX())
                || (collisionPoint.getX() == this.paddle.getUpperLeft().getX()
                + this.getWidth())) {
            newVelo.setDx(-1 * currentVelocity.getDx());
        }

        if ((collisionPoint.getX() >= this.paddle.getUpperLeft().getX()
                && collisionPoint.getX() < this.paddle.getUpperLeft().getX() + region)) {
            newVelo = newVelo.fromAngleAndSpeed(300, speed);
            newVelo = new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
        }

        if ((collisionPoint.getX() >= this.paddle.getUpperLeft().getX() + region)
                && (collisionPoint.getX() < this.paddle.getUpperLeft().getX() + region * 2)) {
            newVelo = newVelo.fromAngleAndSpeed(330, speed);
            newVelo = new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
        }

        if ((collisionPoint.getX() >= this.paddle.getUpperLeft().getX() + region * 2)
                && collisionPoint.getX() < this.paddle.getUpperLeft().getX() + region * 3) {
            newVelo = new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
        }

        if ((collisionPoint.getX() >= this.paddle.getUpperLeft().getX() + region * 3)
                && (collisionPoint.getX() < this.paddle.getUpperLeft().getX() + region * 4)) {
            newVelo = newVelo.fromAngleAndSpeed(30, speed);
            newVelo = new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
        }

        if ((collisionPoint.getX() >= this.paddle.getUpperLeft().getX() + region * 4)
                && (collisionPoint.getX() <= this.paddle.getUpperLeft().getX() + region * 5)) {
            newVelo = newVelo.fromAngleAndSpeed(60, speed);
            newVelo = new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
        }
        return newVelo;
    }

    /**
     * This function removes this paddle from the game.
     *
     * @param game1 the game which we remove the paddle from.
     */
    public void removeFromGame(GameLevel game1) {
        game1.removeCollidable(this);
        game1.removeSprite(this);
    }

    /**
     * This function adds this paddle to the game.
     *
     * @param g the game which we add the paddle to.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }


    /**
     * This function puts the paddle in the middle of the board's bottom.
     */
    public void moveP() {
        Point mid = new Point((GUIWIDTH / 2) - this.paddleWidth / 2, 565);
        this.paddle.setUpperLeft(mid);
    }
}
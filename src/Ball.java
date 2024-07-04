import biuoop.DrawSurface;

/**
 * @author Eden Higani 315791434
 * @version exercise 6
 * this class creates a ball.
 */
public class Ball implements Sprite {
    static final double EPSILON = 1;
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity v = new Velocity(0, 0);
    private double upBoundX;
    private double upBoundY;
    private double downBoundX;
    private double downBoundY;
    private GameEnvironment gameE;

    /**
     * This function is the constructor of the class Ball its build a ball
     * with a center point.
     *
     * @param center the center of the ball.
     * @param r      the radius of the ball.
     * @param color  the color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * This function is the constructor of the class Ball its build a ball
     * with x,y values.
     *
     * @param x     the x value of the center point.
     * @param y     the y value of the center point.
     * @param r     the radius of the ball.
     * @param color the color of the ball.
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
    }

    /**
     * The function returns the game environment of the ball.
     *
     * @return the game environment
     */
    public GameEnvironment getGameE() {
        return this.gameE;
    }

    /**
     * The function return the x value of the center point.
     *
     * @return the x value of the center point
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * The function return the y value of the center point.
     *
     * @return the y value of the center point .
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * The function return the radius value of the ball.
     *
     * @return the radius of the ball .
     */
    public int getSize() {
        return this.r;
    }

    /**
     * The function return the color of the ball.
     *
     * @return the color of the ball .
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * The function returns the higher bound of x values in a frame.
     *
     * @return the large x bound.
     */
    public double getUpBoundX() {
        return this.upBoundX;
    }

    /**
     * The function returns the higher bound of y values in a frame.
     *
     * @return the large y bound.
     */
    public double getUpBoundY() {
        return this.upBoundY;
    }

    /**
     * The function returns the lower bound of x values in a frame.
     *
     * @return the low x bound.
     */
    public double getDownBoundX() {
        return this.downBoundX;
    }

    /**
     * The function returns the lower bound of y values in a frame.
     *
     * @return the low y bound.
     */
    public double getDownBoundY() {
        return this.downBoundY;
    }

    /**
     * The function sets the high bound of x values.
     *
     * @param xupBound the high bound of x
     */
    public void setUpBoundX(double xupBound) {
        this.upBoundX = xupBound;
    }

    /**
     * The function sets the high bound of y values.
     *
     * @param yupBound the high bound of y
     */
    public void setUpBoundY(double yupBound) {
        this.upBoundY = yupBound;
    }

    /**
     * The function sets the low bound of x values.
     *
     * @param xdownBound the low bound of x
     */
    public void setDownBoundX(double xdownBound) {
        this.downBoundX = xdownBound;
    }

    /**
     * The function sets the low bound of y values.
     *
     * @param ydownBound the low bound of y
     */
    public void setDownBoundY(double ydownBound) {
        this.downBoundY = ydownBound;
    }

    /**
     * This function draw on the ball on the draw surface.
     *
     * @param surface the draw surface of the window
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * This function changes the velocity to new one that the function gets.
     *
     * @param v1 the velocity
     */
    public void setVelocity(Velocity v1) {
        this.v = v1;
    }

    /**
     * This function changes the velocity to new one that the function gets.
     *
     * @param dx the x direction
     * @param dy the y direction
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * The function return the velocity of the ball.
     *
     * @return the velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * This function make the move of the ball in the window by checking if
     * it had not hit a collidable object, if it had it changes its direction
     * after located near the block.
     */

    public void moveOneStep() {
        Line trajectory;
        Point helpP = this.getVelocity().applyToPoint(this.center);
        Point nextCenter = new Point(Math.round(helpP.getX()), Math.round(helpP
                .getY()));
        trajectory = new Line(this.center, nextCenter);
        CollisionInfo info = this.gameE.getClosestCollision(trajectory);
        if (info == null) { // if no collision found pass the ball to the next center.
            this.center = nextCenter;
        } else {
            // change ball's velocity
            this.v = info.collisionObject().hit(this, info.collisionPoint(), this.v);
            // move the ball to the point of collision
            if (nextCenter.getY() > center.getY()) {
                if (nextCenter.getX() > center.getX()) {
                    this.center = new Point(info.collisionPoint().getX()
                            - EPSILON, info.collisionPoint().getY() - EPSILON);
                } else {
                    this.center = new Point(info.collisionPoint().getX()
                            + EPSILON, info.collisionPoint().getY() - EPSILON);
                }
            } else {
                if (nextCenter.getX() > center.getX()) {
                    this.center = new Point(info.collisionPoint().getX()
                            - EPSILON, info.collisionPoint().getY() + EPSILON);
                } else {
                    this.center = new Point(info.collisionPoint().getX()
                            + EPSILON, info.collisionPoint().getY() + EPSILON);
                }
            }
        }
    }

    /**
     * the Ball class implements timePassed of Sprite interface.
     * this function notify the ball that time has passed and it need to make a move.
     */
    public void timePassed() {
        moveOneStep();
    }

    /**
     * The function sets the game environment of the ball.
     *
     * @param gameE1 the game environment.
     */
    public void setGameEnvironment(GameEnvironment gameE1) {
        this.gameE = gameE1;
    }

    /**
     * The function adds the ball to the game environment.
     *
     * @param g the game environment.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        this.setGameEnvironment(g.getGameEnvironment());
    }

    /**
     * this function removes this ball from the game.
     *
     * @param game the game we want to remove the ball from.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}
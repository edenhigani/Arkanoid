/**
 * @author Eden Higani 315791434
 * @version exercise 6
 * This class creates a speed for the ball.
 */
// Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {
    private double dx;
    private double dy;

    /**
     * constructor with movement along the x axis and y axis.
     *
     * @param dx movement along the x axis.
     * @param dy movement along the y axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * the method return the movement along the x axis.
     *
     * @return movement along the x axis.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * The method return the movement along the y axis.
     *
     * @return movement along the y axis.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * The method sets the movement along the x axis.
     *
     * @param dx1 movement along the x axis.
     */
    public void setDx(double dx1) {
        this.dx = dx1;
    }

    /**
     * The method sets the movement along the x axis.
     *
     * @param dy1 movement along the x axis.
     */
    public void setDy(double dy1) {
        this.dy = dy1;
    }

    /**
     * The method converts the angle and speed of movement along the x and y
     * axis.
     *
     * @param angle the angle we get.
     * @param speed the speed we get.
     * @return velocity according to the angle and the speed we got.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radAngle = Math.toRadians(angle);
        double dx = Math.sin(radAngle) * speed;
        double dy = Math.cos(radAngle) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * The method accepts a point and move it by dx and dy.
     *
     * @param p the point that we want to change.
     * @return the new point.
     */
    public Point applyToPoint(Point p) {
        return (new Point(p.getX() + this.dx, p.getY() + this.dy));
    }
}
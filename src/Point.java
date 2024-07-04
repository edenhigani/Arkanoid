/**
 * @author Eden Higani 315791434
 * @version exercise 6
 * This class creates a point.
 */
public class Point {
    // the x value of the point
    private double x;
    // the y value of the point
    private double y;

    /**
     * This function is the constructor of the class Point, its build a point.
     *
     * @param x the x value of the point
     * @param y the y value of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * This function calculates the distance between 2 points and return it.
     * ( like the formula of the distance )
     *
     * @param other other point that the function gets.
     * @return the distance between the 2 points .
     */
    public double distance(Point other) {
        double dis = Math.sqrt(((this.getX() - other.getX()) * (this.getX() - other.getX()))
                + ((this.getY() - other.getY()) * (this.getY() - other.getY())));
        return (dis);
    }

    /**
     * This function checks if the current point is equals to the other point.
     *
     * @param other other point that the function gets.
     * @return the function return true if the point are equals else false .
     */
    public boolean equals(Point other) {
        if (this.getX() == other.getX() && this.getY() == other.getY()) {
            return true;
        }
        return false;
    }

    /**
     * This function return the y value of the point.
     *
     * @return the x value of the point .
     */
    public double getX() {
        return this.x;
    }

    /**
     * This function return the y value of the point.
     *
     * @return the y value of the point .
     */
    public double getY() {
        return this.y;
    }
}

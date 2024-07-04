import java.util.List;
import java.util.ArrayList;

/**
 * @author Eden Higani 315791434
 * @version exercise 6
 * This class creates a Rectangle.
 */
class Rectangle {

    private Point upperLeft;
    private double width;
    private double height;

    /**
     * This function is the constructor of the class Rectangle it builds a
     * rectangle with upper left point, width and height.
     *
     * @param upperLeft1 the upper left point of the rectangle.
     * @param width1     the width of the rectangle.
     * @param height1    the height of the rectangle.
     */
    Rectangle(Point upperLeft1, double width1, double height1) {
        this.upperLeft = upperLeft1;
        this.width = width1;
        this.height = height1;
    }

    /**
     * This function returns the width of the rectangle.
     *
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * This function returns the height of the rectangle.
     *
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * This function returns the upper-left point of the rectangle.
     *
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * This function sets the upper left point of the rectangle.
     *
     * @param p the new upper left point of the rectangle.
     */
    public void setUpperLeft(Point p) {
        this.upperLeft = p;
    }

    /**
     * This function returns a List of intersection points with
     * (possibly empty) specified line.
     *
     * @param line the specified line.
     * @return a (possibly empty) List of intersection points with the specified
     * line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        int i;
        Point upperRight = new Point(this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY());
        Point downLeft = new Point(this.getUpperLeft().getX(), this.getUpperLeft().getY() + this.getHeight());
        Point downRight = new Point(this.getUpperLeft().getX() + this.getWidth(),
                this.getUpperLeft().getY() + this.getHeight());
        List<Point> points = new ArrayList<Point>();
        Line[] rectangleSide = new Line[4];
        rectangleSide[0] = new Line(this.getUpperLeft(), upperRight);
        rectangleSide[1] = new Line(downLeft, downRight);
        rectangleSide[2] = new Line(upperRight, downRight);
        rectangleSide[3] = new Line(this.getUpperLeft(), downLeft);
        for (i = 0; i < rectangleSide.length; i++) {
            if (rectangleSide[i].isIntersecting(line)) {
                points.add(line.intersectionWith(rectangleSide[i]));
            }
        }
        return points;
    }

}
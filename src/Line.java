import java.util.List;
import java.util.Random;

/**
 * @author Eden Higani 315791434
 * @version exercise 6
 * This class creates a line.
 */
public class Line {
    // The start point of the line
    private Point start;
    // The end point of the line
    private Point end;

    /**
     * constructor with point of start line and point of end line.
     *
     * @param start the start point.
     * @param end   the and point.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructor with the values of x and y and defines the start and end
     * point of the line.
     *
     * @param x1 the value of x of the start point.
     * @param y1 the value of y of the start point.
     * @param x2 the value of x of the end point.
     * @param y2 the value of y of the end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * The method calculates the length of the line.
     *
     * @return the length of the line.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * The method calculates the mid-point of the line.
     *
     * @return the mid-point of the line.
     */
    public Point middle() {
        double xMid = (this.start.getX() + this.end.getX()) / 2;
        double yMid = (this.start.getY() + this.end.getY()) / 2;
        Point midPoint = new Point(xMid, yMid);
        return midPoint;
    }

    /**
     * The method return the start point of the line.
     *
     * @return the start point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * The method return the end point of the line.
     *
     * @return the end point of the line.
     */
    // Returns the end point of the line
    public Point end() {
        return this.end;
    }

    /**
     * This function generate random line by tossing 4 integer values and create 2
     * points and the line.
     *
     * @return randomLine
     */
    public static Line generateRandomLine() {
        Random rand = new Random();
        int x1 = rand.nextInt(400) + 1; // get integer in range 1-400
        int y1 = rand.nextInt(300) + 1; // get integer in range 1-300
        int x2 = rand.nextInt(400) + 1; // get integer in range 1-400
        int y2 = rand.nextInt(300) + 1; // get integer in range 1-300
        Line randomLine = new Line((int) x1, (int) y1, (int) x2, (int) y2);
        return randomLine;
    }

    /**
     * This function returns the closest intersection point between a rectangle
     * and the line. If there is no a collision will return null.
     *
     * @param rect the rectangle we check its intersections.
     * @return the closest intersection point between the line and rectangle. If
     * there is no a collision will return null.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        int i, closestPlace = 0;
        double minDist, dist;
        //Point startP = this.start();
        List<Point> points = rect.intersectionPoints(this);
        if (points.isEmpty()) {
            return null;
        }
        minDist = this.start().distance(points.get(0));
        for (i = 0; i < points.size(); i++) {
            dist = this.start().distance(points.get(i));
            if (minDist > dist) {
                minDist = dist;
                closestPlace = i;
            }
        }
        return points.get(closestPlace);
    }


    /**
     * The method calculates the intersection point of 2 given lines.
     *
     * @param other line with him we examine whether the lines are cut.
     * @return the intersection point if they are not cut we will return null.
     */
    public Point intersectionPoint(Line other) {
        // Helped variables to find the point of intersection
        double a1, a2, b1, b2, c1, c2, det, x, y;
        a1 = this.start.getY() - this.end.getY();
        b1 = this.end.getX() - this.start.getX();
        c1 = a1 * this.end.getX() + b1 * this.end.getY();
        a2 = other.start.getY() - other.end.getY();
        b2 = other.end.getX() - other.start.getX();
        c2 = a2 * other.end.getX() + b2 * other.end.getY();
        // Examine whether they are parallel lines
        det = (a1 * b2) - (a2 * b1);
        if (det == 0) {
            return null;
        }
        // Calculate the point of intersection
        x = (b2 * c1 - b1 * c2) / det;
        y = (a1 * c2 - a2 * c1) / det;
        Point interPoint = new Point(Math.round(x), Math.round(y));
        return interPoint;
    }


    /**
     * The method calculates the intersection point of two lines
     * and checks if points in the range of the lines.
     * If so, it returns true else returns false.
     *
     * @param other line with him we examine whether the lines are cut.
     * @return true if the lines intersect and false otherwise.
     */
    public boolean isIntersecting(Line other) {
        Point interPoint = intersectionPoint(other);
        if (interPoint == null) {
            return false;
        }
        // checks if points in the range of the lines
        if ((Math.min(this.start.getY(), this.end.getY()) > interPoint.getY())
                || (interPoint.getY() > Math.max(this.start.getY(),
                this.end.getY()))) {
            return false;
        }
        if ((Math.min(this.start.getX(), this.end.getX()) > interPoint.getX())
                || (interPoint.getX() > Math.max(this.start.getX(),
                this.end.getX()))) {
            return false;
        }

        if ((Math.min(other.start.getY(), other.end.getY()) > interPoint.getY())
                || (interPoint.getY() > Math.max(other.start.getY(),
                other.end.getY()))) {
            return false;
        }
        if ((Math.min(other.start.getX(), other.end.getX()) > interPoint.getX())
                || (interPoint.getX() > Math.max(other.start.getX(),
                other.end.getX()))) {
            return false;
        }
        return true;
    }

    /**
     * The method calculates if lines are parallel lines, it returns null else returns
     * the intersection point.
     *
     * @param other line with him we examine whether the lines are cut.
     * @return the intersection point if they are not cut we will return null.
     */
    public Point intersectionWith(Line other) {
        if (isIntersecting(other)) {
            Point interPoint = intersectionPoint(other);
            return interPoint;
        }
        return null;
    }
}

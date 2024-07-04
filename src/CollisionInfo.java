/**
 * @author Eden Higani 315791434
 * @version exercise 6
 * This class creates an object of collision information.
 */
public class CollisionInfo {

    private Collidable object;
    private Point point;

    /**
     * This function is the constructor of the class CollisionInfo its build a
     * collision information with object and point.
     *
     * @param object1 the object of collision information.
     * @param point1  the point of collision information.
     */
    public CollisionInfo(Collidable object1, Point point1) {
        this.object = object1;
        this.point = point1;
    }

    /**
     * This function returns the point at which the collision occurs.
     *
     * @return the point at which the collision occurs
     */
    public Point collisionPoint() {
        return this.point;
    }

    /**
     * This function returns the collidable object involved in the collision.
     *
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.object;
    }
}
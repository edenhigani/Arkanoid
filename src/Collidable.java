/**
 * @author Eden Higani 315791434
 * @version exercise 6
 * This interfaces is for a Collidable.
 */
public interface Collidable {
    // Return the "collision shape" of the object.

    /**
     * This function returns the "collision shape" of the object.
     *
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * This function notify the object that the ball has collided with it at
     * collisionPoint with a current velocity.
     * The return is the new velocity obtained after the hit.
     *
     * @param collisionPoint  the collision point.
     * @param currentVelocity the velocity before the hit.
     * @param hitter          the ball that's doing the hitting.
     * @return the new velocity expected after the hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
/**
 * @author Eden Higani 315791434
 * @version exercise 6
 * this interface is for the listeners
 * that related to the hit action
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit this parameter is the object that is hit.
     * @param hitter   this parameter is the ball that hit the object.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
/**
 * @author Eden Higani 315791434
 * @version exercise 6
 * this class is a listener to follow the score
 * by scoring rules.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * this function is the constructor of this class.
     *
     * @param scoreCounter this parameter is the references of the
     *                     game score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * this function is the get of the current score value.
     *
     * @return currentsScore;
     */
    public Counter getCurrentScore() {
        return currentScore;
    }

    /**
     * This function is called whenever an beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit this parameter is the object that is hit.
     * @param hitter   this parameter is the ball that hit the object.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
        if (beingHit.getHitPoints() == 0) {
            this.currentScore.increase(10);
        }
    }
}
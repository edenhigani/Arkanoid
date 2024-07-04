/**
 * @author Eden Higani 315791434
 * @version exercise 6
 * This class creates ball remover listener.
 * will remove the ball when it falls down "out" of screen.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * this function is the constructor of the class.
     *
     * @param game            the current game.
     * @param remainingBalls1 the number of removed balls.
     */
    public BallRemover(GameLevel game, Counter remainingBalls1) {
        this.game = game;
        this.remainingBalls = remainingBalls1;
    }

    /**
     * this function returns the value of remaining balls.
     *
     * @return value of remainingBalls.
     */
    public Counter getCount() {
        return this.remainingBalls;
    }

    /**
     * this function sets the value of remaining balls.
     *
     * @param remainingBalls1 the counter we want to set.
     */
    public void setRemainingBalls(Counter remainingBalls1) {
        this.remainingBalls = remainingBalls1;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit this parameter is the object that is hit.
     * @param hitter   this parameter is the ball that hit the object.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }
}

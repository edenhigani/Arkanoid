import java.util.ArrayList;
import java.util.List;

/**
 * The type Block remover.
 *
 * @author Eden Higani 315791434
 * @version exercise 6
 * This class create a BlockRemover a which is in charge of
 * removing blocks from the game, as well as keeping count of the remaining blocks.
 */
public class BlockRemover implements HitListener, HitNotifier {
    private GameLevel game;
    private Counter remainingBlocks;
    private List<HitListener> hitListeners;

    /**
     * this function is the constructor of the class.
     *
     * @param game          the current game.
     * @param removedBlocks the number of blocks to remove.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;

        //
        this.hitListeners = new ArrayList<>();
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit this parameter is the object that is hit.
     * @param hitter   this parameter is the ball that hit the object.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        notifyHit(beingHit, hitter);
        if (beingHit.getHitPoints() == 0) {
            beingHit.removeFromGame(this.game);
            this.remainingBlocks.decrease(1);
        }
    }

    /**
     * this function adds hl as a listener to hit events.
     *
     * @param hl the listener the we want to add to the listeners list.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Gets remaining blocks.
     *
     * @return the remaining blocks
     */
    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }

    /**
     * this function removes hl from the list of listeners to hit events.
     *
     * @param hl the listener the we want to remove from the listeners list.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * this function notifies all listeners about a hit event.
     *
     * @param beingHit the block that is being hit.
     * @param hitter   the ball who hit the current block.
     */
    private void notifyHit(Block beingHit, Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(beingHit, hitter);
        }
    }
}
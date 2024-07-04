/**
 * @author Eden Higani 315791434
 * @version exercise 6
 * This interface is for the classes
 * * that has to notify when they have been hit.
 */
public interface HitNotifier {

    /**
     * this function adds hl as a listener to hit events.
     *
     * @param hl the listener the we want to add to the listeners list.
     */
    void addHitListener(HitListener hl);

    /**
     * this function removes hl from the list of listeners to hit events.
     *
     * @param hl the listener the we want to remove from the listeners list.
     */
    void removeHitListener(HitListener hl);
}
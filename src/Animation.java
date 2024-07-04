import biuoop.DrawSurface;

/**
 * The interface Animation.
 */
public interface Animation {

    /**
     * This function makes one movement of the animation on the screen.
     *
     * @param d the drawsurface
     */
    void doOneFrame(DrawSurface d);

    /**
     * this function return boolean value.
     * -false to continue run the animation
     * -true to stop the animation.
     * @return boolean value to stop or continue the animation
     */
    boolean shouldStop();
}
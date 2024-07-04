import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Eden Higani 315791434
 * @version exercise 6
 * This class is the key press stoppable animation.
 */
public class KeyPressStoppableAnimation implements Animation {

    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param sensor     the keyboard sensor
     * @param key1       the key
     * @param animation1 the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key1, Animation animation1) {
        this.keyboardSensor = sensor;
        this.key = key1;
        this.animation = animation1;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.keyboardSensor.isPressed(key) && !this.isAlreadyPressed) {
            this.stop = true;
        }
        if (!this.keyboardSensor.isPressed(key)) {
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
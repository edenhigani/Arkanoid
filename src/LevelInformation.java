import java.awt.Color;
import java.util.List;

/**
 * @author Eden Higani 315791434
 * @version exercise 6
 * This class is the interface of level information.
 */
public interface LevelInformation {
    /**
     * Number of balls in level.
     *
     * @return the number of balls.
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     *
     * @return the list of velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * The speed of the paddle in the level.
     *
     * @return the speed.
     */
    int paddleSpeed();

    /**
     * The width of the paddle in the level.
     *
     * @return the width.
     */
    int paddleWidth();

    /**
     * Gets the color of the paddle in the level.
     *
     * @return the color.
     */
    Color getColor();

    /**
     * The name of the level which will be displayed on screen.
     *
     * @return the name.
     */
    String levelName();

    /**
     * A sprite with the background of the level.
     *
     * @return the background.
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return the list of the blocks.
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed
     * before the level is considered to be "cleared".
     *
     * @return the number of blocks.
     */
    int numberOfBlocksToRemove();

    /**
     * The balls in the level. each balls contains it's size,
     * color and initial location.
     *
     * @return the list
     */
    List<Ball> balls();
}
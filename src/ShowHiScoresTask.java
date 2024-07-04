/**
 * The type Show high scores task.
 */
public class ShowHiScoresTask implements Task<Void> {

    private AnimationRunner runner;
    private Animation highScoresAnimation;

    /**
     * Instantiates a new Show high scores task.
     *
     * @param runner the runner
     */
    public ShowHiScoresTask(AnimationRunner runner) {
        this.runner = runner;
    }

    /**
     * Instantiates a new Show high scores task.
     *
     * @param runner              the runner
     * @param highScoresAnimation the high scores animation
     */
    public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation) {
        this.runner = runner;
        this.highScoresAnimation = highScoresAnimation;
    }

    @Override
    public Void run() {
        this.runner.run(this.highScoresAnimation);
        return null;
    }
}
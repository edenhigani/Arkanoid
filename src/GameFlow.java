import biuoop.DialogManager;
import biuoop.KeyboardSensor;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * The type Hello kitty.
 *
 * @author Eden Higani 315791434
 * @version exercise 6 This class creates the flow of the game with its levels
 */
public class GameFlow {
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private Counter liveCounter;
    private Counter scoreCounter;
    private HighScoresTable table;


    /**
     * Instantiates a new Game flow.
     *
     * @param ar the ar
     * @param ks the ks
     * @param t  the t
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, HighScoresTable t) {
        this.keyboardSensor = ks;
        this.animationRunner = ar;
        this.scoreCounter = new Counter();
        this.liveCounter = new Counter(7);
        this.table = t;
    }

    /**
     * Instantiates a new Game flow.
     *
     * @param ar    the animation runner
     * @param ks    the keyboard sensor
     * @param score the score counter
     * @param lives the lives counter
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, Counter score, Counter lives) {
        this.keyboardSensor = ks;
        this.animationRunner = ar;
        this.scoreCounter = score;
        this.liveCounter = lives;
        this.table = new HighScoresTable(10);
    }

    /**
     * Run uns all the levels one by one by the list.
     *
     * @param levels the levels
     * @param table1  the table
     */
    public void runLevels(List<LevelInformation> levels, HighScoresTable table1) {
        EndScreen end;

        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo,
                    this.animationRunner,
                    this.scoreCounter,
                    this.liveCounter,
                    this.keyboardSensor);

            level.initialize();

            //while player has lives and blocks on the screen
            while (level.getLives().getValue() > 0 && level.getBlockRemover().getRemainingBlocks().getValue() > 0) {
                level.playOneTurn();
                // if he lost a round we decrease number of lives remained
                if (level.getRemover().getCount().getValue() == 0) {
                    level.getLives().decrease(1);
                }
            }
            //if player has no more lives left
            if (this.liveCounter.getValue() == 0) {
                break;
            }
        }
        //checking if the levels ended because of losing or winning the game
        if (this.liveCounter.getValue() == 0) {
            end = new EndScreen(this.keyboardSensor, this.scoreCounter, false);
        } else {
            end = new EndScreen(this.keyboardSensor, this.scoreCounter, true);
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                KeyboardSensor.SPACE_KEY, end));
        if (table1.getRank(this.scoreCounter.getValue()) <= table1.size()) {
            DialogManager dialog = this.animationRunner.getGui().getDialogManager();
            String name = dialog.showQuestionDialog("Name",
                    "What is your name?", "");
            table1.add(new ScoreInfo(this.scoreCounter.getValue(), name));
            try {
                table1.save(new File("highscores.txt"));
            } catch (IOException e1) {
                int i = 0;
                if (i != 0) {
                    System.out.println(3);
                }
            }
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                new HighScoresAnimation(table1)));

    }
}
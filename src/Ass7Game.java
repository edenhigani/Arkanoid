import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Eden Higani 315791434
 * @version exercise 7
 * @since 2019/05/15
 * this class is the main which operates the game.
 */
public class Ass7Game {
    /**
     * this function create game,Initializes it and run it.
     *
     * @param args we will not use the args parameter.
     */
    public static void main(String[] args) {
        String path;
        if (args.length == 0) {
            path = "LevelSets.txt";
        } else {
            path = args[0];
        }

        HighScoresTable table = new HighScoresTable(10);
        File filename = new File("highscores.txt");
        try {
            table.load(filename);
        } catch (IOException e) {
            System.out.println("can not load highscores file");
        }
        HighScoresAnimation highScoresAnimation = new HighScoresAnimation(table);

        GUI gui = new GUI("Arkanoid Game", 800, 600);
        AnimationRunner runner = new AnimationRunner(gui);

        while (true) {
            KeyboardSensor keyboardSensor = runner.getGui().getKeyboardSensor();
            GameFlow flow = new GameFlow(runner, keyboardSensor, new Counter(0), new Counter(7));
            MenuAnimation menu = new MenuAnimation("Arkanoid", keyboardSensor);
            menu.setScoresTable(table);
            menu.setRunner(runner);
            InputStream ireader = ClassLoader.getSystemClassLoader().getResourceAsStream(path);
            InputStreamReader reader = new InputStreamReader(ireader);
            //menu.addSubMenu("s", "game difficulty", new MenuAnimation("s", keyboardSensor), reader);
            // menu.addSubMenuHlp("s", "game difficulty",
            //new MenuAnimation("s", keyboardSensor), reader);
            menu.setReader(reader);
            menu.addSubMenu("s", "game difficulty", new MenuAnimation("s", keyboardSensor));
            menu.addSelection("s", "Start Game", new ShowHiScoresTask(runner, highScoresAnimation) {
                public Void run() {
                    runner.run(((MenuAnimation) menu).getSubMenu());
                    Task<Void> task = (Task<Void>) menu.getSubMenu().getStatus();
                    task.run();
                    return null;
                }
            });
            menu.addSelection("h", "High scores", new ShowHiScoresTask(
                    runner, new KeyPressStoppableAnimation(
                    keyboardSensor, KeyboardSensor.SPACE_KEY, highScoresAnimation
            )));
            menu.addSelection("q", "Quit", new ShowHiScoresTask(runner, highScoresAnimation) {
                public Void run() {
                    System.exit(0);
                    return null;
                }
            });
            runner.run(menu);
            Task<Void> task = (Task<Void>) menu.getStatus();
            task.run();
        }
    }
}
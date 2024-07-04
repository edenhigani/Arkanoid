import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.awt.Image;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * The type Menu animation.
 *
 * @param <T> the type parameter
 */
public class MenuAnimation<T> implements Menu<T> {

    private KeyboardSensor keyboard;
    private boolean stop = false;
    private String title;
    private T status = null;
    private AnimationRunner runner;
    private Map<String, String> keyTitleMap;
    private Map<String, T> keyTaskMap;
    private MenuAnimation subMenu;
    private HighScoresTable scoresTable;
    private Image img = null;
    private java.io.Reader reader;


    /**
     * Instantiates a new Menu animation.
     *
     * @param titleM  the title m
     * @param ks      the ks
     * @param runner1 the runner 1
     */
    public MenuAnimation(String titleM, KeyboardSensor ks, AnimationRunner runner1) {
        this.runner = runner1;
        this.title = titleM;
        this.keyboard = ks;
        this.keyTaskMap = new HashMap<>();
        this.keyTitleMap = new LinkedHashMap<>();
        File file = new File("highscores");
        this.scoresTable = HighScoresTable.loadFromFile(file);

        try {
            img = ImageIO.read(new File("resources/background_images/minion2.jpg"));
        } catch (Exception e) {
            System.out.println("can not read file");
        }
    }

    /**
     * Instantiates a new Menu animation.
     *
     * @param titleM the title m
     * @param ks     the ks
     */
    public MenuAnimation(String titleM, KeyboardSensor ks) {
        this.title = titleM;
        this.keyboard = ks;
        this.keyTaskMap = new HashMap<>();
        this.keyTitleMap = new LinkedHashMap<>();
//        try {
//            img = ImageIO.read(new File("background_images/minion2.jpg"));
//        } catch (Exception e) {
//            System.out.println("can not read file");
//        }
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("background_images/minion2.jpg");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets submenue.
     *
     * @return the submenue
     */
    public Menu getSubmenue() {
        return this.subMenu;
    }


    @Override
    public void addSelection(String key, String message, T returnVal) {
        this.keyTitleMap.put(key, message);
        this.keyTaskMap.put(key, returnVal);
    }

    @Override
    public T getStatus() {
        return this.status;
    }

    public void setReader(java.io.Reader reader1) {
        this.reader = reader1;
    }

    @Override
    public void addSubMenu(String key, String message, Menu<T> subMenu1) {
        addSubMenuHlp(key, message, subMenu1, this.reader);
    }

    public void addSubMenuHlp(String key, String message, Menu subMenuA, java.io.Reader reader1) {
        String line;
        //BufferedReader bufferedReader = null;
        Map<String, String> keyPath = new TreeMap<>();
        MenuAnimation<Object> subMenuB = new MenuAnimation("game difficulty", this.keyboard);
        //try {
//            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        try {
            BufferedReader bf = new BufferedReader(reader1);
            //while ((line = bufferedReader.readLine()) != null) {
            while ((line = bf.readLine()) != null) {
                //e:Easy
                String[] keys = line.split(":");
                subMenuB.getkeyTitleMap().put(keys[0], keys[1]);
                //line = bufferedReader.readLine();
                line = bf.readLine();
                //e:Address
                keyPath.put(keys[0], line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            //bufferedReader.close();
            reader1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //e:Easy ->address
        // h:Hard -> address
        subMenuB.setRunner(this.runner);
        subMenuB.setScoresTable(this.scoresTable);
        for (Map.Entry<String, String> entry : subMenuB.getkeyTitleMap().entrySet()) {
            subMenuB.addSelection(entry.getKey(), entry.getValue(), new ShowHiScoresTask(runner) {
                public Void run() {
                    GameFlow flow = new GameFlow(runner, runner.getGui().getKeyboardSensor(),
                            new Counter(0), new Counter(7));
                    //File f = new File(keyPath.get(entry.getKey()));
                    String s = keyPath.get(entry.getKey());
                    LevelSpecificationReader r = new LevelSpecificationReader();
                    try {
                        //BufferedReader rdr = new BufferedReader((new InputStreamReader(new FileInputStream(f))));
                        //flow.runLevels(r.fromReader(rdr), scoresTable);
                        InputStream ireader = ClassLoader.getSystemClassLoader().
                                getResourceAsStream(s);
                        InputStreamReader rd = new InputStreamReader(ireader);
                        flow.runLevels(r.fromReader(rd), scoresTable);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            });
        }
        this.subMenu = subMenuB;
    }

    /**
     * Gets runner.
     *
     * @return the runner
     */
    public AnimationRunner getRunner() {
        return runner;
    }

    /**
     * Sets runner.
     *
     * @param runner1 the runner
     */
    public void setRunner(AnimationRunner runner1) {
        this.runner = runner1;
    }

    /**
     * Gets title map.
     *
     * @return the title map
     */
    public Map<String, String> getkeyTitleMap() {
        return keyTitleMap;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets sub menu.
     *
     * @return the sub menu
     */
    public MenuAnimation getSubMenu() {
        return subMenu;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.white);
        d.fillRectangle(0, 0, 800, 600);

        d.drawImage(-240, -20, img);
        d.setColor(Color.black);
        d.drawText(300, 80, this.title, 50);
        int i = 0;
        for (Map.Entry<String, String> key : this.keyTitleMap.entrySet()) {
            d.drawText(300, 150 + (i * 50), "(" + key.getKey() + ") " + key.getValue(), 40);
            i++;
        }
        for (String key : this.keyTitleMap.keySet()) {
            if (this.keyboard.isPressed(key)) {
                this.stop = true;
                this.status = this.keyTaskMap.get(key);
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * Sets scores table.
     *
     * @param scoresTable1 the scores table
     */
    public void setScoresTable(HighScoresTable scoresTable1) {
        this.scoresTable = scoresTable1;
    }
}
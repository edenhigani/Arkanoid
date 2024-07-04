import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type Level.
 */
public class Level implements LevelInformation {
    private String levelName;
    private List<Velocity> lstVelocity;
    private int numOfBalls;
    private int paddleSpeed;
    private int paddleWidth;
    private List<Block> lstBlocks = new ArrayList<>();
    private Background background;
    private File bgImage;
    private Color bgColor;
    private int blocksStartX;
    private int blocksStartY;
    private int rowHeight;
    private int numofblocks;
    private String blocksDefLocation;
    private List<String> blockLines = new ArrayList<>();
    private BlocksFromSymbolsFactory factory;
    private List<Ball> ballList = new ArrayList<>();


    /**
     * Instantiates a new Level.
     *
     * @param infoMap    the info map
     * @param blockLines the block lines
     */
    public Level(Map<String, String> infoMap, List<String> blockLines) {
        this.levelName = infoMap.get("level_name");
        this.paddleSpeed = Integer.parseInt(infoMap.get("paddle_speed"));
        this.paddleWidth = Integer.parseInt(infoMap.get("paddle_width"));
        this.lstVelocity = this.setVelocity(infoMap);
        this.numOfBalls = this.lstVelocity.size();
        this.blocksStartX = Integer.parseInt(infoMap.get("blocks_start_x"));
        this.blocksStartY = Integer.parseInt(infoMap.get("blocks_start_y"));
        this.rowHeight = Integer.parseInt(infoMap.get("row_height"));
        this.numofblocks = Integer.parseInt(infoMap.get("num_blocks"));
        this.blocksDefLocation = infoMap.get("block_definitions");
        this.blockLines = blockLines;
        this.ballList = this.generateBallsList();
        setBackground(infoMap.get("background"));
       // FileReader reader = null;
        InputStream ireader = ClassLoader.getSystemClassLoader().getResourceAsStream(this.blocksDefLocation);
        InputStreamReader reader = new InputStreamReader(ireader);
        try {
            //reader = new FileReader(new File(this.blocksDefLocation));
            this.factory = BlocksDefinitionReader.fromReader(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Generate balls list list.
     *
     * @return the list
     */
    public List<Ball> generateBallsList() {
        List<Ball> ballsList = new ArrayList<>();
        for (Velocity v : this.lstVelocity) {
            ballsList.add(new Ball(400, 520, 5, Color.decode("#FF007F")));
        }
        return ballsList;
    }


    /**
     * Sets velocity.
     *
     * @param infoMap the info map
     * @return the velocity
     */
    public List<Velocity> setVelocity(Map<String, String> infoMap) {
        String angle, speed;
        List<Velocity> velocities = new ArrayList<>();
        String[] arr = infoMap.get("ball_velocities").split(" ");
        for (int i = 0; i < arr.length; i++) {
            String[] couple = arr[i].split(",");
            angle = couple[0];
            speed = couple[1];
            Velocity v = new Velocity(1, 1);
            velocities.add(v.fromAngleAndSpeed(Double.parseDouble(angle), Double.parseDouble(speed)));
        }
        return velocities;
    }

    /**
     * Sets background.
     *
     * @param s the s
     */
    public void setBackground(String s) {
        if (s.contains("image")) {
            String fileName = s.substring(6, s.length() - 1);
            this.bgImage = new File(fileName);
            this.bgColor = null;
            this.background = new Background(fileName);
        } else {
            this.bgImage = null;
            ColorsParser parser = new ColorsParser();
            this.bgColor = parser.colorFromString(s);
            this.background = new Background(this.bgColor);
        }
    }

    @Override
    public int numberOfBalls() {
        return this.numOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.lstVelocity;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public Color getColor() {
        return this.bgColor;
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Block> blocks() {
        int i = this.blocksStartY;
        for (String line : this.blockLines) {
            int xtemp = this.blocksStartX;
            char[] charArray = line.toCharArray();
            if (charArray.length == 0) {
                continue;
            }
            for (char s : charArray) {
                if (this.factory.isSpaceSymbol(s + "")) {
                    xtemp += this.factory.getSpaceWidth(s + "");
                }
                if (this.factory.isBlockSymbol(s + "")) {
                    Block b = this.factory.getBlock(s + "", xtemp, i);
                    lstBlocks.add(b);
                    xtemp += b.getCollisionRectangle().getWidth();
                }
            }
            i += this.rowHeight;
        }
        return this.lstBlocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numofblocks;
    }

    @Override
    public List<Ball> balls() {
        return this.generateBallsList();
    }
}

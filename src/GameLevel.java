import java.awt.Color;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.util.List;

/**
 * The type Game.
 *
 * @author Eden Higani 315791434
 * @version exercise 5
 * This class creates a game.
 */
public class GameLevel implements Animation {

    private KeyboardSensor keyboard;
    private boolean running;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private biuoop.Sleeper sleeper;
    private AnimationRunner runner;
    private Counter balls;
    private BallRemover remover;
    private ScoreIndicator score1;
    private BlockRemover blockRemover;
    private Paddle paddle;
    private LevelInformation information;
    private Counter lives;
    private Counter scoreCounter;
    private ScoreTrackingListener scoreListener;
    private LivesIndicator livesIndicator;
    private NameIndicator nameIndicator;
    static final int GUIHEIGHT = 600;
    static final int GUIWIDTH = 800;
    static final int FRAMEWIDTH = 15;

    /**
     * This function is the constructor of the Game class
     * it builds a game and initializes its fields.
     *
     * @param info           the level information
     * @param ar             the ar
     * @param scoreCounter1  the score counter
     * @param liveCounter    the live counter
     * @param keyboardSensor the keyboard sensor
     */
    public GameLevel(LevelInformation info, AnimationRunner ar, Counter scoreCounter1,
                     Counter liveCounter, KeyboardSensor keyboardSensor) {
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        balls = new Counter(2);
        this.runner = ar;
        this.keyboard = keyboardSensor;
        information = info;
        this.lives = liveCounter;
        this.scoreCounter = scoreCounter1;
    }

    /**
     * Gets lives.
     *
     * @return the lives
     */
    public Counter getLives() {
        return this.lives;
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.keyboard.isPressed("p")) {
            PauseScreen pauseScreen = new PauseScreen(this.keyboard);
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY, pauseScreen));
        }
        if (this.getBlockRemover().getRemainingBlocks().getValue() == 0) {
            this.scoreCounter.increase(100);
            this.running = false;
        }

        if (this.balls.getValue() == 0) {
            this.running = false;
        }
        if (this.getRemover().getCount().getValue() == 0) {
            this.getPaddle().moveP();
            this.running = false;
        }
        this.information.getBackground().drawOn(d);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
    }

    /**
     * this function sets the ball remover.
     *
     * @param remover1 the ball remover we set.
     */
    public void setBallRemover(BallRemover remover1) {
        this.remover = remover1;
    }

    /**
     * This function adds a given collidable to the game.
     *
     * @param c the collidable which add to the game.
     */
    public void addCollidable(Collidable c) {
        this.environment.getList().add(c);
    }

    /**
     * This function adds a given sprite to the game.
     *
     * @param s the sprite which add to the game.
     */
    public void addSprite(Sprite s) {
        this.sprites.getList().add(s);
    }

    /**
     * This function returns game environment.
     *
     * @return game environment of the game
     */
    public GameEnvironment getGameEnvironment() {
        return this.environment;
    }

    /**
     * Sets game environment.
     *
     * @param environment1 the game environment object.
     */
    public void setGameEnvironment(GameEnvironment environment1) {
        this.environment = environment1;
    }


    /**
     * this function sets blockremover.
     *
     * @param blockRemover1 the block remover we set.
     */
    public void setBlockRemover(BlockRemover blockRemover1) {
        this.blockRemover = blockRemover1;
    }

    /**
     * this function gets the block remover member.
     *
     * @return blockremover. block remover
     */
    public BlockRemover getBlockRemover() {
        return this.blockRemover;
    }

    /**
     * This function initialize a new game.
     */
    public void initialize() {
        Sprite x = this.information.getBackground();
        this.addSprite(x);
        BlockRemover check = new BlockRemover(this, new Counter());
        this.setBlockRemover(check);
        this.scoreListener = new ScoreTrackingListener(this.scoreCounter);
        check.addHitListener(scoreListener);
        this.score1 = new ScoreIndicator(this.scoreCounter);
        this.setScore1(this.score1);
        this.setBallRemover(new BallRemover(this, this.balls));
        //setting bounds and setting the flor as "deathRegion"
        Rectangle up = new Rectangle(new Point(0, 0), GUIWIDTH, FRAMEWIDTH);
        Block sil = new Block(up, Color.darkGray);
        Rectangle down = new Rectangle(new Point(0, GUIHEIGHT - FRAMEWIDTH + 15), GUIWIDTH, FRAMEWIDTH);
        Block flor = new Block(down, Color.darkGray);
        Rectangle left = new Rectangle(new Point(0, 0), FRAMEWIDTH, GUIHEIGHT);
        Block lwall = new Block(left, Color.darkGray);
        Rectangle rigth = new Rectangle(new Point(GUIWIDTH - FRAMEWIDTH, 0), FRAMEWIDTH, GUIHEIGHT);
        Block rwall = new Block(rigth, Color.darkGray);
        sil.addToGame(this);
        flor.addToGame(this);
        BallRemover deathRegion = this.getRemover();
        flor.addHitListener(this.getRemover());
        this.setBallRemover(deathRegion);
        lwall.addToGame(this);
        rwall.addToGame(this);
        this.addSprite(score1);
        //setting blocks by level
        for (Block block : this.information.blocks()) {
            block.addHitListener(check);
            check.getRemainingBlocks().increase(1);
            block.addToGame(this);
        }
        //setting the paddle
        Paddle paddleE = new Paddle(this.runner.getGui(),
                this.information.paddleSpeed(), this.information.paddleWidth());
        paddleE.setColor(this.information.getColor());
        paddleE.addToGame(this);
        this.setPaddle(paddleE);
        this.livesIndicator = new LivesIndicator(this.lives);
        this.livesIndicator.addToGame(this);
        this.livesIndicator = new LivesIndicator(this.lives);
        this.livesIndicator.addToGame(this);
        this.nameIndicator = new NameIndicator(this.information.levelName());
        this.nameIndicator.addToGame(this);
    }

    /**
     * this function gets the current score.
     *
     * @return score the score member
     */
    public ScoreIndicator getScore1() {
        return this.score1;
    }

    /**
     * this function sets the number of balls.
     *
     * @param balls1 number of balls we set.
     */
    public void setBalls(Counter balls1) {
        this.balls = balls1;
    }

    /**
     * this function sets the ball remover.
     *
     * @param remover1 removers we set.
     */
    public void setRemover(BallRemover remover1) {
        this.remover = remover1;
    }

    /**
     * this function gets the ball remover.
     *
     * @return remover the ball remover member
     */
    public BallRemover getRemover() {
        return this.remover;
    }


    /**
     * Sets balls and paddle in a turn.
     */
    public void setBallsNpaddle() {
        int i;
        List<Ball> ballLst = this.information.balls();
        this.getPaddle().moveP();
        for (i = 0; i < this.information.numberOfBalls(); i++) {
            Ball ball = ballLst.get(i);
            ball.setVelocity(this.information.initialBallVelocities().get(i));
            ball.addToGame(this);
            ball.getGameE().addCollidable((Collidable) this.getPaddle());
        }
        //setting number of balls
        this.getRemover().setRemainingBalls(new Counter(i));
    }

    /**
     * this function if for running a single turn of the game.
     */
    public void playOneTurn() {
        setBallsNpaddle();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        this.sleeper = new Sleeper();
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
    }

    /**
     * This function run the game -- start the animation loop.
     */
    public void run() {
        LivesIndicator lives1 = new LivesIndicator(new Counter(4));
        this.addSprite(lives1);
        while (lives1.getCounter().getValue() > 0) {
            playOneTurn();
            lives1.getCounter().decrease(1);
        }
        return;
    }

    /**
     * this function sets the score.
     *
     * @param scores the scores we set.
     */
    public void setScore1(ScoreIndicator scores) {
        this.score1 = scores;
    }

    /**
     * this function removes a collidable from game.
     *
     * @param c the collidable we remove.
     */
    public void removeCollidable(Collidable c) {
        java.util.ArrayList<Collidable> colliList = this.getGameEnvironment().getList();
        colliList.remove(c);
        this.getGameEnvironment().setCollidables(colliList);
    }

    /**
     * this function removes a sprite from game.
     *
     * @param s the sprite we remove.
     */
    public void removeSprite(Sprite s) {
        java.util.ArrayList<Sprite> sprites1 = this.getSprites().getList();
        sprites1.remove(s);
        SpriteCollection spritesList = new SpriteCollection();
        spritesList.setList(sprites1);
        this.setSprites(spritesList);
    }

    /**
     * this function gets the sprites of game.
     *
     * @return sprites of game.
     */
    public SpriteCollection getSprites() {
        return this.sprites;
    }

    /**
     * this function sets the sprites of game.
     *
     * @param sprites1 paddle we want to set.
     */
    public void setSprites(SpriteCollection sprites1) {
        this.sprites = sprites1;
    }

    /**
     * this function sets the paddle of game.
     *
     * @param p paddle we want to set.
     */
    public void setPaddle(Paddle p) {
        this.paddle = p;
    }

    /**
     * this function gets the paddle member of game.
     *
     * @return paddle
     */
    public Paddle getPaddle() {
        return this.paddle;
    }
}

import java.awt.Color;
import java.util.Map;

/**
 * The type Creator of blocks.
 */
public class CreatorOfBlocks implements BlockCreator {
    private Map<Integer, String> hitColorMap;
    private Color stroke;
    private int height;
    private int width;
    private int hits;

    /**
     * Instantiates a new Creator of blocks.
     *
     * @param map1    the fill map
     * @param height1 the height
     * @param width1  the width
     * @param hits1   the hits
     * @param stroke1 the stroke
     */
    public CreatorOfBlocks(Map<Integer, String> map1, int height1, int width1, int hits1, Color stroke1) {
        this.height = height1;
        this.width = width1;
        this.hits = hits1;
        this.stroke = stroke1;
        this.hitColorMap = map1;
    }

    @Override
    public Block create(int xpos, int ypos) {
        return new Block(new Rectangle(new Point(xpos, ypos), width, height), hitColorMap, hits, stroke);
    }
}

import java.util.Map;

/**
 * The type Blocks from symbols factory.
 */
public class BlocksFromSymbolsFactory {
    //map from key to value of space.
    private Map<String, Integer> spacerWidths;
    //map from key to value of block.
    private Map<String, BlockCreator> blockCreators;

    /**
     * Instantiates a new Blocks from symbols factory.
     *
     * @param spacerWidths1  the spacer widths
     * @param blockCreators1 the block creators
     */
    public BlocksFromSymbolsFactory(Map<String, Integer> spacerWidths1, Map<String, BlockCreator> blockCreators1) {
        this.spacerWidths = spacerWidths1;
        this.blockCreators = blockCreators1;
    }

    /**
     * this function add to the factory block.
     *
     * @param key   the key to the block.
     * @param value the block.
     */
    public void addBlock(String key, BlockCreator value) {
        this.blockCreators.put(key, value);
    }

    /**
     * this function add to the factory space.
     *
     * @param key   the key to the space.
     * @param value the size of the space.
     */
    public void addSpace(String key, Integer value) {
        this.spacerWidths.put(key, value);
    }


    /**
     * returns true if 's' is a valid space symbol.
     *
     * @param s the s
     * @return the boolean
     */
    public boolean isSpaceSymbol(String s) {
        return this.spacerWidths.containsKey(s);
    }

    /**
     * returns true if 's' is a valid block symbol.
     *
     * @param s the s
     * @return the boolean
     */
    public boolean isBlockSymbol(String s) {
        return this.blockCreators.containsKey(s);
    }

    /**
     * Return a block according to the definitions associated
     * with symbol s. The block will be located at position (xpos, ypos).
     *
     * @param s    the symbol of block
     * @param xpos the xpos
     * @param ypos the ypos
     * @return the block
     */

    public Block getBlock(String s, int xpos, int ypos) {
        return this.blockCreators.get(s).create(xpos, ypos);
    }

    /**
     * Returns the width in pixels associated with the given spacer-symbol.
     *
     * @param s the symbol
     * @return the space width
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }
}
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * The type Blocks definition reader.
 */
public class BlocksDefinitionReader {

    /**
     * Reads blocks from symbols factory.
     *
     * @param reader the reader
     * @return the blocks from symbols factory
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        BlocksFromSymbolsFactory factor;
        BufferedReader is = new BufferedReader(reader);
        String line;
        Color defstroke = null, deffillC = null;
        Color bstroke = null, bfillC = null;
        int defheight = 0, defwidth = 0, defhitPoints = 0;
        int bheight = 0, bwidth = 0, bhitPoints = 0;
        Map<String, Integer> spacerWidths = new TreeMap<>();
        Map<String, BlockCreator> blockCreators = new TreeMap<>();

        try {
            while ((line = is.readLine()) != null) {
                bhitPoints = 0;
                if (!line.startsWith("#")) {
                    String[] parts = line.split(" ");
                    if (parts[0].equals("default")) {
                        for (String part : parts) {
                            if (part.contains("height")) {
                                String[] parts1 = part.split(":");
                                defheight = Integer.parseInt(parts1[1]);
                            }
                            if (part.contains("width")) {
                                String[] parts1 = part.split(":");
                                defwidth = Integer.parseInt(parts1[1]);
                            }

                            if (part.contains("hit_points")) {
                                String[] parts1 = part.split(":");
                                defhitPoints = Integer.parseInt(parts1[1]);
                            }

                            if (part.contains("stroke")) {
                                String[] parts1 = part.split(":");
                                defstroke = new ColorsParser().colorFromString(parts1[1]);
                            }

                            Map<Integer, String> fillMap = new TreeMap<>();
                            if (part.contains("fill-")) {
                                String[] parts1 = part.split(":");
                                int hits = Integer.parseInt(part.charAt(5) + "");

                                //colorMap.put(hits,new ColorsParser().colorFromString(parts1[1]));
                                fillMap.put(hits, parts1[1]);
                            } else {
                                String[] parts1 = part.split(":");
                                if (part.contains("fill:")) {
                                    fillMap.put(1, parts1[1]);
                                }
                            }

                        }
                    } else if (parts[0].equals("bdef")) {
                        Map<Integer, String> fillMap = new TreeMap<>();
                        // blockCreators.put(line.charAt(12) + "", new CreatorOfBlocks(line.substring(15)));
                        for (String part : parts) {
                            if (part.contains("height")) {
                                String[] parts1 = part.split(":");
                                bheight = Integer.parseInt(parts1[1]);
                            }
                            if (part.contains("width")) {
                                String[] parts1 = part.split(":");
                                bwidth = Integer.parseInt(parts1[1]);
                            }

                            if (part.contains("hit_points")) {
                                String[] parts1 = part.split(":");
                                bhitPoints = Integer.parseInt(parts1[1]);
                            }

                            if (part.contains("stroke")) {
                                String[] parts1 = part.split(":");
                                bstroke = new ColorsParser().colorFromString(parts1[1]);
                            }

                            if (part.contains("fill-")) {
                                String[] parts1 = part.split(":");
                                int hits = Integer.parseInt(part.charAt(5) + "");

                                //colorMap.put(hits,new ColorsParser().colorFromString(parts1[1]));
                                fillMap.put(hits, parts1[1]);
                            } else {
                                String[] parts1 = part.split(":");
                                if (part.contains("fill:")) {
                                    if (bhitPoints == 0) {
                                        bhitPoints = defhitPoints;
                                    }
                                    fillMap.put(1, parts1[1]);
                                }
                            }
                            // blockCreators.put(line.charAt(12) + "", new CreatorOfBlocks(line.substring(15)));
                        }
                        if (bheight == 0) {
                            bheight = defheight;
                        }
                        if (bwidth == 0) {
                            bwidth = defwidth;
                        }
                        if (bhitPoints == 0) {
                            bhitPoints = defhitPoints;
                        }
                        if (bstroke == null) {
                            bstroke = defstroke;
                        }

                        blockCreators.put(line.charAt(12) + "",
                                new CreatorOfBlocks(fillMap, bheight, bwidth, bhitPoints, bstroke));

                    } else if (parts[0].equals("sdef")) {
                        String symbol;
                        for (String part : parts) {
                            if (part.contains("symbol")) {
                                String[] symbolPart = part.split(":");
                                symbol = symbolPart[1];
                                String[] widthPart = parts[2].split(":");

                                spacerWidths.put(symbol, Integer.parseInt(widthPart[1]));
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        factor = new BlocksFromSymbolsFactory(spacerWidths, blockCreators);
        return factor;
    }
}
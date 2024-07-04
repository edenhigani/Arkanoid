import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * The type Level specification reader.
 */
public class LevelSpecificationReader {

    /**
     * From reader list.
     *
     * @param reader the reader
     * @return the list
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        List<LevelInformation> levels = new LinkedList<LevelInformation>();
        List<String> levelStrings = new LinkedList<String>();
        BufferedReader is = new BufferedReader(reader);
        try {
            String line;
            while ((line = is.readLine()) != null) {
                if (line.equals("START_LEVEL")) {
                    while (!line.equals("END_LEVEL")) {
                        levelStrings.add(line);
                        line = is.readLine();
                    }
                    levels.add(readOneLevel(levelStrings));
                    levelStrings.clear();
                }
            }
        } catch (IOException e1) {
            System.out.println(" Something went wrong while reading !");
        }
        try {
            is.close();
        } catch (IOException e) {
            System.out.println(" Failed closing the file !");
        }
        return levels;
    }

    /**
     * Read one level level information.
     *
     * @param list the list
     * @return the level information
     */
    public LevelInformation readOneLevel(List<String> list) {
        Level level = null;
        java.io.Reader reader = null;
        Block b;
        int count = 0;
        String str;
        LinkedList<Block> blist = new LinkedList<Block>();
        //BlocksFromSymbolsFactory bfsf = null;
        Map<String, String> map = new HashMap<>();

        String line, key, val;
        String[] parts;

        int x = 0, y = 0, tempX, rowHeight = 0;
        List<String> blockLines = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            parts = list.get(i).split(":");
            if (!list.get(i).equals("START_BLOCKS")) {
                if (!parts[0].equals("START_LEVEL") && !parts[0].equals("")) {
                    map.put(parts[0], parts[1]);
                }
            } else {
                i++;
                while (!list.get(i).equals("END_BLOCKS")) {
                    blockLines.add(list.get(i));
                    i++;
                }
                break;
            }
        }
        if (map.size() < 10) {
            System.exit(0);
        }
        level = new Level(map, blockLines);
        return level;
    }
}

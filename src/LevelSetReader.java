import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

/**
 * The type Level set reader.
 */
public class LevelSetReader {

    private Map<String, String> keyTitle = new TreeMap<>();
    private Map<String, String> keyPath = new TreeMap<>();

    /**
     * Instantiates a new Level set reader.
     */
    public LevelSetReader() {
        String line;
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader((new InputStreamReader(
                    new FileInputStream("resources/definitions/LevelSets.txt"))));
            while ((line = bufferedReader.readLine()) != null) {
                String[] keys = line.split(":");
                this.keyTitle.put(keys[0], keys[1]);
                line = bufferedReader.readLine();
                keyPath.put(keys[0], line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Gets title.
     *
     * @return the title
     */
    public Map getTitle() {
        return this.keyTitle;
    }

    /**
     * Gets path.
     *
     * @return the path
     */
    public Map getPath() {
        return this.keyPath;
    }


}

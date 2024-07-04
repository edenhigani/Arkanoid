import java.io.File;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * The type High scores table.
 */
public class HighScoresTable {

    private int size;
    private ArrayList<ScoreInfo> list;

    /**
     * Create an empty high-scores table with the specified size.
     * The size means that the table holds up to size top scores.
     *
     * @param size the size
     */
    public HighScoresTable(int size) {
        this.size = size;
        list = new ArrayList<>();
    }

    /**
     * Add a high-score.
     *
     * @param score the score
     */
    public void add(ScoreInfo score) {
        int i;
        if (this.list.size() < size) {
            for (i = 0; i < this.list.size(); i++) {
                if (this.list.get(i).getScore() < score.getScore()) {
                    break;
                }
            }
            this.list.add(i, score);
        } else {
            for (i = 0; i < this.list.size(); i++) {
                if (this.list.get(i).getScore() <= score.getScore()) {
                    break;
                }
            }
            this.list.add(i, score);
            this.list.remove(this.size);
        }
    }

    /**
     * Return table size.
     *
     * @return the int
     */
    public int size() {
        return this.size;
    }

    /**
     * Gets high scores.
     *
     * @return the high scores
     */
// Return the current high scores.
    // The list is sorted such that the highest
    // scores come first.
    public List<ScoreInfo> getHighScores() {
        return this.list;
    }

    /**
     * return the rank of the current score.
     *
     * @param score the score
     * @return the rank
     */

    public int getRank(int score) {
        int count = 0;
        for (int i = 0; i < this.list.size(); i++) {
            if (this.list.get(i).getScore() < score) {
                break;
            }
            count++;
        }
        return (count + 1);
    }

    /**
     * Clear.
     */
// Clears the table
    public void clear() {
        this.list.clear();
    }

    /**
     * Load.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
// Load table data from file.
    // Current table data is cleared.
    public void load(File filename) throws IOException {
//        //this.clear();
//        try {
//            FileInputStream fileIn = new FileInputStream(filename);
//            ObjectInputStream in = new ObjectInputStream(fileIn);
//            this.list = (ArrayList<ScoreInfo>) in.readObject();
//            in.close();
//            fileIn.close();
//        } catch (IOException i) {
//            i.printStackTrace();
//            return;
//        } catch (ClassNotFoundException c) {
//            System.out.println("Employee class not found");
//            c.printStackTrace();
//            return;
//        }
        boolean bool = false;
        BufferedReader reader = null;
        String name = null, score = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
        } catch (FileNotFoundException e) {
            System.out.println("blablabla");
        }
        String line;
        while ((line = reader.readLine()) != null) {
            if (!bool) {
                name = line;
                bool = true;
            } else {
                score = line;
                this.add(new ScoreInfo(Integer.parseInt(score), name));
                bool = false;
            }
        }
        reader.close();
    }

    /**
     * Save.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
// Save table data to the specified file.
    public void save(File filename) throws IOException {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filename)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (ScoreInfo scoreInfo : this.list) {
            writer.println(scoreInfo.getName());
            writer.println(scoreInfo.getScore());
        }


        writer.close();
    }

    /**
     * Load from file high scores table.
     *
     * @param filename the filename
     * @return the high scores table
     */
// Read a table from file and return it.
    // If the file does not exist, or there is a problem with
    // reading it, an empty table is returned.
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable table = null;
        if (!filename.canRead() || !filename.exists()) {
            return null;
        } else {
            try {
                table.load(filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return table;
        }
    }
}
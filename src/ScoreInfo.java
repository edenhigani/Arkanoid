import java.io.Serializable;

public class ScoreInfo implements Serializable {

    private int score;
    private String name;

    public ScoreInfo(int score1, String name1) {
        this.score = score1;
        this.name = name1;
    }

    public int getScore() {
        return this.score;
    }

    public String getName() {
        return this.name;
    }
}

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Colors parser.
 */
public class ColorsParser {

    private Map<String, Color> colors;

    /**
     * Instantiates a new Colors parser.
     */
    public ColorsParser() {
        this.colors = new HashMap<>();
        this.colors.put("color(black)", Color.black);
        this.colors.put("color(yellow)", Color.yellow);
        this.colors.put("color(blue)", Color.blue);
        this.colors.put("color(cyan)", Color.cyan);
        this.colors.put("color(gray)", Color.gray);
        this.colors.put("color(lightGray)", Color.lightGray);
        this.colors.put("color(green)", Color.green);
        this.colors.put("color(orange)", Color.orange);
        this.colors.put("color(pink)", Color.pink);
        this.colors.put("color(red)", Color.red);
        this.colors.put("color(white)", Color.white);
        this.colors.put("color(darkGray)", Color.darkGray);
        this.colors.put("color(magenta)", Color.magenta);

    }

    /**
     * parse color definition and return the specified color.
     *
     * @param s the string
     * @return the color
     */
    public java.awt.Color colorFromString(String s) {
        Color color;
        //name
        if (this.colors.containsKey(s)) {
            return this.colors.get(s);
        //RGB
        } else {
            String[] colors1 = s.substring(10, s.length() - 2).split(",");
            color = new Color(
                    Integer.parseInt(colors1[0].trim()),
                    Integer.parseInt(colors1[1].trim()),
                    Integer.parseInt(colors1[2].trim())
            );
        }
        return color;
    }
}
import biuoop.DrawSurface;

import java.util.ArrayList;

/**
 * @author Eden Higani 315791434
 * @version exercise 6
 * this class creates sprite collection.
 */
public class SpriteCollection {

    private java.util.ArrayList<Sprite> spriteList;

    /**
     * This function is for setting the spriteList member.
     *
     * @param spriteList1 is the list of sprites we want to set.
     */
    public void setList(java.util.ArrayList<Sprite> spriteList1) {
        this.spriteList = spriteList1;
    }


    /**
     * This function is a constructor of the SpriteCollection.
     * and builds a sprite collection.
     */
    public SpriteCollection() {
        this.spriteList = new java.util.ArrayList<Sprite>();
    }

    /**
     * This function returns the list of the sprites.
     *
     * @return the list of the sprites
     */
    public ArrayList<Sprite> getList() {
        return this.spriteList;
    }

    /**
     * This function adds the given Sprite to the collection of sprites.
     *
     * @param s the given sprite.
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     * This function calls timePassed(d) on all sprites.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < spriteList.size(); i++) {
            this.spriteList.get(i).timePassed();
        }
    }

    /**
     * this function calls drawOn(d) on all sprites.
     *
     * @param d the draw surface.
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < this.spriteList.size(); i++) {
            this.spriteList.get(i).drawOn(d);
        }
    }
}
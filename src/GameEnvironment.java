import java.util.ArrayList;

/**
 * @author Eden Higani 315791434
 * @version exercise 6
 * This class creates GameEnvironment
 */
public class GameEnvironment {

    private java.util.ArrayList<Collidable> collidables;

    /**
     * this function is a set of the collidables in the game environment.
     *
     * @param collidables1 the list of collidebles we set.
     */
    public void setCollidables(ArrayList<Collidable> collidables1) {
        this.collidables = collidables1;
    }


    /**
     * This function is the constructor of the GameEnvironment class it builds a
     * game environment and initializes its fields.
     */
    public GameEnvironment() {
        this.collidables = new java.util.ArrayList<Collidable>();
    }

    /**
     * This function adds the given collidable to the environment.
     *
     * @param c the given collidable
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * This function returns the list of the collidable.
     *
     * @return the list of the collidable.
     */
    public ArrayList<Collidable> getList() {
        return this.collidables;
    }


    /**
     * This function checks If this object will collide with any of the
     * collidables in this collection, returns the information
     * about the closest collision that is going to occur.
     * Else returns null.
     *
     * @param trajectory the trajectory of the object.
     * @return the information about the closest collision that is going to
     * occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        int place = 0;
        Point closeCollision = null, helpP = trajectory.end();
        double dist, minDist = -1;

        if (this.getList().size() == 0) {
            return null;
        }
        for (int i = 0; i < this.getList().size(); i++) {
            helpP = trajectory
                    .closestIntersectionToStartOfLine(this.getList()
                            .get(i).getCollisionRectangle());
            if (helpP != null) {
                dist = trajectory.start().distance(helpP);
                if ((dist < minDist) || minDist == -1) {
                    minDist = dist;
                    place = i;
                    closeCollision = helpP;
                }
            }
        }
        if (closeCollision == null) {
            return null;
        }
        return new CollisionInfo(this.collidables.get(place), closeCollision);
    }
}
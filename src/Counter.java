/**
 * @author Eden Higani 315791434
 * @version exercise 6
 * This class creates a Counter.
 */
public class Counter {

    private int count;

    /**
     * this function is the constructor of this class.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * this function is a constructor of this class.
     *
     * @param num int we want to set.
     */
    public Counter(int num) {
        this.count = num;
    }

    /**
     * add number to current count.
     *
     * @param number add this number to the current count.
     */
    public void increase(int number) {
        this.count = this.count + number;
    }

    /**
     * subtract number from current count.
     *
     * @param number Subtract this parameter from the current count.
     */
    public void decrease(int number) {
        this.count = this.count - number;
    }

    /**
     * get current count.
     *
     * @return the value of the counter.
     */
    public int getValue() {
        return this.count;
    }
}
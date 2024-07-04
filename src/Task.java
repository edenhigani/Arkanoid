/**
 * @author Eden Higani 315791434
 * @version exercise 6
 * The interface Task.
 *
 * @param <T> the type parameter
 */
public interface Task<T> {
    /**
     * Run task.
     *
     * @return the t
     */
    T run();
}
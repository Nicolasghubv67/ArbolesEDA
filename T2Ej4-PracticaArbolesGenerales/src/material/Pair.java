
package material;

/**
 *
 * @author mayte
 * @param <E>
 * @param <D>
 */
public class Pair<E,D> {
 
    private final E first;
    private final D second;

    public Pair(E first, D second) {
        this.first = first;
        this.second = second;
    }

    public E getFirst() {
        return first;
    }

    public D getSecond() {
        return second;
    }
    
}

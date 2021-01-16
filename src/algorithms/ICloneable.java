package algorithms;

/**
 * Interface providing the contract for cloning. Provides a more
 * open api for cloning by extending the Cloneable interface and using generics
 * to eliminate the need for type casting.
 *
 * @param <T> the type of object to clone
 * @author Caleb Bostic-Gardner
 */
public interface ICloneable<T> extends Cloneable {
    T deepClone() throws CloneNotSupportedException;
}

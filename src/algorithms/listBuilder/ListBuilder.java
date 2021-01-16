package algorithms.listBuilder;

import algorithms.ICloneable;

import java.util.Collection;
import java.util.List;

/**
 * Builder interface which provides the functionality of the Builder pattern
 * as a potential wrapper to the Java Collections framework
 *
 * @author Camilo Espinosa (https://github.com/Camiloesp)
 */

public interface ListBuilder<T> extends ICloneable<ListBuilder<T>> {
    /**
     * This method will build a list that is not sorted.
     *
     * @return Returns an Integer array
     */
    ListBuilder<T> makeRandom();

    /**
     * This method will build an ordered list
     *
     * @return the builder of the specified type
     */
    ListBuilder<T> makeSorted();

    /**
     * @param collection the items to add
     * @return the builder of the specified type
     */
    ListBuilder<T> add(Collection<T> collection);

    /**
     * Adds an item to the builder's collection
     *
     * @param item the item to be added to the builder
     * @return the builder of the specified type
     */
    ListBuilder<T> add(T item);

    /**
     * Shuffles the items in the builder
     *
     * @return the builder of the specified type
     */
    ListBuilder<T> shuffle();

    /**
     * This method will print the elements of an array
     *
     * @return the builder of the specified type
     */
    ListBuilder<T> print();

    /**
     * Creates the list based on the actions applied to the builder
     *
     * @return the built list
     */
    List<T> build();
}

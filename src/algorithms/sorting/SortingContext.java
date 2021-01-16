package algorithms.sorting;

import algorithms.ICloneable;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Interface providing the contract for a Sorting Strategy
 *
 * @param <T> the type of items for the strategy to sort
 * @author Caleb Bostic-Gardner
 */
public interface SortingContext<T extends Comparable<T>> extends ICloneable<SortingContext<T>> {
    /**
     * Method which provides the delegating sorting call to the strategy
     */
    void sort();

    /**
     * Sets the strategy's comparator
     *
     * @param comparator the comparator the strategy should use
     */
    void setComparator(Comparator<T> comparator);

    /**
     * Gets the strategy's comparator
     *
     * @return the comparator for the strategy
     */
    Comparator<T> getComparator();

    /**
     * Sets the strategy's sorting algorithm
     *
     * @param sortingAlgorithm the sorting algorithm the strategy should use
     */
    void setSortingAlgorithm(SortingAlgorithm<T> sortingAlgorithm);

    /**
     * Gets the strategy's sorting algorithm
     *
     * @return the sorting algorithm from the strategy
     */
    SortingAlgorithm<T> getSortingAlgorithm();

    /**
     * Sets the strategy's items
     *
     * @param list the list the strategy should use
     */
    void setItems(List<T> list);

    /**
     * Gets the strategy's items
     *
     * @return the list from the strategy
     */
    List<T> getItems();

    /**
     * Gets the strategy's iterator
     *
     * @return the iterator from the strategy
     */
    Iterator<T> getIterator();
}

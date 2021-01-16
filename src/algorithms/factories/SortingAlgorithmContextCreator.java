package algorithms.factories;

import algorithms.sorting.SortingAlgorithm;
import algorithms.sorting.SortingContext;

import java.util.Comparator;
import java.util.List;

/**
 * Factory method base class for constructing parts and then the whole SortingAlgorithmContext
 *
 * @param <T> the type of objects the sorting context will sort
 * @author Caleb Bostic-Gardner
 * @see SortingContext
 */
public abstract class SortingAlgorithmContextCreator<T extends Comparable<T>> {

    /**
     * Creation method for building the whole sorting context from its constituent parts
     *
     * @param algorithmType the type of sorting algorithm the context should use
     * @param items         the items for the context to sort
     * @return the completed sorting context
     */
    public SortingContext<T> makeSortingContext(SortingAlgorithmType algorithmType, List<T> items) {
        return makeSortingContext(items, makeSortingAlgorithm(algorithmType), makeComparator());
    }

    /**
     * Abstract method to build the comparator
     *
     * @return the complete comparator for the context
     */
    protected abstract Comparator<T> makeComparator();

    /**
     * Abstract method to build the sorting algorithm
     *
     * @param algorithmType the type of sorting algorithm to create
     * @return the complete sorting algorithm for the context
     */
    protected abstract SortingAlgorithm<T> makeSortingAlgorithm(SortingAlgorithmType algorithmType);

    /**
     * Abstract method to build the sorting context
     *
     * @param items      the items for the context to sort
     * @param algorithm  the sorting algorithm for the context
     * @param comparator the comparator the context should use
     * @return the complete sorting context
     */
    protected abstract SortingContext<T> makeSortingContext(List<T> items, SortingAlgorithm<T> algorithm, Comparator<T> comparator);
}

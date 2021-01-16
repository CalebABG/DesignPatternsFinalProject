package algorithms.factories;

import algorithms.sorting.DefaultSortingAlgorithmContext;
import algorithms.sorting.SortingAlgorithm;
import algorithms.sorting.SortingContext;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Default implementation of the SortingAlgorithmContextCreator
 *
 * @param <T> the type of objects the context will sort
 * @author Caleb Bostic-Gardner
 * @see algorithms.factories.SortingAlgorithmContextCreator
 */
public class DefaultSortingAlgorithmContextCreator<T extends Comparable<T>> extends SortingAlgorithmContextCreator<T> {
    protected SortingAlgorithmFactory<T> factory;

    /**
     * Constructor used to initialize the sorting algorithm factory (using default factory)
     */
    public DefaultSortingAlgorithmContextCreator() {
        factory = new DefaultSortingAlgorithmFactory<>();
    }

    /**
     * Overriding method to construct the comparator for the context to use
     *
     * @return a comparator which the sorting context will use
     */
    @Override
    protected Comparator<T> makeComparator() {
        return Comparator.naturalOrder();
    }

    /**
     * Overriding method for constructing a sorting algorithm
     *
     * @param algorithmType the type of sorting algorithm
     * @return associated sorting algorithm implementation
     */
    @Override
    protected SortingAlgorithm<T> makeSortingAlgorithm(SortingAlgorithmType algorithmType) {
        return factory.makeSortingAlgorithm(algorithmType);
    }

    /**
     * Creation method for building the whole sorting context from its constituent parts
     *
     * @param algorithm  the algorithm the context should use
     * @param items      the items for the context to sort
     * @param comparator the comparator for the context to use
     * @return the completed sorting context
     */
    @Override
    protected SortingContext<T> makeSortingContext(List<T> items, SortingAlgorithm<T> algorithm, Comparator<T> comparator) {
        return new DefaultSortingAlgorithmContext<>(items, algorithm, comparator);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaultSortingAlgorithmContextCreator<?> that = (DefaultSortingAlgorithmContextCreator<?>) o;
        return Objects.equals(factory, that.factory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(factory);
    }

    @Override
    public String toString() {
        return "DefaultSortingAlgorithmContextCreator {" +
                "factory=" + factory +
                ", comparator=" + makeComparator() +
                '}';
    }
}

package algorithms.sorting;

import java.util.Comparator;
import java.util.List;

/**
 * Default implementation of the SortingAlgorithmContext. When the default constructor is called
 * the context will be initialized with a new instance of the DefaultSortingAlgorithm.
 *
 * @param <T> the type of items the context will sort
 * @author Caleb Bostic-Gardner
 * @see BaseSortingAlgorithmContext
 */
public class DefaultSortingAlgorithmContext<T extends Comparable<T>> extends BaseSortingAlgorithmContext<T> {
    /**
     * Default constructor
     */
    public DefaultSortingAlgorithmContext() {
        super(null, new DefaultSortingAlgorithm<>(), Comparator.naturalOrder());
    }

    /**
     * Constructor for initializing the context with a list of items.
     * By default the context will be initialized with a new instance of the DefaultSortingAlgorithm.
     *
     * @param items the items for the context
     */
    public DefaultSortingAlgorithmContext(List<T> items) {
        this(items, new DefaultSortingAlgorithm<>());
    }

    /**
     * Constructor for creating a sorting context from a SortingAlgorithmContextMemento
     *
     * @param memento the memento to restore from
     * @see SortingAlgorithmContextMemento
     */
    public DefaultSortingAlgorithmContext(SortingAlgorithmContextMemento<T> memento) {
        super(memento);
    }

    /**
     * Constructor for creating a sorting context with a list of items and a comparator.
     * By default the context will be initialized with a new instance of the DefaultSortingAlgorithm.
     *
     * @param items      the items for the context
     * @param comparator the comparator the context should use
     */
    public DefaultSortingAlgorithmContext(List<T> items, Comparator<T> comparator) {
        super(items, new DefaultSortingAlgorithm<>(), comparator);
    }

    /**
     * Constructor for creating a sorting context with a list of items and a sorting algorithm.
     *
     * @param items           the items for the context
     * @param sortingStrategy the sorting algorithm (strategy) the context should use
     * @see SortingContext
     */
    public DefaultSortingAlgorithmContext(List<T> items, SortingAlgorithm<T> sortingStrategy) {
        super(items, sortingStrategy);
    }

    /**
     * Constructor for creating a sorting context with a list of items, a a sorting algorithm, and a comparator.
     *
     * @param items           he items for the context
     * @param sortingStrategy the sorting algorithm (strategy) the context should use
     * @param comparator      the comparator the context should use
     */
    public DefaultSortingAlgorithmContext(List<T> items, SortingAlgorithm<T> sortingStrategy, Comparator<T> comparator) {
        super(items, sortingStrategy, comparator);
    }

    /**
     * Overriding template method for initializing a given list of items
     *
     * @param list the list to initialize
     */
    @Override
    protected void initialize(List<T> list) { /* Does nothing */ }
}

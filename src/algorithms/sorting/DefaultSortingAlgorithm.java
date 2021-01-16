package algorithms.sorting;

import java.util.Comparator;
import java.util.List;

/**
 * Defaults to QuickSort implementation for a SortingAlgorithm. Acts a decorator which provides
 * the implementation to the SortingAlgorithm interface through delegation
 *
 * @param <T> the type of items this sorting algorithm will sort
 * @author Caleb Bostic-Gardner
 * @see algorithms.sorting.BaseSortingAlgorithm
 */
public class DefaultSortingAlgorithm<T extends Comparable<T>> extends BaseSortingAlgorithm<T> {
    protected SortingAlgorithm<T> defaultSortingAlgorithm;

    /**
     * Default constructor
     */
    public DefaultSortingAlgorithm() {
        defaultSortingAlgorithm = new QuickSort<>();
    }

    /**
     * Delegating method for sorting
     *
     * @param list       The list of objects to be sorted
     * @param comparator The comparator to be used when sorting
     */
    @Override
    public void sort(List<T> list, Comparator<T> comparator) {
        defaultSortingAlgorithm.sort(list, comparator);
    }

    /**
     * Delegating method for setting completion status of sorting algorithm
     *
     * @param completionStatus the status to set
     */
    @Override
    public void setCompletionStatus(boolean completionStatus) {
        defaultSortingAlgorithm.setCompletionStatus(completionStatus);
    }

    /**
     * Delegating method of getting the completion status of the sorting algorithm
     *
     * @return whether the sorting algorithm has finished
     */
    @Override
    public boolean sortComplete() {
        return defaultSortingAlgorithm.sortComplete();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " {" +
                "decoratedSortingAlgorithm=(" + defaultSortingAlgorithm +
                "), id='" + id + '\'' +
                ", sortComplete=" + sortComplete +
                '}';
    }
}

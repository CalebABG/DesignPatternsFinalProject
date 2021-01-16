package algorithms.sorting;

import algorithms.ICloneable;

import java.util.Comparator;
import java.util.List;

/**
 * Interface providing the contract for a Sorting Algorithm
 *
 * @param <T> The type of objects the implementation class will sort
 * @author Caleb Bostic-Gardner
 */
public interface SortingAlgorithm<T extends Comparable<T>> extends ICloneable<SortingAlgorithm<T>> {
    /**
     * Method to sort the list of objects
     *
     * @param list       The list of objects to be sorted
     * @param comparator The comparator to be used when sorting
     */
    void sort(List<T> list, Comparator<T> comparator);

    /**
     * Sets the sorting completion status of sorting algorithm
     *
     * @param completionStatus the completion status to set
     */
    void setCompletionStatus(boolean completionStatus);

    /**
     * Gets whether the sorting algorithm has finished sorting
     *
     * @return boolean indicating whether the sorting algorithm has finished sorting
     */
    boolean sortComplete();
}

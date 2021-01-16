package algorithms.sorting;

import java.util.Comparator;
import java.util.List;

/**
 * Quick Sort algorithm implementation
 *
 * @param <T> The type of objects this class can work with and sort
 * @author Raymond T. Farrell
 * @see algorithms.factories.SortingAlgorithmFactory
 */
public class QuickSort<T extends Comparable<T>> extends BaseSortingAlgorithm<T> {
    /**
     * Implementation and overriding of parent's sort method; specifically QuickSort's
     * sorting algorithm
     *
     * @param list       The list of objects to sort
     * @param comparator The comparator to use when sorting and comparing objects
     */
    @Override
    public void sort(List<T> list, Comparator<T> comparator) {
        quickSort(list, comparator, 0, list.size() - 1);
    }

    /**
     * Main Quick Sort Method
     * <p>
     * This method recursively sorts the array by partitioning indexes
     * that are not in proper order.
     *
     * @param list is the input array.
     * @param low  is a low array element value.
     * @param high is a higher element value
     */
    private void quickSort(List<T> list, Comparator<T> comparator, int low, int high) {
        if (low < high) {
            /* i is partitioning index, list[i] is
              now at right place
             */
            int i = partition(list, comparator, low, high);

            // Recursively sort elements before
            // partition and after partition
            quickSort(list, comparator, low, i - 1);
            quickSort(list, comparator, i + 1, high);
        }

        this.sortComplete = true;
    }

    /**
     * Helper method for Quick Sort
     * <p>
     * The partition method only moves array values
     * greater than the given value for 'low.'
     * This method returns the partition position to the
     * quickSort method.
     *
     * @param list is the input array.
     * @param low  is the low value for partitioning.
     * @param high is the high value for partitioning.
     * @return
     */
    private int partition(List<T> list, Comparator<T> comparator, int low, int high) {
        T pivot = list.get(high);

        int i = (low - 1);

        for (int j = low; j < high; j++) {
            // If current element is smaller than the pivot
            T obj1 = list.get(j);

            int compareResult = comparator == null
                    ? obj1.compareTo(pivot)
                    : comparator.compare(obj1, pivot);

            if (compareResult < 0) {
                i++;

                // swap list[i] and list[j]
                T temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }

        // swap list[i+1] and list[high] (or pivot)
        T temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);

        return i + 1;
    }
}

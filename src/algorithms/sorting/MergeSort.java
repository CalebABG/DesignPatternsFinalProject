package algorithms.sorting;

import java.util.Comparator;
import java.util.List;

/**
 * Merge Sort algorithm implementation
 *
 * @param <T> The type of objects this class can work with and sort
 * @author Raymond T. Farrell
 * @see algorithms.factories.SortingAlgorithmFactory
 */
public class MergeSort<T extends Comparable<T>> extends BaseSortingAlgorithm<T> {
    /**
     * Implementation and overriding of parent's sort method; specifically MergeSort's
     * sorting algorithm
     *
     * @param list       The list of objects to sort
     * @param comparator The comparator to use when sorting and comparing objects
     */
    @Override
    public void sort(List<T> list, Comparator<T> comparator) {
        mergeSort(list, comparator, 0, list.size() - 1);
    }

    /**
     * Main Merge Sort method
     * This method recursively sorts
     * the input array with its given parameter 'start ends,' then
     * merges the sorted halves.
     *
     * @param list is the input array.
     * @param l    is the left end of the array.
     * @param r    is the right end of the array.
     */
    private void mergeSort(List<T> list, Comparator<T> comparator, int l, int r) {
        if (l < r) {
            // Find the median
            int m = (l + r) / 2;

            // Sort first and second halves
            mergeSort(list, comparator, l, m);
            mergeSort(list, comparator, m + 1, r);

            // Merge the sorted halves
            merge(list, comparator, l, m, r);
        }

        this.sortComplete = true;
    }

    /**
     * Helper method for Merge Sort
     * This merge method only combines two arrays into 1.
     *
     * @param list is the input array for this method only.
     * @param l    is the left side of the array.
     * @param m    is the median value in the array.
     * @param r    is the right end of the array.
     */
    private void merge(List<T> list, Comparator<T> comparator, int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        Object L[] = new Object[n1];
        Object R[] = new Object[n2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = list.get(l + i);
        for (int j = 0; j < n2; ++j)
            R[j] = list.get(m + 1 + j);

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2) {
            T obj1 = (T) L[i];
            T obj2 = (T) R[j];

            int compareResult = comparator == null
                    ? obj1.compareTo(obj2)
                    : comparator.compare(obj1, obj2);

            if (compareResult <= 0) {
                list.set(k, (T) L[i]);
                i++;
            } else {
                list.set(k, (T) R[j]);
                j++;
            }
            k++;
        }

        while (i < n1) {
            list.set(k, (T) L[i]);
            i++;
            k++;
        }

        while (j < n2) {
            list.set(k, (T) R[j]);
            j++;
            k++;
        }
    }
}

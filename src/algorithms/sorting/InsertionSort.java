package algorithms.sorting;

import java.util.Comparator;
import java.util.List;

/**
 * Insertion Sort algorithm implementation
 *
 * @param <T> The type of objects this class can work with and sort
 * @author Raymond T. Farrell
 * @see algorithms.factories.SortingAlgorithmFactory
 */
public class InsertionSort<T extends Comparable<T>> extends BaseSortingAlgorithm<T> {
    /**
     * Implementation and overriding of parent's sort method; specifically InsertionSort's
     * sorting algorithm
     *
     * @param list       The list of objects to sort
     * @param comparator The comparator to use when sorting and comparing objects
     */
    @Override
    public void sort(List<T> list, Comparator<T> comparator) {
        int n = list.size();

        for (int i = 1; i < n; ++i) {
            T key = list.get(i);

            int j = i - 1;

            /* Move up all elements larger than
             * key. */
            while (j >= 0 && (comparator == null ? list.get(j).compareTo(key) : comparator.compare(list.get(j), key)) > 0) {
                list.set(j + 1, list.get(j));
                j = j - 1;
            }

            list.set(j + 1, key);
        }

        this.sortComplete = true;
    }
}

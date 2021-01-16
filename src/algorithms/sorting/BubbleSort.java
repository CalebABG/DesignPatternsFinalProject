package algorithms.sorting;

import java.util.Comparator;
import java.util.List;

/**
 * Bubble Sort algorithm implementation
 *
 * @param <T> The type of objects this class can work with and sort
 * @author Raymond T. Farrell
 * @see algorithms.factories.SortingAlgorithmFactory
 */
public class BubbleSort<T extends Comparable<T>> extends BaseSortingAlgorithm<T> {
    /**
     * Implementation and overriding of parent's sort method; specifically BubbleSort's
     * sorting algorithm
     *
     * @param list       The list of objects to sort
     * @param comparator The comparator to use when sorting and comparing objects
     */
    @Override
    public void sort(List<T> list, Comparator<T> comparator) {
        int n = list.size();
        int i, j;

        boolean swapped;

        for (i = 0; i < n - 1; i++) {
            swapped = false;

            for (j = 0; j < n - i - 1; j++) {
                T obj1 = list.get(j);
                T obj2 = list.get(j + 1);

                int compareResult = comparator == null
                        ? obj1.compareTo(obj2)
                        : comparator.compare(obj1, obj2);

                // Means arr[j] > arr[j+1]
                if (compareResult > 0) {
                    // swap arr[j] and arr[j+1]
                    T temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);

                    swapped = true;
                }
            }

            // IF no two elements were
            // swapped by inner loop, then break
            if (!swapped)
                break;
        }

        this.sortComplete = true;
    }
}

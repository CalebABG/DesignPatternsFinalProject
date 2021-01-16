package algorithms.searching;

/**
 * LinearSearch class looks up an element in a list, and returns the index where
 * the element is located, -1 otherwise.
 *
 * @author Camilo Espinosa (https://github.com/Camiloesp)
 */

import java.util.List;

/**
 * Linear Search algorithm.
 *
 * @param <T> Object type for linear search
 */
public class BinarySearch<T extends Comparable<T>> implements SearchingAlgorithm<T> {
    /**
     * This method will search an element in a sorted list.
     *
     * @param list  List contains objects
     * @param value Target is the element that needs to be searched for in the list.
     * @return true if found in the collection and false if not
     */
    @Override
    public boolean search(List<T> list, T value) {
        boolean found = false;
        int left = 0, right = list.size() - 1;

        while (left <= right) {
            int middle = left + (right - 1) / 2;
            int comp = list.get(middle).compareTo(value);

            //if int comp == -1 then list[middle] < value
            //if int comp ==  0 then list[middle] == value
            //if int comp ==  1 then list[middle] > value
            if (comp == 0) {
                System.out.println("\nValue found at index " + middle + "\n");
                found = true;
                break;
            } else if (comp > 0)
                right = middle - 1;
            else
                left = middle + 1;
        }

        if (!found)
            System.out.println("Value not found.");

        return found;
    }
}

package algorithms.searching;


import java.util.List;

/**
 * LinearSearch class looks up an element in a list, and returns the index where
 * the element is located, -1 otherwise.
 *
 * @param <T> Object type for linear search
 * @author Camilo Espinosa (https://github.com/Camiloesp)
 */

public class LinearSearch<T extends Comparable<T>> implements SearchingAlgorithm<T> {

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

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).compareTo(value) == 0) {
                System.out.println("\nValue " + value + " found at index " + i);
                found = true;
                break;
            }
        }

        if (!found)
            System.out.println("\nValue " + value + " not found.");

        return found;
    }
}

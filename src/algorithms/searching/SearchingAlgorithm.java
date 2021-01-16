package algorithms.searching;


import java.util.List;

/**
 * This interface to create different searching algorithms.
 *
 * @param <T> The type of objects SearchingAlgorithm' subclasses will search on.
 * @author Camilo Espinosa (https://github.com/Camiloesp)
 */

public interface SearchingAlgorithm<T extends Comparable<T>> {
    /**
     * This method will search an element in a sorted list.
     *
     * @param list  List contains objects
     * @param value Target is the element that needs to be searched for in the list.
     * @return a boolean whether the value was found in the collection
     */
    boolean search(List<T> list, T value);
}

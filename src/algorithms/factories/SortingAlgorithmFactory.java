package algorithms.factories;

import algorithms.sorting.SortingAlgorithm;

/**
 * Abstract Factory for creating Sorting Algorithms
 *
 * @author Caleb Bostic-Gardner
 * @see SortingAlgorithm
 */
public interface SortingAlgorithmFactory<T extends Comparable<T>> {
    /**
     * Method for creating a sorting algorithm given the type requested
     *
     * @param algorithmType the type of sorting algorithm to create
     * @return the proper sorting algorithm
     */
    SortingAlgorithm<T> makeSortingAlgorithm(SortingAlgorithmType algorithmType);
}


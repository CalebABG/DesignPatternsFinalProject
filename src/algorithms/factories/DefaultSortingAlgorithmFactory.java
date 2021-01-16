package algorithms.factories;

import algorithms.sorting.*;

/**
 * Baseline Abstract Factory implementation class for creating SortingAlgorithms
 *
 * @author Caleb Bostic-Gardner
 * @see algorithms.factories.SortingAlgorithmFactory
 */
public class DefaultSortingAlgorithmFactory<T extends Comparable<T>> implements SortingAlgorithmFactory<T> {
    /**
     * Overriding method for constructing and returning a SortingAlgorithm given
     * parameters
     *
     * @param algorithmType The type of Sorting Algorithm to create
     * @return The appropriate sorting algorithm based of the provided parameters
     */
    @Override
    public SortingAlgorithm<T> makeSortingAlgorithm(SortingAlgorithmType algorithmType) {
        switch (algorithmType) {
            case BUBBLE_SORT:
                return new BubbleSort<>();
            case INSERTION_SORT:
                return new InsertionSort<>();
            case MERGE_SORT:
                return new MergeSort<>();
            case QUICK_SORT:
                return new QuickSort<>();
            default:
                return new DefaultSortingAlgorithm<>();
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " {" + '}';
    }
}

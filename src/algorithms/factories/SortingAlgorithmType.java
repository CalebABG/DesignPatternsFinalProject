package algorithms.factories;

/**
 * Enumeration of Sorting Algorithm types
 *
 * @author Caleb Bostic-Gardner
 */
public enum SortingAlgorithmType {

    BUBBLE_SORT("BubbleSort"),
    INSERTION_SORT("InsertionSort"),
    MERGE_SORT("MergeSort"),
    QUICK_SORT("QuickSort");

    /**
     * Name of Sorting Algorithm
     */
    private final String algorithmName;

    /**
     * Constructor for setting the name of the algorithm to its
     * enum value
     *
     * @param algorithmName the name of the sorting algorithm
     */
    SortingAlgorithmType(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    @Override
    public String toString() {
        return algorithmName;
    }
}


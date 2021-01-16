package algorithms.sorting;

import java.util.Objects;

/**
 * Base class defining a skeleton for a SortingAlgorithm
 *
 * @param <T> The type of objects this sorting algorithm will sort
 * @author Caleb Bostic-Gardner
 * @see SortingAlgorithm
 */
public abstract class BaseSortingAlgorithm<T extends Comparable<T>> implements SortingAlgorithm<T> {
    protected String id;
    protected boolean sortComplete;

    /**
     * Default constructor
     */
    public BaseSortingAlgorithm() {
        id = java.util.UUID.randomUUID().toString();
    }

    /**
     * Gets whether the SortingAlgorithm has finished sorting
     *
     * @return boolean indicating if sort is complete or not
     */
    @Override
    public boolean sortComplete() {
        return sortComplete;
    }

    /**
     * Sets the completion status for the sorting algorithm
     *
     * @param completionStatus the status to set
     */
    @Override
    public void setCompletionStatus(boolean completionStatus) {
        this.sortComplete = completionStatus;
    }

    /**
     * Method for cloning a sorting algorithm
     *
     * @return a clone of the sorting algorithm
     * @throws CloneNotSupportedException throws if the base class doesn't implement the Cloneable interface
     */
    @Override
    public BaseSortingAlgorithm<T> deepClone() throws CloneNotSupportedException {
        BaseSortingAlgorithm<T> clone = (BaseSortingAlgorithm<T>) super.clone();
        // Set clone reference members to class's reference clones
        return clone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseSortingAlgorithm<?> that = (BaseSortingAlgorithm<?>) o;
        return sortComplete == that.sortComplete &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sortComplete);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " {" +
                "id='" + id + '\'' +
                ", sortComplete=" + sortComplete +
                "}";
    }
}

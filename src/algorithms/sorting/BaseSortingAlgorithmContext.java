package algorithms.sorting;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Abstract base class for defining the skeleton for a sorting algorithm context.
 *
 * @param <T> the type of items the context will sort
 * @author Caleb Bostic-Gardner
 * @see SortingContext
 */
public abstract class BaseSortingAlgorithmContext<T extends Comparable<T>> implements SortingContext<T> {
    protected List<T> items;
    protected Comparator<T> comparator;
    protected SortingAlgorithm<T> sortingStrategy;

    /**
     * Default empty constructor
     */
    public BaseSortingAlgorithmContext() {
    }

    /**
     * Constructor for setting the contexts items
     *
     * @param items the items the context will sort
     */
    public BaseSortingAlgorithmContext(List<T> items) {
        this(items, null, null);
    }

    /**
     * Constructor for creating a sorting context from a memento
     *
     * @param memento the memento to restore from
     */
    public BaseSortingAlgorithmContext(SortingAlgorithmContextMemento<T> memento) {
        this(memento.getList(), memento.getSortingAlgorithm(), memento.getComparator());
    }

    /**
     * Constructor for creating a sorting context with a list of items and a sorting algorithm
     *
     * @param items           the items the context will sort
     * @param sortingStrategy the sorting algorithm (strategy) the context will use to sort
     */
    public BaseSortingAlgorithmContext(List<T> items, SortingAlgorithm<T> sortingStrategy) {
        this(items, sortingStrategy, null);
    }

    /**
     * Constructor for creating a sorting context with a list of items, a sorting algorithm, and a comparator
     *
     * @param items           the items the context will sort
     * @param sortingStrategy the sorting algorithm (strategy) the context will use to sort
     * @param comparator      the comparator the context will use when sorting
     */
    public BaseSortingAlgorithmContext(List<T> items, SortingAlgorithm<T> sortingStrategy, Comparator<T> comparator) {
        this.items = items;
        this.sortingStrategy = sortingStrategy;
        this.comparator = comparator;
    }

    /**
     * Template method for sub-classes to implement. Can be used to perform any needed
     * setup or initialization. This method is called in the base constructor after instance
     * variables are set from parameters
     *
     * @param list the list to use for initialization
     */
    // Template Method 1
    protected abstract void initialize(List<T> list);

    /**
     * Implementation method for SortingStrategy's sort method.
     * Calls template method 'initialize' method before sorting
     *
     * @see SortingContext
     */
    public void sort() {
        initialize(this.items);
        if (!sortingStrategy.sortComplete())
            sortingStrategy.sort(items, comparator);
    }

    /**
     * Sets the context's items
     *
     * @param list the list for the context to use
     */
    @Override
    public void setItems(List<T> list) {
        this.items = list;
    }

    /**
     * Gets the context's items
     *
     * @return the items from the context
     */
    @Override
    public List<T> getItems() {
        return items;
    }

    /**
     * Method to return a copy of the context's items
     *
     * @return a deep copy of the context's items
     */
    private List<T> copyItems() {
        return this.items.stream().collect(Collectors.toList());
    }

    /**
     * Get the context's comparator
     *
     * @return the comparator for the context
     */
    @Override
    public Comparator<T> getComparator() {
        return comparator;
    }

    /**
     * Sets the context's comparator
     *
     * @param comparator the comparator the context should use
     */
    @Override
    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    /**
     * Sets the context's sorting algorithm
     *
     * @param sortingAlgorithm the sorting algorithm the context should use
     */
    @Override
    public void setSortingAlgorithm(SortingAlgorithm<T> sortingAlgorithm) {
        sortingStrategy = sortingAlgorithm;
    }

    /**
     * Gets the context's sorting algorithm
     *
     * @return the algorithm for the context
     */
    @Override
    public SortingAlgorithm<T> getSortingAlgorithm() {
        return sortingStrategy;
    }

    /**
     * This method returns the context's items Iterator
     */
    public Iterator<T> getIterator() {
        if (items == null) return null;
        return this.new SortingAlgorithmIterator();
    }

    /**
     * Creates a clone of the sorting algorithm context
     *
     * @return a deep clone of the context
     * @throws CloneNotSupportedException throws if the base class doesn't implement the Cloneable interface
     */
    @Override
    public BaseSortingAlgorithmContext<T> deepClone() throws CloneNotSupportedException {
        BaseSortingAlgorithmContext<T> clone = (BaseSortingAlgorithmContext<T>) super.clone();
        // Set clone reference members to class's reference clones
        clone.items = copyItems();
        clone.sortingStrategy = sortingStrategy.deepClone();
        clone.comparator = comparator;
        return clone;
    }

    /**
     * Creates a memento with the state of the context
     *
     * @return the Memento which encapsulates the state of the context
     */
    public SortingAlgorithmContextMemento<T> createMemento() {
        SortingAlgorithmContextMemento<T> memento = new SortingAlgorithmContextMemento<>();
        // NOT efficient
        List<T> listCopy = copyItems();
        memento.setList(listCopy);
        try {
            memento.setSortingAlgorithm(sortingStrategy.deepClone());
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
        }
        memento.setSortCompleted(sortingStrategy.sortComplete());
        memento.setComparator(comparator);
        return memento;
    }

    /**
     * Restores the context from a Memento
     *
     * @param memento the memento from which to restore the state
     */
    public void restoreFromMemento(SortingAlgorithmContextMemento<T> memento) {
        setItems(memento.getList());
        setSortingAlgorithm(memento.getSortingAlgorithm());
        setComparator(memento.getComparator());
        if (sortingStrategy != null) {
            sortingStrategy.setCompletionStatus(memento.getSortCompleted());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseSortingAlgorithmContext<?> that = (BaseSortingAlgorithmContext<?>) o;
        return Objects.equals(items, that.items) &&
                Objects.equals(comparator, that.comparator) &&
                Objects.equals(sortingStrategy, that.sortingStrategy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items, comparator, sortingStrategy);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " {" +
                "comparator=" + comparator +
                ", sortingStrategy=" + sortingStrategy +
                "}";
    }

    /**
     * Custom Iterator to replace a call to items.getIterator()
     *
     * @author Caleb Bostic-Gardner
     * @see java.util.Iterator
     */
    private class SortingAlgorithmIterator implements Iterator<T> {
        private final Iterator<T> sortingAlgoIterator = items.iterator();

        /**
         * Gets whether the iterator has a next item
         *
         * @return true if the iterator has a next item or false if the next item is null
         */
        public boolean hasNext() {
            if (sortingAlgoIterator == null) return false;
            return sortingAlgoIterator.hasNext();
        }

        /**
         * Gets the next item in the iterator
         *
         * @return item of specified generic type, or null if the next item in the iterator is null
         */
        public T next() {
            if (sortingAlgoIterator == null || !sortingAlgoIterator.hasNext())
                return null;

            return sortingAlgoIterator.next();
        }

        /**
         * Removes an item from the iterator.
         */
        public void remove() {
            throw new RuntimeException("Operation is invalid, Iterator is does not mutate.");
        }
    }

    /**
     * A Memento class to store states of an SortingAlgorithm
     *
     * @param <T> the type of objects the collection will store
     * @author Caleb Bostic-Gardner
     */
    public static class SortingAlgorithmContextMemento<T extends Comparable<T>> {
        private boolean sortCompleted;
        private List<T> list;
        private SortingAlgorithm<T> sortingAlgorithm;
        private Comparator<T> comparator;

        /**
         * Default constructor
         */
        private SortingAlgorithmContextMemento() {
        }

        /**
         * Sets the memento's items
         *
         * @param list the list the memento should store
         */
        private void setList(List<T> list) {
            this.list = list;
        }

        /**
         * Gets the memento's items
         *
         * @return the items from the memento
         */
        private List<T> getList() {
            return list;
        }

        /**
         * Stores the completion status from a sorting algorithm in the memento
         *
         * @param sortCompleted the completion status to store
         */
        private void setSortCompleted(boolean sortCompleted) {
            this.sortCompleted = sortCompleted;
        }

        /**
         * Gets the memento's completion status
         *
         * @return the completion status for a sorting algorithm from the memento
         */
        private boolean getSortCompleted() {
            return sortCompleted;
        }

        /**
         * Sets the memento's sorting algorithm
         *
         * @param sortingAlgorithm the sorting algorithm for the memento to store
         */
        private void setSortingAlgorithm(SortingAlgorithm<T> sortingAlgorithm) {
            this.sortingAlgorithm = sortingAlgorithm;
        }

        /**
         * Gets the memento's sorting algorithm
         *
         * @return the sorting algorithm from the memento
         */
        private SortingAlgorithm<T> getSortingAlgorithm() {
            return sortingAlgorithm;
        }

        /**
         * Get the memento's comparator
         *
         * @return the comparator from the memento
         */
        private Comparator<T> getComparator() {
            return comparator;
        }

        /**
         * Sets the memento's comparator
         *
         * @param comparator the comparator for the memento to store
         */
        private void setComparator(Comparator<T> comparator) {
            this.comparator = comparator;
        }
    }
}

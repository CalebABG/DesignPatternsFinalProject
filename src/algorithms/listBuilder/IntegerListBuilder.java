package algorithms.listBuilder;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementation class of the ListBuilder interface
 *
 * @author Camilo Espinosa (https://github.com/Camiloesp)
 * @see algorithms.listBuilder.ListBuilder
 */
public class IntegerListBuilder implements ListBuilder<Integer> {
    private static final int DEFAULT_MAKER_METHOD_ITEMS = 20;

    private int makerMethodItems;
    private List<Integer> backingList;

    /**
     * Default constructor for initializing internal collection of items
     */
    public IntegerListBuilder() {
        this(DEFAULT_MAKER_METHOD_ITEMS);
    }

    /**
     * Constructor for initializing internal collection of items
     *
     * @param numberOfItemsForMakerMethods the number of items to add to the list when making a random or sorted list
     */
    public IntegerListBuilder(int numberOfItemsForMakerMethods) {
        this.backingList = new ArrayList<>();
        this.makerMethodItems = numberOfItemsForMakerMethods;
    }

    /**
     * Constructor for setting the internal collection provided a collection
     *
     * @param list                         the items the internal collection will be set to
     * @param numberOfItemsForMakerMethods the number of items to add to the list when making a random or sorted list
     */
    private IntegerListBuilder(List<Integer> list, int numberOfItemsForMakerMethods) {
        this.backingList = list;
        this.makerMethodItems = numberOfItemsForMakerMethods;
    }

    /**
     * If the internal list is empty, then this method will build a list of random integers. Otherwise,
     * it will return the builder immediately
     *
     * @return the builder of type Integer
     */
    public ListBuilder<Integer> makeRandom() {
        if (backingList.isEmpty()) {
            Random rand = new Random();
            for (int i = 0; i < makerMethodItems; ++i) {
                int j = rand.nextInt(1000) + 1;
                backingList.add(j);
            }
        }
        return this;
    }

    /**
     * If the internal list is empty, then this method will build an ordered list. Otherwise,
     * it will return the builder immediately
     *
     * @return the builder of type Integer
     */
    public ListBuilder<Integer> makeSorted() {
        if (backingList.isEmpty()) {
            for (int i = 0; i < makerMethodItems; ++i) {
                backingList.add(i + 1);
            }
        }
        return this;
    }

    /**
     * Adds a collection of Integers to the builders list
     *
     * @param collection the items to add
     * @return the builder of type Integer
     */
    @Override
    public ListBuilder<Integer> add(Collection<Integer> collection) {
        if (collection != null) {
            backingList.addAll(collection);
        }
        return this;
    }

    /**
     * Adds an Integer to the builders list
     *
     * @param item the item to be added to the builder
     * @return the builder of type Integer
     */
    @Override
    public ListBuilder<Integer> add(Integer item) {
        backingList.add(item);
        return this;
    }

    /**
     * Shuffles the items in the builder
     *
     * @return the builder of type Integer
     */
    @Override
    public ListBuilder<Integer> shuffle() {
        Collections.shuffle(backingList);
        return this;
    }

    /**
     * This method will print the elements of an array
     *
     * @return the builder of type Integer
     */
    public ListBuilder<Integer> print() {
        System.out.print("List: ");
        for (int i = 0; i < backingList.size(); ++i) {
            System.out.print(backingList.get(i) + " ");
        }
        return this;
    }

    /**
     * Creates the complete list
     *
     * @return the list of Integers from the builder
     */
    @Override
    public List<Integer> build() {
        return backingList;
    }

    /**
     * Clones the builder of type Integer
     *
     * @return a deep-copy of the builder of type Integer
     */
    @Override
    public ListBuilder<Integer> deepClone() {
        List<Integer> newList = backingList.stream().collect(Collectors.toList());
        return new IntegerListBuilder(newList, makerMethodItems);
    }
}

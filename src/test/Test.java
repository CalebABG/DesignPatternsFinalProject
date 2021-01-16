package test;

import algorithms.factories.*;
import algorithms.listBuilder.IntegerListBuilder;
import algorithms.listBuilder.ListBuilder;
import algorithms.sorting.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.Callable;

/**
 * Class for testing project design, algorithms and design patterns
 *
 * @author Caleb Bostic-Gardner
 */
public class Test {
    /**
     * Main method
     *
     * @param args the arguments to the program
     */
    public static void main(String[] args) {
        try {
            runEnumSortTypeTest();
            runTestBench();
        } catch (CloneNotSupportedException e) {
            System.out.println("ICloneable Exception Happened: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("General Exception Happened: " + e.getMessage());
        }
    }

    /**
     * Method for running all or specified test methods
     */
    public static void runTestBench() throws Exception {
        runTest("Abstract Factory", Test::testSortingAlgorithmFactory);
        runTest("Factory Method", Test::testSortingAlgorithmContextCreator);
        runTest("Iterator", Test::testSortingAlgorithmContextIterator);
        runTest("Memento", Test::testSortingAlgorithmMemento);
        runTest("Decorator", Test::testSortingAlgorithmDecorator);
        runTest("Strategy", Test::testSortingAlgorithmContextStrategy);
        runTest("Builder", Test::testIntegerListBuilder);
        runTest("Prototype", Test::testICloneableInterfaceOnSortingAlgorithmContext);
    }

    /**
     * Method for testing all Enumerations of Sorting Algorithms
     */
    public static void runEnumSortTypeTest(){
        SortingAlgorithmType[] sortingAlgorithmTypes = SortingAlgorithmType.values();
        SortingAlgorithmContextCreator<Integer> sortingAlgorithmContextCreator = new DefaultSortingAlgorithmContextCreator<>();

        for (SortingAlgorithmType sortingAlgorithmType : sortingAlgorithmTypes) {
            SortingContext<Integer> sortingContext = sortingAlgorithmContextCreator.makeSortingContext(
                    sortingAlgorithmType,
                    new IntegerListBuilder().makeRandom().build()
            );

            testSort(sortingAlgorithmType.name(), sortingContext.getSortingAlgorithm(), sortingContext.getItems(), sortingContext.getComparator());
        }
    }

    /**
     * Helper method for running tests
     *
     * @param testName   the name of the test
     * @param testMethod the test method to run
     * @throws Exception rethrows 'Callable' class exception; throws a CloneNotSupportedException if a base or child class doesn't support cloning
     */
    public static void runTest(String testName, Callable<Void> testMethod) throws Exception {
        System.out.println("#### Test: '" + testName + "' ####\n");
        testMethod.call();
        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------\n");
    }

    /**
     * Method to test the Iterator
     *
     * @see SortingContext
     */
    public static Void testSortingAlgorithmContextIterator() {
        ListBuilder<Integer> integerListBuilder = new IntegerListBuilder().makeRandom();

        SortingContext<Integer> sortingAlgorithmContext = new DefaultSortingAlgorithmContext<>();
        sortingAlgorithmContext.setItems(integerListBuilder.build());

        System.out.println("Sorting Algorithm Context");
        System.out.println(sortingAlgorithmContext + "\n");

        System.out.println("Iterator");

        Iterator<Integer> integerIterator = sortingAlgorithmContext.getIterator();
        while (integerIterator.hasNext())
            System.out.print(integerIterator.next() + ", ");

        return null;
    }

    /**
     * Method to test the Strategy pattern
     *
     * @see SortingContext
     */
    public static Void testSortingAlgorithmContextStrategy() {
        try {
            ListBuilder<Integer> integerListBuilder1 = new IntegerListBuilder().makeRandom();
            ListBuilder<Integer> integerListBuilder2 = integerListBuilder1.deepClone();

            SortingContext<Integer> sortingAlgorithmContext = new DefaultSortingAlgorithmContext<>();
            sortingAlgorithmContext.setItems(integerListBuilder1.build());

            System.out.println("Original Sorting Strategy");
            System.out.println(sortingAlgorithmContext.getSortingAlgorithm() + "\n");

            System.out.println("Sort Completed: " + sortingAlgorithmContext.getSortingAlgorithm().sortComplete());
            sortingAlgorithmContext.sort();

            System.out.println("Attempting to Sort When Completed");
            System.out.println("Sort Completed: " + sortingAlgorithmContext.getSortingAlgorithm().sortComplete() + "\n");

            System.out.println("Swapping Sorting Strategy: Using 'Merge Sort'");
            sortingAlgorithmContext.setItems(integerListBuilder2.build());
            sortingAlgorithmContext.setSortingAlgorithm(new MergeSort<>());

            System.out.println("New Sorting Strategy");
            System.out.println(sortingAlgorithmContext.getSortingAlgorithm());
        } catch (CloneNotSupportedException e) {
            System.out.println("ICloneable Exception Happened: " + e.getMessage());
        }

        return null;
    }

    /**
     * Method to test the Decorator pattern
     *
     * @see DefaultSortingAlgorithm
     */
    public static Void testSortingAlgorithmDecorator() {
        SortingAlgorithm<Integer> sortingAlgorithm = new DefaultSortingAlgorithm<>();

        System.out.println("Sorting Algorithm");
        System.out.println(sortingAlgorithm);

        return null;
    }

    /**
     * Method to test the Prototype pattern
     *
     * @see algorithms.ICloneable
     */
    public static Void testICloneableInterfaceOnSortingAlgorithmContext() throws CloneNotSupportedException {
        ListBuilder<Integer> integerListBuilder = new IntegerListBuilder();
        integerListBuilder
                .makeRandom()
                .add(1)
                .add(0)
                .add(-1)
                .add(4);

        SortingAlgorithmContextCreator<Integer> sortingAlgorithmContextCreator = new DefaultSortingAlgorithmContextCreator<>();
        SortingContext<Integer> sortingAlgorithmContext = sortingAlgorithmContextCreator.makeSortingContext(
                SortingAlgorithmType.QUICK_SORT,
                integerListBuilder.build()
        );

        System.out.println("Original Context");
        System.out.println(sortingAlgorithmContext.toString());

        System.out.println();

        SortingContext<Integer> clonedSortingAlgorithmContext = sortingAlgorithmContext.deepClone();

        System.out.println("Cloned Context");
        System.out.println(clonedSortingAlgorithmContext.toString());

        return null;
    }

    /**
     * Method to test the SortingAlgorithmContextCreator
     *
     * @see SortingAlgorithmContextCreator
     */
    public static Void testSortingAlgorithmContextCreator() {
        ListBuilder<Integer> integerListBuilder = new IntegerListBuilder();
        integerListBuilder
                .makeRandom()
                .add(1)
                .add(0)
                .add(-1)
                .add(4);

        SortingAlgorithmContextCreator<Integer> sortingAlgorithmContextCreator = new DefaultSortingAlgorithmContextCreator<>();
        SortingContext<Integer> sortingAlgorithmContext = sortingAlgorithmContextCreator.makeSortingContext(
                SortingAlgorithmType.MERGE_SORT,
                integerListBuilder.build()
        );

        System.out.println("Constructing 'SortingAlgorithmContext' w/ Given Creator: ");
        System.out.println(sortingAlgorithmContextCreator + "\n");

        System.out.println("Sorting Algorithm Context");
        System.out.println(sortingAlgorithmContext);

        return null;
    }

    /**
     * Method to test the Memento pattern
     *
     * @see algorithms.sorting.BaseSortingAlgorithmContext.SortingAlgorithmContextMemento
     */
    public static Void testSortingAlgorithmMemento() {
        ListBuilder<Integer> integerListBuilder = new IntegerListBuilder();
        integerListBuilder
                .add(1)
                .add(0)
                .add(-1)
                .add(4);

        BaseSortingAlgorithmContext<Integer> bubbleSortContext = new DefaultSortingAlgorithmContext<>(
                integerListBuilder.build(),
                new QuickSort<>(),
                Comparator.naturalOrder()
        );

        System.out.println("Original Context");
        System.out.println(bubbleSortContext.toString());

        System.out.println();

        SortingContext<Integer> mergeSortContext = new DefaultSortingAlgorithmContext<>(bubbleSortContext.createMemento());
        mergeSortContext.setSortingAlgorithm(new MergeSort<>());
        mergeSortContext.setComparator(Comparator.reverseOrder());
        mergeSortContext.sort();

        System.out.println("Memento Context");
        System.out.println(mergeSortContext.toString());

        System.out.println();

        System.out.println("Memento Sorted: " + verifySorted(mergeSortContext.getItems(), mergeSortContext.getComparator()));
        for (Integer i : mergeSortContext.getItems()) {
            System.out.print(i + ", ");
        }

        return null;
    }

    /**
     * Method to test the Builder pattern
     *
     * @see IntegerListBuilder
     */
    public static Void testIntegerListBuilder() {
        ListBuilder<Integer> integerListBuilder = new IntegerListBuilder();

        integerListBuilder
                .makeRandom()
                .add(1)
                .add(200)
                .add(-100)
                .add(-5500)
                .add(new IntegerListBuilder().add(100).add(20).build())
                .shuffle();

        List<Integer> integerList = integerListBuilder.build();

        System.out.println("ListBuilder Items: ");
        for (Integer i : integerList) System.out.print(i + ", ");

        return null;
    }

    /**
     * Method to test the Abstract Factory pattern
     *
     * @see SortingAlgorithmFactory
     */
    public static Void testSortingAlgorithmFactory() {
        SortingAlgorithmFactory<Integer> sortingAlgorithmFactory = new DefaultSortingAlgorithmFactory<>();
        SortingAlgorithm<Integer> sortingAlgorithm = sortingAlgorithmFactory.makeSortingAlgorithm(SortingAlgorithmType.INSERTION_SORT);

        System.out.println("Factory");
        System.out.println(sortingAlgorithmFactory);

        System.out.println();

        System.out.println("Algorithm");
        System.out.println(sortingAlgorithm);

        return null;
    }

    /**
     * Method which tests a Sorting Algorithm given an input file full of Integers
     *
     * @param algorithmType The type of sorting algorithm to test
     */
    private static Void testIntegerSortFromFile(SortingAlgorithmType algorithmType) {
        /* Split by testing env + sort data */
        String[] cases = new String[]{"best", "average", "worst"};
        // Corresponds to index for above array
        int mode = 1;

        List<Integer> list = getIntsFromFile("src/test/sorting_data/sortdata" + cases[mode] + ".txt");
        testBaselineFactorySortingAlgorithm(algorithmType, list, Comparator.naturalOrder());

        return null;
    }

    /**
     * Helper method to provide scaffolding for tests. This helper method creates the Abstract Sorting algorithm Factory,
     * creates a sorting algorithm given the type, and then executes the sort on the sorting algorithm
     *
     * @param algorithmType The type of sorting algorithm to use
     * @param list          The list of objects to sort
     * @param comparator    The comparator to use when sorting
     * @param <T>           The type of objects to sort
     */
    public static <T extends Comparable<T>> void testBaselineFactorySortingAlgorithm(SortingAlgorithmType algorithmType, List<T> list, Comparator<T> comparator) {
        SortingAlgorithmFactory<T> sortingAlgorithmFactory = new DefaultSortingAlgorithmFactory<>();
        SortingAlgorithm<T> sortingAlgorithm = sortingAlgorithmFactory.makeSortingAlgorithm(algorithmType);
        testSort(algorithmType.name(), sortingAlgorithm, list, comparator);
    }

    /**
     * Helper method to provide information about a given test. This helper method provides info before and after using
     * the sorting algorithm to sort, as well as performing a verification to whether in fact the collection was sorted
     *
     * @param sortFuncName     The name of the sorting algorithm
     * @param sortingAlgorithm The sorting algorithm to use
     * @param list             The list of objects to sort
     * @param comparator       The comparator to use when sorting
     * @param <T>              The type of objects to sort
     */
    public static <T extends Comparable<T>> void testSort(String sortFuncName, SortingAlgorithm<T> sortingAlgorithm, List<T> list, Comparator<T> comparator) {
        String header = "Sorting Algorithm: " + sortFuncName;
        String headerWrap = repeat("--", list.size() * 3);

        System.out.println();
        System.out.println(headerWrap);
        System.out.println(header + "\n");
        System.out.println("Unsorted Data:");
        for (T item : list) {
            System.out.print(item + ", ");
        }
        System.out.println();

        sortingAlgorithm.sort(list, comparator);

        System.out.println("\nSorted Data (Length: " + list.size() + "): " + verifySorted(list, comparator));

        for (T item : list) {
            System.out.print(item + ", ");
        }
        System.out.println();

        System.out.println(headerWrap + "\n\n");
    }

    /**
     * Helper method for repeating a String sequence
     * Source - StackOverflow: https://stackoverflow.com/questions/1235179/simple-way-to-repeat-a-string
     *
     * @param s the string to repeat
     * @param n how many times to repeat the given string
     * @return the string repeated <code>n</code> times
     */
    private static String repeat(String s, int n) {
        if (s == null) return "";
        final StringBuilder sb = new StringBuilder(s.length() * n);
        for (int i = 0; i < n; ++i) sb.append(s);
        return sb.toString();
    }

    /**
     * Helper method to verify if the collection of objects are actually sorted (in correct order)
     *
     * @param objects    The collection of objects which were to be sorted
     * @param comparator The comparator which is used to check if the objects are in the right order
     * @param <T>        The type of objects in the collection
     * @return boolean indicating whether the objects are sorted (in the correct order) according to the Comparator
     */
    private static <T> boolean verifySorted(List<T> objects, Comparator<T> comparator) {
        for (int i = 1; i < objects.size() - 1; i++) {
            T left = objects.get(i - 1);
            T right = objects.get(i);

            if (comparator.compare(left, right) > 0) return false;
        }

        return true;
    }

    /**
     * Helper method to get Integers from input file
     *
     * @param filepath Path to the file containing integers
     * @return A list of integers from the input file
     */
    private static List<Integer> getIntsFromFile(String filepath) {
        List<Integer> ints = null;
        File file = new File(filepath);
        Scanner sc;

        try {
            List<Integer> temp = new ArrayList<>(200);
            sc = new Scanner(file);

            while (sc.hasNextLine() && sc.hasNextInt()) {
                int data = sc.nextInt();
                temp.add(data);
            }

            ints = temp;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return ints;
    }

}

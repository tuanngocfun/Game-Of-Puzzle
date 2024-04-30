package com.edu.homeedu.puzzle.kenken.utils.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.edu.homeedu.puzzle.kenken.utils.Validators;

/**
 * Provides utility methods for common operations on collections such as
 * checking for duplicates, emptiness, copying, and merging. This class
 * is designed to facilitate manipulation and examination of collections
 * and maps in a concise way.
 * <p>
 * All methods in this class are static, meaning they can be called directly
 * on the class without needing an instance.
 */
public class CollectionHelpers {
    private CollectionHelpers() {}

    /**
     * Checks if the provided collection has duplicate elements.
     *
     * @param collection the collection to check for duplicates
     * @return true if there are duplicates, false otherwise
     */
    public static boolean containsDuplicates(Collection<?> collection) {
        return collection.size() != new HashSet<>(collection).size();
    }

    /**
     * Checks if the provided collection is null or empty.
     *
     * @param collection the collection to check
     * @return true if the collection is null or empty, false otherwise
     */
    public static boolean isNullOrEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * Checks if the provided map is null or empty.
     *
     * @param map the map to check
     * @return true if the map is null or empty, false otherwise
     */
    public static boolean isNullOrEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * Checks if the provided collection has exactly one item.
     *
     * @param collection the collection to check
     * @return true if the collection has exactly one item, false otherwise
     */
    public static boolean hasExactlyOneItem(Collection<?> collection) {
        return collection != null && collection.size() == 1;
    }

    /**
     * Checks if the provided collection has more than one item.
     *
     * @param collection the collection to check
     * @return true if the collection has more than one item, false otherwise
     */
    public static <T> List<T> asList(Collection<T> collection) {
        return new ArrayList<>(collection);
    }

    /**
     * Creates a shallow copy of the specified set.
     *
     * @param <T> the type of elements in the set
     * @param src the set to copy
     * @return a new HashSet containing all elements of the source set
     */
    public static <T> Set<T> copy(Set<T> src) {
        return new HashSet<>(src);
    }

    /**
     * Creates a shallow copy of the specified list.
     *
     * @param <T> the type of elements in the list
     * @param src the list to copy
     * @return a new ArrayList containing all elements of the source list
     */
    public static <T> List<T> copy(List<T> src) {
        return new ArrayList<>(src);
    }

    /**
     * Creates a shallow copy of the specified map.
     *
     * @param <K> the type of keys in the map
     * @param <V> the type of values in the map
     * @param <S> the type of the set of values in the map
     * @param src the map to copy
     * @return a new HashMap containing all entries of the source map
     */
    public static <K, V, S extends Set<V>> Map<K, S> copy(Map<K, S> src) {
        return new HashMap<>(src);
    }

    /**
     * Optionally creates a deep copy of the specified map if 'copiedSets' is true.
     * Otherwise, performs a shallow copy.
     *
     * @param <K> the type of the keys in the map
     * @param <V> the type of the values in the map
     * @param <S> the specific subtype of Set that contains the values
     * @param src the map to copy
     * @param copiedSets flag indicating whether to perform a deep copy of the sets
     * @return a new map containing the copied contents
     */
    @SuppressWarnings("unchecked")
    public static <K, V, S extends Set<V>> Map<K, S> copy(Map<K, S> src, boolean copiedSets) {
        if (!copiedSets) {
            return copy(src);
        }
        else {
            return (Map<K, S>) src
                    .entrySet()
                    .stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, entry -> copy(entry.getValue())));
        }
    }

    /**
     * Merges the contents of 'other' map into 'into' map. This operation modifies the 'into' map.
     *
     * @param <K> the type of the keys in both maps
     * @param <V> the type of the values in both maps
     * @param into the map into which the other map's entries will be merged
     * @param other the map whose entries are to be merged into the 'into' map
     * @return the 'into' map containing all entries from both maps
     */
    public static <K, V> Map<K, V> concatMap(Map<K, V> into, Map<K, V> other) {
        into.putAll(other);
        return into;
    }

    /**
     * Filters a collection based on a predicate and returns a list containing the elements that match.
     *
     * @param <T> the type of elements in the collection
     * @param collection the collection to filter
     * @param predicate the condition to test against the elements
     * @return a list of elements that satisfy the predicate
     */
    public static <T> List<T> filter(Collection<T> collection, Predicate<T> predicate) {
        return collection.stream().filter(predicate).collect(Collectors.toList());
    }

    /**
     * Computes the Cartesian product of a list of lists.
     * The Cartesian product is a set that contains all possible combinations
     * of elements from the input lists.
     *
     * @param <T> the type of elements in the lists
     * @param lists a list of lists for which the Cartesian product is calculated
     * @return a Set containing Lists, each representing a possible combination of elements
     * @usage
     * Example:
     * <pre>
     * List<List<Integer>> lists = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4));
     * Set<List<Integer>> product = CollectionHelpers.cartesianProduct(lists);
     * product.forEach(System.out::println); // Prints [1, 3], [1, 4], [2, 3], [2, 4]
     * </pre>
     */
    public static <T> Set<List<T>> cartesianProduct(List<List<T>> lists) {
        if (lists == null || lists.isEmpty()) {
            return Collections.emptySet();
        }

        return cartesianProductImpl(lists.size() - 1, lists);
    }

    /**
     * Retrieves the first element of the specified collection or throws an exception with the provided message if the collection is empty.
     *
     * @param <T> the type of elements in the collection
     * @param collection the collection to retrieve the first element from
     * @param msg the exception message to use if the collection is empty
     * @return the first element of the collection
     * @throws IllegalArgumentException if the collection is empty
     */
    public static <T> T firstOrThrow(Collection<T> collection, String msg) {
        return collection.stream().findFirst().orElseThrow(() -> new IllegalArgumentException(msg));
    }

    public static <T> T firstOrThrow(Collection<T> collection) {
        return firstOrThrow(collection, "Empty collection");
    }
    /**
     * Retrieves the first element of the specified collection or returns the default value if the collection is empty.
     *
     * @param <T> the type of elements in the collection
     * @param collection the collection to retrieve the first element from
     * @param defaultValue the default value to return if the collection is empty
     * @return the first element of the collection, or the default value if the collection is empty
     */
    public static <T> T firstOrDefault(Collection<T> collection, T defaultValue) {
        return collection.stream().findFirst().orElse(defaultValue);
    }

    /**
     * Retrieves the first element of the specified collection or returns null if the collection is empty.
     *
     * @param <T> the type of elements in the collection
     * @param collection the collection to retrieve the first element from
     * @return the first element of the collection, or null if the collection is empty
     */
    public static <T> T firstOrDefault(Collection<T> collection) {
        return firstOrDefault(collection, null);
    }

    /**
     * Retrieves the last element of the specified collection or throws an exception with the provided message if the collection is empty.
     *
     * @param <T> the type of elements in the collection
     * @param collection the collection to retrieve the last element from
     * @param msg the exception message to use if the collection is empty
     * @return the last element of the collection
     * @throws IllegalArgumentException if the collection is empty
     */
    public static <T> T singleOrThrow(Collection<T> collection, String msg) {
        if (collection.size() != 1) {
            throw new IllegalArgumentException(msg);
        }
        return firstOrThrow(collection);
    }

    public static <T> T singleOrThrow(Collection<T> collection) {
        return singleOrThrow(collection, "Non-single collection");
    }

    /**
     * Retrieves a random item from a list.
     *
     * @param <T> the type of elements in the list
     * @param items the list from which to retrieve a random item
     * @return a randomly selected item from the list
     */
    public static <T> T getRandomItem(List<T> items) {
        return items.get(ThreadLocalRandom.current().nextInt(items.size()));
    }

    /**
     * Generates a stream of all permutations of a list.
     * Each permutation is itself a list containing the elements of the original list in a different order.
     *
     * @param <T> the type of elements in the list
     * @param original the list to permute
     * @return a Stream of Lists, each representing a permutation of the original list
     */
    public static <T> Stream<List<T>> permutationsStream(List<T> original) {
        return possiblePermutationsAsMaps(original)
                .stream()
                .map(Map::values)
                .map(CollectionHelpers::asList);
    }

    /**
     * Selects either the first item or reduces the items using a binary operator if more than one item exists.
     * This method is useful for combining elements of a list based on a custom operation defined by the accumulator.
     *
     * @param <T> the type of elements in the list
     * @param items the list of items to process
     * @param accumulator the binary operator to apply if the list contains more than one item
     * @return the first item or the result of the reduction
     * @throws IllegalArgumentException if the list is empty
     */
    public static <T> T firstOrReduce(List<T> items, BinaryOperator<T> accumulator) {
        Validators.ensureContainsAtLeastOne(items);
        return items.stream().skip(1).reduce(items.get(0), accumulator);
    }

    /**
     * Recursively calculates the Cartesian product of a list of lists. This method
     * iterates over the elements of the list at the given index and combines them
     * with the results from the recursive call to the next lower index, effectively
     * building all possible combinations of elements from the provided lists.
     *
     * @param <T> the type of the elements in the lists
     * @param index the current index in the list of lists being processed
     * @param lists the list of lists from which to compute the Cartesian product
     * @return a set of lists, each representing a possible combination from the Cartesian product
     */
    private static <T> Set<List<T>> cartesianProductImpl(int index, List<List<T>> lists) {
        Set<List<T>> ret = new HashSet<>();
        if (index < 0) {
            ret.add(new ArrayList<>());
        } 
        else {
            for (T obj : lists.get(index)) {
                for (List<T> set : cartesianProductImpl(index - 1, lists)) {
                    set.add(obj);
                    ret.add(set);
                }
            }
        }
        return ret;
    }

    /**
     * Generates all possible permutations of the positions in a given list using a mapping approach.
     * Each permutation is represented as a list of maps, where each map links an index position
     * in the list to an element at that position, thereby creating a unique combination of index-element pairings.
     * <p>
     * This method utilizes the Java Stream API to generate permutations, making extensive use of mapping
     * operations to produce a list of map entries representing each permutation. The approach involves
     * creating a stream of singleton maps for each element indexed by its position, then reducing these streams
     * to a single list by recursively combining and filtering to ensure that each permutation contains unique
     * index keys. The result is a list where each map represents a different permutation of the input list elements.
     * <p>
     * In short, Generates all distinct permutations of element positions in a list, each represented as a list of maps
     * linking indices to elements.
     *
     * @param <E> the type of elements in the input list
     * @param list the input list from which to generate permutations. The list may contain duplicates, which
     *             are treated as distinct elements based on their position in the list.
     * @return a List of Maps, where each Map represents a permutation of the input list. Each key in the map
     *         is an integer representing the position of an element in the list, and the corresponding value
     *         is the element at that position. If the input list is null or empty, the method returns an empty list.
     * @usage Example:
     * <pre>
     * List<String> inputList = Arrays.asList("a", "b", "c");
     * List<Map<Integer, String>> permutations = CollectionHelpers.possiblePermutationsAsMaps(inputList);
     * permutations.forEach(System.out::println);
     * // Output might include maps like {0=a, 1=b, 2=c}, {0=a, 1=c, 2=b}, ..., depending on the permutation logic.
     * </pre>
     */
    private static <E> List<Map<Integer, E>> possiblePermutationsAsMaps(List<E> list) {
        // check if the list is non-null and non-empty
        if (list == null || list.isEmpty()) return Collections.emptyList();
        return IntStream.range(0, list.size())
                // represent each list element as a list of permutation maps
                .mapToObj(i -> IntStream.range(0, list.size())
                        // key - element position, value - element itself
                        .mapToObj(j -> Collections.singletonMap(j, list.get(j)))
                        // Stream<List<Map<Integer,E>>>
                        .collect(Collectors.toList()))
                // reduce a stream of lists to a single list
                .reduce((list1, list2) -> list1.stream()
                        .flatMap(map1 -> list2.stream()
                                // filter out those keys that are already present
                                .filter(map2 -> map2.keySet().stream()
                                        .noneMatch(map1::containsKey))
                                // concatenate entries of two maps, order matters
                                .map(map2 -> new LinkedHashMap<Integer, E>() {{
                                    putAll(map1);
                                    putAll(map2);
                                }}))
                        // list of combinations
                        .collect(Collectors.toList()))
                // otherwise an empty collection
                .orElse(Collections.emptyList());
    }
}

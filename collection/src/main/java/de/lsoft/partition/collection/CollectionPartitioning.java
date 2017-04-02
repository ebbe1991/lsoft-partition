package de.lsoft.partition.collection;

import de.lsoft.partition.Partitioning;
import de.lsoft.partition.Range;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Collection Partitioning
 *
 * @param <T> Objects in Collection
 */
public class CollectionPartitioning<T> extends Partitioning<List<T>> {

    private CollectionPartitioning(List<T> objects) {
        super(objects);
    }

    @Override
    public List<T> invoke(List<T> objects, Range range) {
        List<T> partitionedObjects = new ArrayList<>();
        if (range != null) {
            for (int i = range.lowerEndpoint() - 1; i < range.upperEndpoint(); i++) {
                partitionedObjects.add(objects.get(i));
            }
        }
        return partitionedObjects;
    }

    @Override
    protected int getObjectCount(List<T> objects) {
        return objects.size();
    }

    /**
     * Creates a new CollectionPartitioning.
     */
    public static <T> CollectionPartitioning<T> forObjects(List<T> objects) {
        return new CollectionPartitioning<>(objects);
    }

    /**
     * Creates a new CollectionPartitioning. Partitioning based on natural order.
     */
    public static <T> CollectionPartitioning<T> forObjects(Collection<T> objects) {
        return new CollectionPartitioning<>(objects.stream().sorted().collect(Collectors.toList()));
    }

    /**
     * Creates a new CollectionPartitioning. Partitioning based on the given comparator.
     */
    public static <T> CollectionPartitioning<T> forObjects(Collection<T> objects, Comparator<T> comparator) {
        return new CollectionPartitioning<>(objects.stream().sorted(comparator).collect(Collectors.toList()));
    }
}

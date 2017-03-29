package de.lsoft.partition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Lars on 29.03.2017.
 */
public class CollectionPartitioning<T> extends Partitioning<List<T>> {

    private CollectionPartitioning(List<T> objects) {
        super(objects);
    }

    @Override
    protected List<T> invoke(List<T> objects, Range range) {
        List<T> partitionedObjects = new ArrayList<>();
        if (range != null) {
            for (int i = range.getLowerEndpoint() - 1; i < range.getUpperEndpoint(); i++) {
                partitionedObjects.add(objects.get(i));
            }
        }
        return partitionedObjects;
    }

    @Override
    protected int getObjectCount(List<T> objects) {
        return objects.size();
    }

    public static <T> CollectionPartitioning<T> forObjects(List<T> objects) {
        return new CollectionPartitioning<>(objects);
    }

    public static <T> CollectionPartitioning<T> forObjects(Collection<T> objects) {
        return new CollectionPartitioning<>(objects.stream().sorted().collect(Collectors.toList()));
    }

    public static <T> CollectionPartitioning<T> forObjects(Collection<T> objects, Comparator<T> comparator) {
        return new CollectionPartitioning<>(objects.stream().sorted(comparator).collect(Collectors.toList()));
    }
}

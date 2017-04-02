package de.lsoft.partition;

import java.util.*;

import static java.util.stream.Collectors.toSet;

/**
 * Abstract Partitioning-Object.
 * Builder to invoke a partition towards the objects
 *
 * @param <T> objects to be partitioned
 */
public abstract class Partitioning<T> {

    private Set<Partition> partitions;
    private T objects;

    protected Partitioning(T objects) {
        this.objects = objects;
        this.partitions = new HashSet<>();
    }

    /**
     * Sets the partitions that will be invoked to the objects.
     *
     * @param partitions partitions, may also only be a part of all partitions
     * @return Partition-Builder
     */
    public Partitioning<T> withPartitions(Partition... partitions) {
        this.partitions.addAll(Arrays.asList(partitions));
        return this;
    }

    /**
     * Invokes the partition.
     *
     * @return a map with key = part of partition and value = the partitioned objects
     */
    public Map<Integer, T> invoke() {
        if (objects == null) {
            throw new IllegalStateException("objects not set.");
        }
        if (partitions.size() == 0) {
            throw new IllegalStateException("partitions not set or empty.");
        }
        if (partitions.stream().map(Partition::getTotalParts).collect(toSet()).size() != 1) {
            throw new IllegalStateException("different total parts in partitions.");
        }

        Map<Integer, T> map = new HashMap<>();
        for (Partition partition : partitions) {
            if (!partition.isPartitioned()) {
                map.put(partition.getPart(), objects);
            }
            Optional<Range> range = getRanges(partition.getTotalParts(), getObjectCount(objects)).get(partition.getPart());
            map.put(partition.getPart(), invoke(objects, range.orElse(null)));
        }
        return map;
    }

    /**
     * Invokes a single partitioning, if only one partition is set.
     *
     * @return the objects of the partition
     */
    public T invokeSinglePartition() {
        if (partitions.size() != 1) {
            throw new IllegalStateException();
        }

        Partition partition = partitions.iterator().next();
        if (!partition.isPartitioned()) {
            return objects;
        }
        Optional<Range> range = getRanges(partition.getTotalParts(), getObjectCount(objects)).get(partition.getPart());
        return invoke(objects, range.orElse(null));
    }

    /**
     * Invokes the object partitioning for the given range.
     *
     * @return partitioned objects
     */
    public abstract T invoke(T objects, Range range);

    /**
     * @return count of objects
     */
    protected abstract int getObjectCount(T objects);

    private static Map<Integer, Optional<Range>> getRanges(Integer countPartitions, int countDataset) {
        Map<Integer, Optional<Range>> partitionWithObjectRanges = new HashMap<>();

        Integer simplePartition = countDataset / countPartitions;
        Integer rest = countDataset % countPartitions;
        Integer totalPartitionedDatasets = 0;
        for (int i = 1; i <= countPartitions; i++) {
            Integer countPartitionedDatasets = simplePartition;
            if (rest > 0) {
                countPartitionedDatasets++;
                rest = rest - 1;
            }
            Optional<Range> datasetRange = Optional.empty();
            if (countPartitionedDatasets > 0) {
                datasetRange = Optional.of(new Range(totalPartitionedDatasets + 1, totalPartitionedDatasets + countPartitionedDatasets));
                totalPartitionedDatasets = totalPartitionedDatasets + countPartitionedDatasets;
            }
            partitionWithObjectRanges.put(i, datasetRange);
        }
        return partitionWithObjectRanges;
    }
}

package de.lsoft.partition.collection;

import de.lsoft.partition.Partition;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class CollectionPartitioningTest {

    @Test
    public void Partition_1_1_returns_all_List_Objects() throws Exception {
        List<String> animals = new ArrayList<>();
        animals.add("Mouse");
        animals.add("Cat");
        animals.add("Dog");

        Map<Integer, List<String>> result = CollectionPartitioning
                .forObjects(animals)
                .withPartitions(new Partition(1, 1))
                .invoke();

        assertThat(result.get(1), contains("Mouse", "Cat", "Dog"));
    }

    @Test
    public void Partition_1_1_returns_all_List_Objects2() throws Exception {
        List<String> animals = new ArrayList<>();
        animals.add("Mouse");
        animals.add("Cat");
        animals.add("Dog");

        List<String> result = CollectionPartitioning
                .forObjects(animals)
                .withPartitions(new Partition(1, 1))
                .invokeSinglePartition();

        assertThat(result, contains("Mouse", "Cat", "Dog"));
    }

    @Test
    public void Partition_without_rest() throws Exception {
        List<String> animals = new ArrayList<>();
        animals.add("Mouse");
        animals.add("Cat");
        animals.add("Dog");

        Map<Integer, List<String>> result = CollectionPartitioning
                .forObjects(animals)
                .withPartitions(new Partition(1, 3))
                .withPartitions(new Partition(2, 3))
                .withPartitions(new Partition(3, 3))
                .invoke();

        assertThat(result.get(1), contains("Mouse"));
        assertThat(result.get(2), contains("Cat"));
        assertThat(result.get(3), contains("Dog"));
    }

    @Test
    public void Partition_with_rest() throws Exception {
        List<String> animals = new ArrayList<>();
        animals.add("Mouse");
        animals.add("Cat");
        animals.add("Dog");

        Map<Integer, List<String>> result = CollectionPartitioning
                .forObjects(animals)
                .withPartitions(new Partition(1, 2))
                .withPartitions(new Partition(2, 2))
                .invoke();

        assertThat(result.get(1), contains("Mouse", "Cat"));
        assertThat(result.get(2), contains("Dog"));
    }

    @Test
    public void Partition_with_HashSet_natural_order() throws Exception {
        Set<String> animals = new HashSet<>();
        animals.add("Mouse");
        animals.add("Cat");
        animals.add("Dog");

        Map<Integer, List<String>> result = CollectionPartitioning
                .forObjects(animals)
                .withPartitions(new Partition(1, 3))
                .withPartitions(new Partition(2, 3))
                .withPartitions(new Partition(3, 3))
                .invoke();

        assertThat(result.get(1), contains("Cat"));
        assertThat(result.get(2), contains("Dog"));
        assertThat(result.get(3), contains("Mouse"));
    }

    @Test
    public void Partition_with_Comparator() throws Exception {
        List<String> animals = new ArrayList<>();
        animals.add("Mouse");
        animals.add("Cat");
        animals.add("Dog");

        Map<Integer, List<String>> result = CollectionPartitioning
                .forObjects(animals, String::compareTo)
                .withPartitions(new Partition(1, 3))
                .withPartitions(new Partition(2, 3))
                .withPartitions(new Partition(3, 3))
                .invoke();

        assertThat(result.get(1), contains("Cat"));
        assertThat(result.get(2), contains("Dog"));
        assertThat(result.get(3), contains("Mouse"));
    }

    @Test
    public void No_Objects() throws Exception {
        Map<Integer, List<String>> result = CollectionPartitioning
                .forObjects(Collections.emptyList(), String::compareTo)
                .withPartitions(new Partition(1, 3))
                .withPartitions(new Partition(2, 3))
                .withPartitions(new Partition(3, 3))
                .invoke();

        assertThat(result.get(1), Matchers.empty());
        assertThat(result.get(2), Matchers.empty());
        assertThat(result.get(3), Matchers.empty());
    }

    @Test(expected = IllegalStateException.class)
    public void Multiple_Partitions_with_Single_Invoke() throws Exception {
        List<String> animals = new ArrayList<>();
        animals.add("Mouse");
        animals.add("Cat");
        animals.add("Dog");

        CollectionPartitioning
                .forObjects(animals, String::compareTo)
                .withPartitions(new Partition(1, 3))
                .withPartitions(new Partition(2, 3))
                .withPartitions(new Partition(3, 3))
                .invokeSinglePartition();
    }

    @Test(expected = IllegalStateException.class)
    public void No_Partitions() throws Exception {
        List<String> animals = new ArrayList<>();
        animals.add("Mouse");
        animals.add("Cat");
        animals.add("Dog");

        CollectionPartitioning
                .forObjects(animals)
                .invoke();
    }

    @Test(expected = IllegalStateException.class)
    public void Multiple_Partitions_with_different_total_parts() throws Exception {
        List<String> animals = new ArrayList<>();
        animals.add("Mouse");
        animals.add("Cat");
        animals.add("Dog");

        CollectionPartitioning
                .forObjects(animals)
                .withPartitions(new Partition(1, 3))
                .withPartitions(new Partition(2, 3))
                .withPartitions(new Partition(2, 2))
                .invokeSinglePartition();
    }
}

package de.lsoft.partition;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PartitionTest {

    @Test
    public void Partition_ONE() {
        Partition partition = Partition.ONE;
        assertThat(partition.getPart(), is(1));
        assertThat(partition.getTotalParts(), is(1));
    }

    @Test
    public void Partition_Test() {
        Partition partition = new Partition(1, 2);
        assertThat(partition.getPart(), is(1));
        assertThat(partition.getTotalParts(), is(2));
    }

    @Test (expected = IllegalArgumentException.class)
    public void Partition_greater_part() {
        new Partition(2, 1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void Partition_negativ_part() {
        new Partition(-1, 1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void Partition_0_part() {
        new Partition(0, 2);
    }
}

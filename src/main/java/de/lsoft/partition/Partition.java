package de.lsoft.partition;

import java.io.Serializable;
import java.util.Objects;

public class Partition implements Serializable {

    public static final Partition ONE = new Partition(1, 1);

    private final int part;
    private final int totalParts;

    public Partition(Integer part, Integer totalParts) {
        if (part > totalParts) {
            throw new IllegalArgumentException("total parts must be greater or equal than the part");
        }
        if (part <= 0) {
            throw new IllegalArgumentException("part muss be greater 0");
        }
        this.part = part;
        this.totalParts = totalParts;
    }

    /**
     * @return the part as number
     */
    public int getPart() {
        return part;
    }

    /**
     * @return count of all parts
     */
    public int getTotalParts() {
        return totalParts;
    }

    /**
     * @return true, if the partition is a real partition (so total parts is greater than one)
     */
    public boolean isPartitioned() {
        return getTotalParts() > 1;
    }

    @Override
    public String toString() {
        return String.format("Partition %d of %d", getPart(), getTotalParts());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Partition partition = (Partition) o;
        return part == partition.part && totalParts == partition.totalParts;
    }

    @Override
    public int hashCode() {
        return Objects.hash(part, totalParts);
    }
}

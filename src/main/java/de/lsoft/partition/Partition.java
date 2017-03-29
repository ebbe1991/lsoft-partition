package de.lsoft.partition;

import java.io.Serializable;
import java.util.Objects;

public class Partition implements Serializable {

    public static final Partition ONE = new Partition(1, 1);

    private final int part;
    private final int totalParts;

    public Partition(Integer part, Integer totalParts) {
        if (part > totalParts)
            throw new IllegalArgumentException();
        if (part <= 0)
            throw new IllegalArgumentException();
        if (totalParts <= 0)
            throw new IllegalArgumentException();

        this.part = part;
        this.totalParts = totalParts;
    }

    public int getPart() {
        return part;
    }

    public int getTotalParts() {
        return totalParts;
    }

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

package de.lsoft.partition;

/**
 * Simple Closed-Range-Object with lower and upped endpoints.
 */
public class Range {

    private final int lowerEndpoint;
    private final int upperEndpoint;

    public Range(int lowerEndpoint, int upperEndpoint) {
        if (lowerEndpoint > upperEndpoint) {
            throw new IllegalArgumentException("lower endpoint must be lower than upper endpoint.");
        }

        this.lowerEndpoint = lowerEndpoint;
        this.upperEndpoint = upperEndpoint;
    }

    public int lowerEndpoint() {
        return lowerEndpoint;
    }

    public int upperEndpoint() {
        return upperEndpoint;
    }
}

package de.lsoft.partition;

/**
 * Simple Closed-Range-Object with lower and upped endpoints.
 */
class Range {

    private final int lowerEndpoint;
    private final int upperEndpoint;

    Range(int lowerEndpoint, int upperEndpoint) {
        if (lowerEndpoint > upperEndpoint) {
            throw new IllegalArgumentException("lower endpoint must be lower than upper endpoint.");
        }

        this.lowerEndpoint = lowerEndpoint;
        this.upperEndpoint = upperEndpoint;
    }

    int lowerEndpoint() {
        return lowerEndpoint;
    }

    int upperEndpoint() {
        return upperEndpoint;
    }
}

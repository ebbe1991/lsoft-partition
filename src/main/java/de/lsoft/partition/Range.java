package de.lsoft.partition;

/**
 * Simple Closed-Range-Object with lower and upped endpoints.
 */
class Range {

    private final int lowerEndpoint;
    private final int upperEndpoint;

    Range(int lowerEndpoint, int upperEndpoint) {
        this.lowerEndpoint = lowerEndpoint;
        this.upperEndpoint = upperEndpoint;
    }

    int getLowerEndpoint() {
        return lowerEndpoint;
    }

    int getUpperEndpoint() {
        return upperEndpoint;
    }
}

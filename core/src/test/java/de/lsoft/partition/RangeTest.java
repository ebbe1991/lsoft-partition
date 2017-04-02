package de.lsoft.partition;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RangeTest {

    @Test
    public void range_test() {
        Range range = new Range(1, 5);
        assertThat(range.lowerEndpoint(), is(1));
        assertThat(range.upperEndpoint(), is(5));
    }

    @Test
    public void range_test2() {
        Range range = new Range(1, 1);
        assertThat(range.lowerEndpoint(), is(1));
        assertThat(range.upperEndpoint(), is(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void lower_endpoint_greater() {
        new Range(2, 1);
    }
}

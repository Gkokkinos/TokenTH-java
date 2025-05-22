package com.georgios;

/**
 * Class that holds a time interval with start and end time properties
 */
public class Interval {
    int start;
    int end;

    public Interval(int start, int end) {
        validateInput(start, end);
        this.start = start;
        this.end = end;
    }

    private void validateInput(int lower, int upper) {
        if (upper < lower) {
            throw new RuntimeException("time interval can't have an end time that is smaller than the start time!");
        }

        if (lower < 0) {
            throw new RuntimeException("time can't be negative.. yet?");
        }
    }

    @Override
    public String toString() {
            return "[" + start + "," + end + "]";
    }

    @Override
    public boolean equals(Object object) {
        return (((Interval) object).start == this.start && ((Interval) object).end == this.end);
    }
}


package com.georgios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class manages a collection of time intervals
 */
public class IntervalManager {

    private List<Interval> allIntervals = new ArrayList<>();

    /**
     * Add an interval to the list or merge it with existing intervals if it satisfies the merging conditions
     */
    public void addInterval(Interval interval) {
        allIntervals.add(interval);
        if (allIntervals.size() > 1) {
            MergeIntervals();
        }
    }

    /**
     * Iterate through the intervals list, get the first element and the new added element (last in list) and check if we should merge them
     * If merging is decided, remove the two elements and add the new one.
     * Then we call this  function in recursion until no merging is furthermore required
     */
    private List<Interval> MergeIntervals() {
        boolean shouldMerge = false;
        Interval currentInterval = null;
        Interval nextInterval = null;
        int jIter = allIntervals.size() -1;
        for (int i = 0; i< allIntervals.size()-1; i++) {
            currentInterval = allIntervals.get(i);
            for (int j = jIter ; j > i; j--) {
                nextInterval = allIntervals.get(j);
                if (areIntervalsOverlapping(currentInterval, nextInterval) || areIntervalsAdjacent(currentInterval, nextInterval)) {
                    shouldMerge = true;
                    break;
                }
            }
            if (shouldMerge) { break; }
        }
        if (shouldMerge)
        {
            allIntervals.add(new Interval(Math.min(currentInterval.start, nextInterval.start), Math.max(currentInterval.end, nextInterval.end)));
            allIntervals.remove(currentInterval);
            allIntervals.remove(nextInterval);
            if (allIntervals.size() > 1) {
                allIntervals = MergeIntervals();
            }
        }
        return allIntervals;
    }

    //Checks if intervals are adjacent e.g. [0,5] -> [6,9]
    private boolean areIntervalsAdjacent(Interval currentInterval, Interval nextInterval) {
        if (currentInterval.start + 1 == nextInterval.start || currentInterval.start == nextInterval.start + 1
                || currentInterval.end + 1 == nextInterval.start || currentInterval.end == nextInterval.start + 1
                || currentInterval.end + 1 == nextInterval.end || currentInterval.end == nextInterval.end + 1) {
            return true;
        }
        return false;
    }

    //Checks if intervals are overlapping e.g. [0,5]-[5,6] OR [10,20]-[13,17] OR [1,10]-[7,12] .etc
    //notes -> thought process:
    /*if (currentInterval.start == nextInterval.start || currentInterval.end == nextInterval.end || currentInterval.end == nextInterval.start ||  currentInterval.start == nextInterval.end) {
        return true;
    }
        if ((currentInterval.start >= nextInterval.start && currentInterval.end <= nextInterval.end) || (nextInterval.start >= currentInterval.start && nextInterval.end <= currentInterval.end)){
        return true;
    }*/
    private boolean areIntervalsOverlapping(Interval currentInterval, Interval nextInterval) {
        if ((currentInterval.end <= nextInterval.end && currentInterval.end >= nextInterval.start) || (nextInterval.end <= currentInterval.end && nextInterval.end >= currentInterval.start)) {
            return true;
        }
        return false;
    }

    //we sort and save the intervals every time we get it -potentially sort on merge/add depending on use of application, caching also an option to avoid sorting a sorted list
    public List<Interval> getIntervals() {
        allIntervals = sortIntervals();
        return allIntervals;
    }

    //sort based on start time
    private List<Interval> sortIntervals() {
        List<Interval> sortedIntervals = new ArrayList<>();
        List<Integer> sortedStartTimes = new ArrayList<>();
        allIntervals.forEach(interval -> sortedStartTimes.add(interval.start));
        Collections.sort(sortedStartTimes);

        sortedStartTimes.forEach(st -> {
            for (Interval interval : allIntervals) {
                if (interval.start == st) {
                    sortedIntervals.add(interval);
                    break;
                }
            }
        });
        return sortedIntervals;
    }

    public void removeInterval(Interval intervalToRemove) {
        for (int i = 0; i< allIntervals.size(); i++) {
            if (allIntervals.get(i).equals(intervalToRemove)) {
                allIntervals.remove(intervalToRemove);
                break;
            }
            if (areIntervalsOverlapping(allIntervals.get(i), intervalToRemove)) {
                splitIntervalAndAddBack(allIntervals.get(i), intervalToRemove);
                break;
            }
        }
    }

    private void splitIntervalAndAddBack(Interval intervalToSplit, Interval intervalRemoved) {
        allIntervals.remove(intervalToSplit);
        if (intervalRemoved.start > intervalToSplit.start && intervalRemoved.end < intervalToSplit.end) {
            allIntervals.add(new Interval(intervalToSplit.start, intervalRemoved.start));
            allIntervals.add(new Interval(intervalRemoved.end, intervalToSplit.end));
        }
        //TODO: more cases e.g. more overlapping cases one border, removal spanning two different intervals already there, negative no removal..
    }


}

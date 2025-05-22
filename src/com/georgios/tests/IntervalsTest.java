package com.georgios.tests;

import com.georgios.Interval;
import com.georgios.IntervalManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;


public class IntervalsTest {

    IntervalManager intervalManager;

    @BeforeEach
    public void initManager() {
        intervalManager = new IntervalManager();
    }

    @Test
    public void simpleTest() {
        intervalManager.addInterval(new Interval(1,3));
        intervalManager.addInterval(new Interval(5,7));
        intervalManager.addInterval(new Interval(2,6));
        System.out.println(intervalManager.getIntervals());
        Assertions.assertEquals(List.of(new Interval(1,7)).toString() , intervalManager.getIntervals().toString());
    }

    @Test
    public void simpleTestReverse() {
        intervalManager.addInterval(new Interval(2,6));
        intervalManager.addInterval(new Interval(5,7));
        intervalManager.addInterval(new Interval(1,3));
        System.out.println(intervalManager.getIntervals());
        Assertions.assertEquals(List.of(new Interval(1,7)).toString() , intervalManager.getIntervals().toString());
    }

    @Test
    public void simpleTestSub1() {
        intervalManager.addInterval(new Interval(1,3));
        intervalManager.addInterval(new Interval(2,6));
        System.out.println(intervalManager.getIntervals());
        Assertions.assertEquals(List.of(new Interval(1,6)).toString() , intervalManager.getIntervals().toString());
    }

    @Test
    public void simpleTestSub2() {
        intervalManager.addInterval(new Interval(1,3));
        intervalManager.addInterval(new Interval(5,7));
        System.out.println(intervalManager.getIntervals());
        Assertions.assertEquals(List.of(new Interval(1,3), new Interval(5,7)).toString() , intervalManager.getIntervals().toString());
    }

    @Test
    public void simpleTestOther() {
        intervalManager.addInterval(new Interval(111,113));
        intervalManager.addInterval(new Interval(100,200));
        System.out.println(intervalManager.getIntervals());
        Assertions.assertEquals(List.of(new Interval(100,200)).toString(), intervalManager.getIntervals().toString());
    }

    @Test
    public void mergeTest1() {
        intervalManager.addInterval(new Interval(1,3));
        intervalManager.addInterval(new Interval(100,150));
        intervalManager.addInterval(new Interval(1,4));
        intervalManager.addInterval(new Interval(1,98));
        intervalManager.addInterval(new Interval(101,170));
        System.out.println(intervalManager.getIntervals());
        Assertions.assertEquals(List.of(new Interval(1,98), new Interval(100,170)).toString() , intervalManager.getIntervals().toString());
    }

    @Test
    public void mergeTest2() {
        intervalManager.addInterval(new Interval(1,3));
        intervalManager.addInterval(new Interval(4,99));
        intervalManager.addInterval(new Interval(1,65));
        intervalManager.addInterval(new Interval(101,150));
        intervalManager.addInterval(new Interval(100,170));
        intervalManager.addInterval(new Interval(120,169));
        System.out.println(intervalManager.getIntervals());
        Assertions.assertEquals(List.of(new Interval(1,170)).toString() , intervalManager.getIntervals().toString());
    }

    @Test
    public void sortingAndMergingTest() {
        intervalManager.addInterval(new Interval(101,167));
        intervalManager.addInterval(new Interval(105,170));
        intervalManager.addInterval(new Interval(171,172));
        intervalManager.addInterval(new Interval(3,3));
        intervalManager.addInterval(new Interval(4,99));
        intervalManager.addInterval(new Interval(0,97));
        System.out.println(intervalManager.getIntervals());
        Assertions.assertEquals(List.of(new Interval(0,99), new Interval(101,172)).toString() , intervalManager.getIntervals().toString());
    }

    @Test
    public void duplicateTest() {
        intervalManager.addInterval(new Interval(1,1));
        intervalManager.addInterval(new Interval(1,1));
        System.out.println(intervalManager.getIntervals());
        Assertions.assertEquals(List.of(new Interval(1,1)).toString() , intervalManager.getIntervals().toString());
    }

    @Test
    public void negativeTest() {
        Assertions.assertThrows(RuntimeException.class, ()-> intervalManager.addInterval(new Interval(-1,1)));
    }

    @Test
    public void smallerUpperTest() {
        Assertions.assertThrows(RuntimeException.class, ()->intervalManager.addInterval(new Interval(1,0)));
    }

    @Test
    public void removalSameIntervalCaseTest() {
        intervalManager.addInterval(new Interval(1,1));
        intervalManager.removeInterval(new Interval(1,1));
        System.out.println(intervalManager.getIntervals());
        Assertions.assertTrue(intervalManager.getIntervals().isEmpty());
    }

    @Test
    public void removalSimpleCaseTest() {
        intervalManager.addInterval(new Interval(1,10));
        intervalManager.removeInterval(new Interval(3,5));
        System.out.println(intervalManager.getIntervals());
        Assertions.assertEquals(List.of(new Interval(1,3), new Interval(5,10)).toString() , intervalManager.getIntervals().toString());
    }


}

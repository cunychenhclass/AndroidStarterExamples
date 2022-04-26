package edu.cuny.bc.cisc3171.teachaverageapp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Simple Unit Tests
 */
public class AverageUnitTest {
    @Test
    public void test_computeAverage1() {
        Averager averager = new Averager();
        int a = 1;
        int b = 5;
        double expected = 3.0;
        double actual = averager.computeAverage(a, b);
        double delta = 1E-10;
        assertEquals("Failed computing the average", expected, actual, delta);
    }

    @Test
    public void test_computeAverage2() {
        Averager averager = new Averager();
        int a = 2;
        int b = 5;
        double expected = 3.5;
        double actual = averager.computeAverage(a, b);
        double delta = 1E-10;
        assertEquals("Failed computing the average", expected, actual, delta);
    }

}
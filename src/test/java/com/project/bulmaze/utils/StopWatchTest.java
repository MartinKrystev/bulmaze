package com.project.bulmaze.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StopWatchTest {

    @Test
    public void testGetElapsedTimeFormatted() {
        StopWatch stopWatch = new StopWatch();

        stopWatch.setStartTime(1L);
        stopWatch.setStopTime(10L);

        Assertions.assertEquals(1L, stopWatch.getStartTime());
        Assertions.assertEquals(10L, stopWatch.getStopTime());
    }

    @Test
    public void testIsRunning() {
        StopWatch stopWatch = new StopWatch();

        Assertions.assertFalse(stopWatch.isRunning());
    }

    @Test
    public void testStart() {
        StopWatch stopWatch = new StopWatch();

        stopWatch.start();

        Assertions.assertTrue(stopWatch.isRunning());
    }

    @Test
    public void testStop() {
        StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        stopWatch.stop();

        Assertions.assertFalse(stopWatch.isRunning());
    }

    @Test
    public void testTime() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.setStartTime(1L);
        stopWatch.setStopTime(2L);

        long elapsed = 1L;

        Assertions.assertEquals(elapsed, stopWatch.getElapsedTime());
        Assertions.assertEquals(0L, stopWatch.getElapsedTimeSecs());
        Assertions.assertEquals("00:00:00", stopWatch.getElapsedTimeFormatted());
    }

}

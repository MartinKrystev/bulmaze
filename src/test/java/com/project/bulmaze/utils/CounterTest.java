package com.project.bulmaze.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CounterTest {

    @Test
     void testCounter() {
        Counter counter = new Counter()
                .setCount(1);
        Counter counter2 = new Counter()
                .setCount(2);
        Counter counter3 = new Counter()
                .setCount(3);

        counter2.incrementAndGet();
        counter3.reset();

        Assertions.assertEquals(1, counter.getCount());
        Assertions.assertEquals(3, counter2.getCount());
        Assertions.assertEquals(0, counter3.getCount());
    }
}

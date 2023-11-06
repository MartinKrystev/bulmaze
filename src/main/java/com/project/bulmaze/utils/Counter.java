package com.project.bulmaze.utils;

public class Counter {
    private int count;

    public Counter() {
        this.count = 0;
    }

    public int getCount() {
        return count;
    }

    public Counter setCount(int count) {
        this.count = count;
        return this;
    }

    public void reset() {
        this.count = 0;
    }

    public int incrementAndGet() {
        return ++count;
    }
}

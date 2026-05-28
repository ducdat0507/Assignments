package com.example.ejb;

import javax.ejb.Singleton;

@Singleton
public class VisitorCounterEJB {
    private int count = 0;

    public synchronized int incrementAndGet() {
        count++;
        return count;
    }
}

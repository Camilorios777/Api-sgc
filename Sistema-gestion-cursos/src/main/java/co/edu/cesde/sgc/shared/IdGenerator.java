package co.edu.cesde.sgc.shared;

import java.util.concurrent.atomic.AtomicLong;


public class IdGenerator {

    private final AtomicLong counter = new AtomicLong(0);

    public Long next() {
        return counter.incrementAndGet();
    }
}

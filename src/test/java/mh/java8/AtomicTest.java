package mh.java8;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

import static org.junit.Assert.assertEquals;

public class AtomicTest {

    @Test
    public void testAtomicUpdate() throws Exception {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        int value = atomicInteger.updateAndGet(x -> Math.max(x, 6));
        assertEquals(6, value);
    }


    @Test
    public void testAtomicAccumulate() throws Exception {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        int value = atomicInteger.accumulateAndGet(6, Math::max);
        assertEquals(6, value);
    }

    @Test
    public void testAdder() throws Exception {
        LongAdder adder = new LongAdder();
        adder.add(10);

        assertEquals(10, adder.sum());
    }

    //TODO HUDYMA !!!!
//    @Test
//    public void testAccumulator() throws Exception {
//        LongAccumulator accumulator = new LongAccumulator(0);
//        POPRAWIC !!!!!!!!!!!
//
//
//    }

    // ConcurrentHashMap - tries of a map are typically stored in buckets accessed by the generated hashcode of the key.
    // But if many keys return the same hashcode, performance will deteriorate because buckets are implemented as
    // Lists with O(n) retrieval.
    // In Java 8, when the buckets become too big, they’re dynamically replaced with sorted trees,
    // which have O(log(n)) retrieval.
    // Note that this is possible only when the keys are Comparable (for example, String or Number classes).


}

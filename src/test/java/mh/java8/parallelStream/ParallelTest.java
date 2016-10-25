package mh.java8.parallelStream;

import org.junit.Test;

import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelTest {

    @Test
    public void testSetThreadPoolSize() throws Exception {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "12");
    }

    @Test
    public void testParallel() throws Exception {
        long sum = Stream.iterate(1l, i -> i + 1)
                         .limit(1000)
                         .parallel()
                         .reduce(0L, Long::sum);
        System.out.println(sum);
    }


    private static class Accumulator {
        public long total = 0;
        public void add(long value) { total += value; }
    }

    public static long sideEffectParallelSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
        return accumulator.total;
    }

    @Test
    public void testPParallelTestarallelProblems() throws Exception {
        System.out.println("parallel sum done in: " + sideEffectParallelSum(10_000_000));
        System.out.println("correct value is      50000005000000" );
    }
}

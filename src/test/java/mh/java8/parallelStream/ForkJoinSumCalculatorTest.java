package mh.java8.parallelStream;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

import static org.junit.Assert.assertEquals;

public class ForkJoinSumCalculatorTest {

    @Test
    public void testForkJoinSumCalculaltor() throws Exception {
        long[] numbers = LongStream.rangeClosed(1, 100_000_000).toArray();

        long sequentialSum = 0;
        for (long i : numbers) {
            sequentialSum += i;
        }

        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        long forkJoinSum = new ForkJoinPool().invoke(task);

        assertEquals(sequentialSum, forkJoinSum);
    }

}

package mh.java8.streams;

import org.junit.Test;

import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class InfiniteStreamsTest {

    @Test
    public void testIterate() throws Exception {
        Stream.iterate(0, n -> n + 2)
              .limit(10)
              .forEach(System.out::println);
    }

    @Test
    public void testFibonaci() throws Exception {
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
              .limit(20)
              .map(t -> t[0])
              .forEach(System.out::println);
    }

    @Test
    public void testGenerate() throws Exception {
        Stream.generate(Math::random)
              .limit(10)
              .forEach(System.out::println);
    }

    @Test
    public void testGenerateConstants() throws Exception {
        IntStream.generate(() -> 1).forEach(System.out::println);

        IntStream.generate(new IntSupplier() {
            public int getAsInt() {
                return 2;
            }
        }).forEach(System.out::println);
    }

    @Test
    public void testFibonacciSupplier() throws Exception {
        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;

            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };
        IntStream.generate(fib).limit(10).forEach(System.out::println);
    }
}

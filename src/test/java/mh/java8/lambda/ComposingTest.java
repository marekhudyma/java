package mh.java8.lambda;

import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class ComposingTest {

    @Test
    public void testAndThen() throws Exception {
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g); // g(f)
        int result = h.apply(1);
        assertEquals(4, result);
    }

    @Test
    public void testComposing() throws Exception {
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.compose(g); // f(g)
        int result = h.apply(1);
        assertEquals(3, result);
    }



}

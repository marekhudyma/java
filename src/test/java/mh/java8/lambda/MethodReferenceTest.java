package mh.java8.lambda;

import com.google.common.collect.ImmutableList;
import mh.java8.patterns.core.Apple;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Comparator.comparing;

public class MethodReferenceTest {

    @Test
    public void testMethodReference() throws Exception {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                                              new Apple(150, "green"),
                                              new Apple(120, "red"));
        inventory.sort(comparing(Apple::getWeight));
    }

    @Test
    public void testMethodReference2() throws Exception {
        List<String> inventory = ImmutableList.of("a", "c", "B");
        inventory.sort(String::compareToIgnoreCase);
    }

    @Test
    public void testMethodReference_constructor() throws Exception {
        Supplier<Apple> c1 = Apple::new;
        Apple a1 = c1.get();
    }

    @Test
    public void testMethodReference_constructor2() throws Exception {
        Function<Integer, Apple> c1 = Apple::new;
        Apple a1 = c1.apply(100);
    }

    @Test
    public void testMethodReference_constructor3() throws Exception {
        BiFunction<Integer, String, Apple> c3 = Apple::new;
        Apple a1 = c3.apply(100, "red");
    }
}

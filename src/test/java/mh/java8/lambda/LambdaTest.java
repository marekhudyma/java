package mh.java8.lambda;

import com.google.common.collect.ImmutableList;
import mh.java8.patterns.core.Apple;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

import static java.util.Comparator.comparing;

public class LambdaTest {

    @Test
    public void testPredicate() throws Exception {
        List<String> listOfStrings = ImmutableList.of("a", "", "b", "", "c");

        Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
        List<String> nonEmpty1 = filter(listOfStrings, nonEmptyStringPredicate);
        List<String> nonEmpty2 = filter(listOfStrings, (String s) -> !s.isEmpty());

        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                                              new Apple(150, "green"),
                                              new Apple(120, "red"));
        List<Apple> greenApples1 = filter(inventory, a -> "green".equals(a.getColor()));
        List<Apple> greenApples2 = filter(inventory, (Apple a) -> "green".equals(a.getColor()));
    }

    @Test
    public void testDescending() throws Exception {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                                              new Apple(150, "green"),
                                              new Apple(120, "red"));
        inventory.sort(comparing(Apple::getWeight)
                               .reversed()
                               .thenComparing(Apple::getColor));
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> results = new ArrayList<>();
        for (T s : list) {
            if (p.test(s)) {
                results.add(s);
            }
        }
        return results;
    }

    @Test
    public void testConsumer() throws Exception {
        forEach(ImmutableList.of(1,2,3,4,5), (Integer i) -> System.out.println(i));
    }

    public static <T> void forEach(List<T> list, Consumer<T> c) {
        for(T i : list) {
            c.accept(i);
        }
    }

    @Test
    public void testFunction() throws Exception {
        List<Integer> l = map(ImmutableList.of("a", "bc", "def"), (String s) -> s.length());
    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for(T s : list) {
            result.add(f.apply(s));
        }
        return result;
    }

    @Test
    public void testIntPredicate() throws Exception {
        IntPredicate evenNumbers = (int i) -> i % 2 == 0;
        evenNumbers.test(100);
    }

}

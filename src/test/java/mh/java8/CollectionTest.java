package mh.java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CollectionTest {

    @Test
    public void testMap() throws Exception {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "one");

        assertEquals("two", map.getOrDefault(2, "two"));

        map.computeIfAbsent(5, k -> "five");

        assertEquals("five", map.get(5));
    }

    @Test
    public void testList() throws Exception {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        numbers.replaceAll(x -> x * 2);
        System.out.println(numbers);
    }

}

package mh.java8.streams;

import com.google.common.collect.ImmutableList;
import mh.java8.streams.core.Dish;
import mh.java8.streams.core.DishFactory;
import mh.java8.streams.core.Trader;
import mh.java8.streams.core.Transaction;
import mh.java8.streams.core.TransactionFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static org.junit.Assert.assertEquals;

public class StreamTest {

    @Test
    public void testStream() throws Exception {
        List<String> threeHighCaloricDishName = DishFactory.create().stream()
                                                           .filter(dish -> dish.getCalories() > 300)
                                                           .map(Dish::getName)
                                                           .limit(3)
                                                           .collect(toList());
        System.out.println(threeHighCaloricDishName);
    }

    @Test(expected = IllegalStateException.class)
    public void testStreamIllegalStateException() throws Exception {
        List<String> list = ImmutableList.of("a", "b", "c");
        Stream<String> s = list.stream();
        s.forEach(System.out::println);
        s.forEach(System.out::println); //can consume stream only once
    }

    @Test
    public void testForEach() throws Exception {
        List<String> names = new ArrayList<>();
        for (Dish dish : DishFactory.create()) {
            names.add(dish.getName());
        }
    }

    @Test
    public void testForEachBehindTheScenes() throws Exception {
        List<String> names = new ArrayList<>();
        Iterator<Dish> iterator = DishFactory.create().iterator();
        while (iterator.hasNext()) {
            Dish d = iterator.next();
            names.add(d.getName());
        }
    }

    @Test
    public void testForEachStreams() throws Exception {
        DishFactory.create().stream().map(Dish::getName).collect(toList());
    }

    @Test
    public void testStreamSystemOutPrintln() throws Exception {
        List<String> threeHighCaloricDishName = DishFactory.create().stream()
                                                           .filter(dish -> {
                                                               System.out.println("filtering " + dish.getName());
                                                               return dish.getCalories() > 300;
                                                           })
                                                           .map(dish -> {
                                                               System.out.println("mapping " + dish.getName());
                                                               return dish.getName();
                                                           })
                                                           .limit(3)
                                                           .collect(toList());
        System.out.println(threeHighCaloricDishName);
    }

    @Test
    public void testStreamFiltering() throws Exception {
        List<Dish> vegatarianMenu = DishFactory.create().stream()
                                               .filter(Dish::isVegetarian)
                                               .collect(toList());

        List<Integer> numbers = ImmutableList.of(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
               .filter(i -> i % 2 == 0)
               .distinct()
               .forEach(System.out::println);

        List<Dish> dishes1 = DishFactory.create().stream()
                                        .filter(d -> d.getCalories() > 300)
                                        .limit(3)
                                        .collect(toList());

        List<Dish> dishes2 = DishFactory.create().stream()
                                        .filter(d -> d.getCalories() > 300)
                                        .skip(2)
                                        .collect(toList());
    }

    @Test
    public void testStreamMapping() throws Exception {
        List<String> dishNames = DishFactory.create().stream()
                                            .map(Dish::getName)
                                            .collect(toList());

        List<String> words = ImmutableList.of("a", "bc", "def", "g");
        List<Integer> wordLengths = words.stream()
                                         .map(String::length)
                                         .collect(toList());

        List<Integer> dishNameLengths = DishFactory.create().stream()
                                                   .map(Dish::getName)
                                                   .map(String::length)
                                                   .collect(toList());
    }

    @Test
    public void testStremFlatMap() throws Exception {
        String[] words = {"Goodbye", "World"};

        Arrays.stream(words)
              .map(word -> word.split(""))
              .flatMap(Arrays::stream)
              .distinct()
              .forEach(System.out::println);


        //Stream<String> streamOfwords = Arrays.stream(words);
    }

    @Test
    public void testStremMatching() throws Exception {
        if (DishFactory.create().stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("There is a vegetarian food");
        }

        if (DishFactory.create().stream().allMatch(dish -> dish.getCalories() < 1000)) {
            System.out.println("All food is healthy");
        }

        if (DishFactory.create().stream().noneMatch(dish -> dish.getCalories() >= 1000)) {
            System.out.println("All food is healthy 2");
        }
    }

    @Test
    public void testStremFinding() throws Exception {
        Optional<Dish> dish = DishFactory.create().stream()
                                         .filter(Dish::isVegetarian)
                                         .findAny();

        DishFactory.create().stream()
                   .filter(Dish::isVegetarian)
                   .findAny()
                   .ifPresent(d -> System.out.println(d.getName()));
    }

    @Test
    public void testStreamReduce() throws Exception {
        List<Integer> numbers = ImmutableList.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        assertEquals(55, sum);
        int sum2 = numbers.stream().reduce(0, Integer::sum);
        assertEquals(55, sum2);

        Optional<Integer> sum3 = numbers.stream().reduce(Integer::sum);
        assertEquals(Long.valueOf(55), Long.valueOf(sum3.get()));
    }


    @Test
    public void testMax() throws Exception {
        List<Integer> numbers = ImmutableList.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        assertEquals(Long.valueOf(10), Long.valueOf(max.get()));

        Optional<Dish> minDish = DishFactory.create().stream().min(
                (Dish dish1, Dish dish2) -> Integer.valueOf(dish1.getCalories()).compareTo(dish2.getCalories()));

    }

    @Test
    public void testFindAllTransactionsIn2011AndSortByValueAsc() throws Exception {
        List<Transaction> transactions2011 = TransactionFactory.create().stream()
                                                               .filter(transaction -> transaction.getYear() == 2011)
                                                               .sorted(comparing(Transaction::getValue))
                                                               .collect(toList());
    }

    @Test
    public void testFindUniqueCitriesWhereTraiderWork() throws Exception {
        List<String> cities = TransactionFactory.create().stream()
                                                .map(transaction -> transaction.getTrader().getCity())
                                                .distinct()
                                                .collect(toList());
        Set<String> cities2 = TransactionFactory.create().stream()
                                                .map(transaction -> transaction.getTrader().getCity())
                                                .collect(toSet());
    }

    @Test
    public void testFindAllTraidersFromCambridgeAndSortByName() throws Exception {
        List<Trader> traders = TransactionFactory.create().stream()
                                                 .map(Transaction::getTrader)
                                                 .filter(trader -> trader.getCity().equals("Cambridge"))
                                                 .distinct()
                                                 .sorted(comparing(Trader::getName))
                                                 .collect(toList());
    }

    @Test
    public void testReturnAStringOfTraidersNamesSortedAsc() throws Exception {
        String names = TransactionFactory.create().stream()
                                         .map(transaction -> transaction.getTrader().getName())
                                         .distinct()
                                         .sorted()
                                         .reduce("", (n1, n2) -> n1 + n2);

        String names2 = TransactionFactory.create().stream()
                                          .map(transaction -> transaction.getTrader().getName()).distinct()
                                          .sorted()
                                          .collect(joining());
    }

    @Test
    public void testAnyTraiderBasedInMilan() throws Exception {
        boolean traderMilan = TransactionFactory.create().stream()
                                                .anyMatch(transaction -> transaction.getTrader().getCity()
                                                                                    .equals("Milan"));
    }

    @Test
    public void testPrintAllTransactionsValuesFromTheTradersInCambridge() throws Exception {
        TransactionFactory.create().stream()
                          .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                          .map(Transaction::getValue)
                          .sorted(comparing(Integer::intValue).reversed())
                          .forEach(System.out::println);
    }

    @Test
    public void testMappingToPrimitives() throws Exception {
        int calories = DishFactory.create().stream()
                                  .mapToInt(Dish::getCalories)
                                  .sum();
    }

    @Test
    public void testMappingToPrimitivesAndBox() throws Exception {
        Optional<Integer> calories = DishFactory.create().stream()
                                                .mapToInt(Dish::getCalories)
                                                .boxed()
                                                .reduce(Integer::sum);
    }

    @Test
    public void testOptionalInt() throws Exception {
        OptionalInt max = DishFactory.create().stream()
                                     .mapToInt(Dish::getCalories)
                                     .max();

        int max2 = DishFactory.create().stream()
                              .mapToInt(Dish::getCalories)
                              .max()
                              .orElse(1);
    }

    @Test
    public void testRange() throws Exception {
        IntStream.rangeClosed(1, 100)
                .filter(n -> n % 2 == 0)
                .forEach(System.out::println);
    }

    @Test
    public void testStreamOf() throws Exception {
        Stream.of("a", "b", "c")
                .map(String::toUpperCase)
                .forEach(System.out::println);

        Stream.empty().forEach(System.out::println);
    }

    @Test
    public void testStreamArray() throws Exception {
        int[] numbers = {1,2,3,4,5};
        int sum = Arrays.stream(numbers).sum();
    }



}

package mh.java8.streams;

import mh.java8.streams.core.Dish;
import mh.java8.streams.core.DishFactory;
import org.junit.Test;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.summarizingInt;
import static java.util.stream.Collectors.summingInt;

public class CollectorsTest {

    @Test
    public void testCount() throws Exception {
        long size = DishFactory.create().stream().count();
    }

    @Test
    public void testCollectMaxBy() throws Exception {
        Optional<Dish> mostCaloriesDish = DishFactory.create().stream().collect(maxBy(comparing(Dish::getCalories)));
    }

    @Test
    public void testCollectReduceMaxBy() throws Exception {
        Optional<Dish> mostCalorieDish = DishFactory.create().stream()
                                                    .collect(reducing(
                                                            (d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
    }

    @Test
    public void testCollectSumming() throws Exception {
        int totalCalories = DishFactory.create().stream().collect(summingInt(Dish::getCalories));
    }

    @Test
    public void testCollectReduceSumming() throws Exception {
        int totalCalories = DishFactory.create().stream().collect(reducing(0, Dish::getCalories, Integer::sum));
    }


    @Test
    public void testSummarizing() throws Exception {
        IntSummaryStatistics menuStatistics = DishFactory.create().stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(menuStatistics);
    }

    @Test
    public void testCollectJoining() throws Exception {
        String shortMenu = DishFactory.create().stream().map(Dish::getName).collect(joining());
    }

    @Test
    public void testCollectReduceJoining() throws Exception {
        String shortMenu = DishFactory.create().stream()
                                      .map(Dish::getName).collect(reducing((s1, s2) -> s1 + s2)).get();
    }

    @Test
    public void testGroupingBy() throws Exception {
        Map<Dish.Type, List<Dish>> dishesByType = DishFactory.create().stream().collect(groupingBy(Dish::getType));
    }

    public enum CaloricLevel { DIET, NORMAL, FAT }

    @Test
    public void testGroupingBy2() throws Exception {
        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel =
                DishFactory.create().stream()
                           .collect(groupingBy(dish -> {
                               if (dish.getCalories() <= 400) {
                                   return CaloricLevel.DIET;
                               } else if (dish.getCalories() <= 700) {
                                   return
                                           CaloricLevel.NORMAL;
                               } else {
                                   return CaloricLevel.FAT;
                               }
                           }));
    }

    @Test
    public void testGroupingByMultilevel() throws Exception {
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel =
                DishFactory.create().stream().collect(
                        groupingBy(Dish::getType, groupingBy(dish -> {
                            if (dish.getCalories() <= 400) {
                                return CaloricLevel.DIET;
                            } else if (dish.getCalories() <= 700) {
                                return CaloricLevel.NORMAL;
                            } else {
                                return CaloricLevel.FAT;
                            }
                        }))
                );
    }

    @Test
    public void testGroupingCounting() throws Exception {
        Map<Dish.Type, Long> typesCount = DishFactory.create().stream()
                                                     .collect(groupingBy(Dish::getType, counting()));
    }


    @Test
    public void testGroupingSubGroup() throws Exception {
        Map<Dish.Type, Dish> mostCaloricByType =
                DishFactory.create().stream()
                           .collect(groupingBy(Dish::getType,
                                               collectingAndThen(
                                                       maxBy(comparingInt(Dish::getCalories)),
                                                       Optional::get)));
    }

    @Test
    public void testGroupingSumming() throws Exception {
        Map<Dish.Type, Integer> totalCaloriesByType =
                DishFactory.create().stream()
                           .collect(groupingBy(Dish::getType,
                                               summingInt(Dish::getCalories)));
    }

    @Test
    public void testPartitioning() throws Exception {
        Map<Boolean, List<Dish>> partitionedMenu =
                DishFactory.create().stream()
                           .collect(partitioningBy(Dish::isVegetarian));
    }

    @Test
    public void testPartitioningGroupBy() throws Exception {
        Map<Boolean, Map<Dish.Type, List<Dish>>> partitionedMenu =
                DishFactory.create().stream()
                           .collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
    }

    public boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot)
                        .noneMatch(i -> candidate % i == 0);
    }



}

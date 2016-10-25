package mh.java8.patterns;

import mh.java8.patterns.core.Apple;
import mh.java8.patterns.core.ApplePredicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LambdaAfter {

    public static void main(String...args) {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                                              new Apple(150, "green"),
                                              new Apple(120, "red"));

        List<Apple> heavyApples = filterApples(inventory, (Apple apple) -> apple.getWeight() > 100);

        List<Apple> greenApples = filterApples(inventory, (Apple apple) -> "green".equals(apple.getColor()));

        List<Apple> redApples = filter(inventory, (Apple apple) -> "red".equals(apple.getColor()));

        inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));

        new Thread(() -> System.out.println("Thread hear")).start();


    }

    public static List<Apple> filterApples(List<Apple> apples, ApplePredicate predicate) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : apples) {
            if(predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    private interface Predicate<T> {
        boolean test(T t);
    }

    private static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for(T e: list) {
            if(p.test(e)) {
                result.add(e);
            }
        }
        return result;
    }

}

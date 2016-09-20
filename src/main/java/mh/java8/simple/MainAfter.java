package mh.java8.simple;

import mh.java8.simple.core.Apple;
import mh.java8.simple.core.ApplePredicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainAfter {

    public static void main(String...args) {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                                              new Apple(150, "green"),
                                              new Apple(120, "red"));

        List<Apple> heavyApples = filterApples(inventory, (Apple apple) -> apple.getWeight() > 100);

        List<Apple> greenApples = filterApples(inventory, (Apple apple) -> "green".equals(apple.getColor()));

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

}

package mh.java8.patterns;

import mh.java8.patterns.core.Apple;
import mh.java8.patterns.core.AppleGreenColorPredicate;
import mh.java8.patterns.core.AppleHeavyWeightPredicate;
import mh.java8.patterns.core.ApplePredicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LambdaBefore {

    public static void main(String...args) {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                                              new Apple(150, "green"),
                                              new Apple(120, "red"));

        List<Apple> heavyApples = filterApples(inventory, new AppleHeavyWeightPredicate());

        List<Apple> greenApples = filterApples(inventory, new AppleGreenColorPredicate());

    }

    public static List<Apple> filterApples(List<Apple> apples, ApplePredicate predicate) {
        List<Apple> result = new ArrayList<Apple>();
        for(Apple apple : apples) {
            if(predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

}

package mh.java8.simple.core;

public class AppleGreenColorPredicate implements ApplePredicate {
    public boolean test(final Apple apple) {
        return "green".equals(apple.getColor());
    }
}

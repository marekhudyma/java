package mh.java8.patterns.core;

public class AppleHeavyWeightPredicate implements ApplePredicate {
    public boolean test(final Apple apple) {
        return apple.getWeight() > 150;
    }
}

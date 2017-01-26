package mh.java8.defaultMethods;

public interface HelloA {

    default void hello() {
        System.out.println("Hello from A");
    }
}

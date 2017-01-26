package mh.java8.defaultMethods;

public interface HelloB {

    default void hello() {
        System.out.println("Hello from B");
    }
}

package mh.java8.defaultMethods;

public interface Sized {

    int size();

    default boolean isEmpty() {
        return size() == 0;
    }

    default void remove() {
        throw new UnsupportedOperationException();
    }
}

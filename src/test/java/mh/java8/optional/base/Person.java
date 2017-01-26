package mh.java8.optional.base;

import java.util.Optional;

public class Person {

    private Optional<Car> car;

    public Optional<Car> getCar() {
        return car;
    }

    public void setCar(final Optional<Car> car) {
        this.car = car;
    }
}

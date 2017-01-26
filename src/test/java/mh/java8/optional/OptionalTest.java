package mh.java8.optional;

import mh.java8.optional.base.Car;
import mh.java8.optional.base.Insurance;
import mh.java8.optional.base.Person;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

// Because the Optional class wasn’t intended for use as a field type,
// it also doesn’t implement the Serializable interface. For this reason, using Optionals in your domain model
// could break applications using tools or frameworks that require a serializable model to work.
//
// You can use getters and setters and than write:
// public class Person {
//     private Car car;
//     public Optional<Car> getCarAsOptional() { return Optional.ofNullable(car);
//     }
// }
//
// Guava.Optional implements Serializable
public class OptionalTest {

    @Test
    public void testOptional() throws Exception {
        Integer i = 5;
        Optional<Integer> optional1 = Optional.of(i);
        Optional<Integer> optional2 = Optional.empty();
        Optional<Integer> optional3 = Optional.ofNullable(i);

        Optional<Integer> value = optional1.map(Integer::intValue);
    }

    @Test
    public void testAvoidNulls() throws Exception {
        Optional<Person> person = Optional.ofNullable(new Person());
        {
            Car car = new Car();
            Insurance insurance = new Insurance();
            insurance.setName("InsuranceName");
            car.setInsurance(Optional.ofNullable(insurance));
            person.get().setCar(Optional.ofNullable(car));
        }

        //instead writing
//        Car car = person.getCar();
//        if(car != null) {
//            Insurance insurance = car.getInsurance();
//            if(insurance != null) {
//                String name = insurance.getName();
//                if(name != null) {
//
//                }
//            }
//        }
        assertEquals("InsuranceName", getInsuranceName(person));
    }

    @Test
    public void testAvoidNulls_withNull() throws Exception {
        Optional<Person> person = Optional.ofNullable(new Person());
        {
            Car car = new Car();
            car.setInsurance(Optional.ofNullable(null));
            person.get().setCar(Optional.ofNullable(car));
        }
        assertEquals("Unknown", getInsuranceName(person));
    }

    private String getInsuranceName(Optional<Person> person) {
        String name =
                person.flatMap(Person::getCar)
                      .flatMap(Car::getInsurance)
                      .map(Insurance::getName)
                      .orElse("Unknown");
        return name;
    }

    private Optional<Integer> stringToInt(String str) {
        try {
            return Optional.of(Integer.valueOf(str));
        } catch (NumberFormatException nfe) {
            return Optional.empty();
        }
    }
}

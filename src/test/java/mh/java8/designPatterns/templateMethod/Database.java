package mh.java8.designPatterns.templateMethod;

public class Database {

    public static Customer getCustomerWithId(int id) {
        return new Customer();
    }
}

package mh.java8.designPatterns.factory;

public class ProductFactory {

    public static class Product {

    }

    public static class Loan extends Product {

    }

    public static class Stock extends Product {

    }

    public static class Bond extends Product {

    }


    public static Product createProduct(String name) {
        switch (name) {
            case "loan":
                return new Loan();
            case "stock":
                return new Stock();
            case "bond":
                return new Bond();

            default:
                throw new RuntimeException("No such product " + name);
        }
    }
}

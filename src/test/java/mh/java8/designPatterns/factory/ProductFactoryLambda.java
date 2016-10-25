package mh.java8.designPatterns.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ProductFactoryLambda {

    final static Map<String, Supplier<ProductFactory.Product>> map = new HashMap<>();

    static {
        map.put("loan", ProductFactory.Loan::new);
        map.put("stock", ProductFactory.Stock::new);
        map.put("bond", ProductFactory.Bond::new);
    }

    public static ProductFactory.Product createProduct(String name) {
        Supplier<ProductFactory.Product> p = map.get(name);
        if (p != null) {
            return p.get();
        }
        throw new IllegalArgumentException("No such product " + name);
    }

}

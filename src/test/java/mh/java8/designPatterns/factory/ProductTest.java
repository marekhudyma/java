package mh.java8.designPatterns.factory;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ProductTest {

    @Test
    public void testTraditionalFactory() throws Exception {
        ProductFactory.Product actual = ProductFactory.createProduct("loan");
        assertTrue(actual instanceof ProductFactory.Loan);
    }

    @Test
    public void testLambdaFactory() throws Exception {
        ProductFactory.Product actual = ProductFactoryLambda.createProduct("loan");
        assertTrue(actual instanceof ProductFactory.Loan);
    }
}

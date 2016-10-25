package mh.java8.designPatterns.templateMethod;

import org.junit.Test;

public class TemlateMethodTest {

    @Test
    public void testTradictionalTemplateMethod() throws Exception {
        new OnlineBankingImpl().processCustomer(1);
    }

    @Test
    public void testLambdaTemplateMethod() throws Exception {
        new OnlineBankingLambda().processCustomer(1, (Customer c) -> System.out.println("Customer happy"));
    }

}

package mh.java8.lambda;

import org.junit.Test;

public class EfectiveFinalTest {

    @Test
    public void testEfectiveFinal() throws Exception {
        int variable = 5;
        Runnable r = () -> System.out.println(variable);
        //variable = 6; doesn't work
    }


}

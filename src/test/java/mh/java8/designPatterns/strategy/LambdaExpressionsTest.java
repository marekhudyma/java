package mh.java8.designPatterns.strategy;

import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class LambdaExpressionsTest {

    @Test
    public void testLambdaNumericValidator() throws Exception {
        Validator numericValidator = new Validator((String s) -> s.matches("\\d+"));
        assertFalse(numericValidator.validate("aaa"));
    }

    @Test
    public void testLambdaLowerCaseValidator() throws Exception {
        Validator lowerCaseValidator = new Validator((String s) -> s.matches("[a-z]+"));
        assertTrue(lowerCaseValidator.validate("bbb"));
    }

    @Test
    public void testTraditionalNumericValidator() throws Exception {
        Validator numericValidator = new Validator(new IsNumeric());
        assertFalse(numericValidator.validate("aaa"));
    }

    @Test
    public void testTraditionakCaseValidator() throws Exception {
        Validator lowerCaseValidator = new Validator(new IsAllLowerCase());
        assertTrue(lowerCaseValidator.validate("bbb"));
    }



}

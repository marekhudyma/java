package mh.java8.designPatterns.chainOfResponsibility;

import org.junit.Test;

import java.util.function.Function;
import java.util.function.UnaryOperator;

import static org.junit.Assert.assertEquals;

public class ChainOfResponsibilityTest {

    @Test
    public void testTraditionalChainOfResponsibility() throws Exception {
        ProcessingObject<String> p1 = new HeaderTextProcessing();
        ProcessingObject<String> p2 = new SpellCheckerProcessing();

        p1.setSuccessor(p2);

        String expected = "From Marek: Java is great";
        String actual = p1.handle("java is great");

        assertEquals(expected, actual);
    }

    @Test
    public void testLambdaChainOfResponsibility() throws Exception {
        UnaryOperator<String> headerProcessing = (String text) -> "From Marek: " + text;
        UnaryOperator<String> spellCheckerProcessing = (String text) -> text.replace("java", "Java");

        Function<String, String> pipeline = headerProcessing.andThen(spellCheckerProcessing);

        String expected = "From Marek: Java is great";
        String actual = pipeline.apply("java is great");

        assertEquals(expected, actual);
    }
}

package mh.java8.designPatterns.chainOfResponsibility;

public class SpellCheckerProcessing extends ProcessingObject<String> {

    @Override
    public String handleWork(String text) {
        return text.replace("java", "Java");
    }
}

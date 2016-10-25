package mh.java8.designPatterns.chainOfResponsibility;

public class HeaderTextProcessing extends ProcessingObject<String> {

    @Override
    public String handleWork(String text) {
        return "From Marek: " + text;
    }
}

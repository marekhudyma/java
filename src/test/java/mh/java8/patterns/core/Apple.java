package mh.java8.patterns.core;

public class Apple {

    private Integer weight;

    private String color;

    public Apple() {
        this(10, "red");
    }

    public Apple(Integer weight) {
        this(weight, "red");
    }

    public Apple(Integer weight, String color) {
        this.weight = weight;
        this.color = color;
    }

    public Integer getWeight() {
        return weight;
    }

    public String getColor() {
        return color;
    }

}

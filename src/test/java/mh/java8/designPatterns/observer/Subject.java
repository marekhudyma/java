package mh.java8.designPatterns.observer;

public interface Subject {

    void registerObserver(Observer o);

    void notifyObservers(String tweet);

}

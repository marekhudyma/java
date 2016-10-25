package mh.java8.designPatterns.observer;

import org.junit.Test;

public class ObserverTest {

    @Test
    public void testTraditionalObserver() throws Exception {
        Feed f = new Feed();
        f.registerObserver(new NYTimes());
        f.registerObserver(new Guardian());
        f.notifyObservers("The queen said her favourite book is Java 8 in Action!");
    }

    @Test
    public void testLambdaObserver() throws Exception {
        Feed f = new Feed();
        f.registerObserver((String tweet) -> {
            if(tweet != null && tweet.contains("money")){
                System.out.println("Breaking news in NY! " + tweet); }
        });
        f.registerObserver((String tweet) -> {
            if(tweet != null && tweet.contains("queen")){
                System.out.println("Yet another news in London... " + tweet); }
        });

    }
}

package mh.generic;

import mh.generic.item.Id;

import java.util.Set;

public class Aggregator {

    private final Sender sender;

    public Aggregator(Sender sender) {
        this.sender = sender;
    }

    public void send(Set<? extends Id> items) {
        sender.send(items);
    }

}

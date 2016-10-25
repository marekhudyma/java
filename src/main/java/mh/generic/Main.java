package mh.generic;

import mh.generic.item.BaseId;

import java.util.HashSet;

public class Main {

    public static void main(String[] args) {
        Sender sender = new Sender();
        Aggregator aggregator = new Aggregator(sender);

        HashSet<BaseId> items = new HashSet<>();
        items.add(new BaseId(1));

        aggregator.send(items);

        //List<Id>
        //? extends Id
        //List<BaseId>

    }
}

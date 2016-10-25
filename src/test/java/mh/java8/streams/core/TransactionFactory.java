package mh.java8.streams.core;

import com.google.common.collect.ImmutableList;

import java.util.List;

public class TransactionFactory {

    public static List<Transaction> create() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        return ImmutableList.of(new Transaction(brian, 2011, 300),
                                new Transaction(raoul, 2012, 1000),
                                new Transaction(raoul, 2011, 400),
                                new Transaction(mario, 2012, 710),
                                new Transaction(mario, 2012, 700),
                                new Transaction(alan, 2012, 950)
        );
    }

}

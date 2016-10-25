package mh.java8.designPatterns.templateMethod;

public class OnlineBankingImpl extends OnlineBanking {

    @Override
    protected void makeCustomerHappy(Customer c) {
        System.out.println("Customer happy");
    }
}

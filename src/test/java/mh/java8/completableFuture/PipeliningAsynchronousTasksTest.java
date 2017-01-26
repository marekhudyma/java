package mh.java8.completableFuture;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;
import java.util.Random;

import static java.util.stream.Collectors.toList;

public class PipeliningAsynchronousTasksTest {

    private static enum Code {
        NONE(0),
        SILVER(5),
        GOLD(10),
        PLATINUM(15),
        DIAMOND(20);
        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    private static class Discount {
        private Random random = new Random();

        public String getPrice(String product) {
            double price = calculatePrice(product);
            Code code = Code.values()[random.nextInt(Code.values().length)];
            return String.format("%s:%.2f:%s", product, price, code);
        }

        private double calculatePrice(String product) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return random.nextDouble() * product.charAt(0) + product.charAt(1);
        }
        public static String applyDiscount(Quote quote) {
            return quote.getShopName() + " price is " + Discount.apply(quote.price, quote.getDiscountCode());
        }

        private static double apply(double price, Code code) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return price * (100 - code.percentage) / 100;
        }
    }

    private static class Quote {
        private final String shopName;
        private final double price;
        private final Code discountCode;

        public Quote(String shopName, double price, Code code) { this.shopName = shopName;
            this.price = price;
            this.discountCode = code;
        }
        public static Quote parse(String s) {
            String[] split = s.split(":");
            String shopName = split[0];
            double price = Double.parseDouble(split[1]);
            Code discountCode = Code.valueOf(split[2]);
            return new Quote(shopName, price, discountCode);
        }
        public String getShopName() {
            return shopName;
        }
        public double getPrice() {
            return price;
        }
        public Code getDiscountCode() {
            return discountCode;
        }
    }

    private static class Shop {
        private final String name;
        public Shop(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
        public double getPrice() {
            return 1.1 * name.length();
        }
    }

    private List<Discount> shops = ImmutableList.of(new Discount(), new Discount(), new Discount());

    public List<String> findPrices(String product) {
        return shops.stream()
                .map(shop -> shop.getPrice(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(toList());

        // we can use parallel stream (but it will be slow because it will use
        // Runtime.getRuntime().availableProcessors() == 4 for me
        //
        // use executors !
    }

    @Test
    public void testPipeline() throws Exception {
        findPrices("abc").stream()
                         .forEach(System.out::println);
    }

    @Test
    public void testCombiningTwoIndependentCompletableFutures() {
//        Future<Double> futurePricesInUSD =
//                CompletableFuture.supplyAsync(() -> shop.getPrice(product))
//                .thenCombine(
//                        CompletableFuture.supplyAsync(
//                                () -> exchangeService.getRate(Money.EUR, Money.USD),
//                                (price, rate) -> price * rate
//                        ));
// you can use thenCombineAsync
    }
}

package mh.java8.completableFuture;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static java.util.stream.Collectors.toList;

public class CompletableFutureTest {

    @Test
    public void testCompletableFuture() throws Exception {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Double> future =  executor.submit(new Callable<Double>(){
            @Override
            public Double call() throws Exception {
                return 5.0; //slow operation
            }
        });
        //some other operations

        try {
            future.get(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }

    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread( () -> {
            try {
                double price = 5.0; //slow operation
                futurePrice.complete(price);
            } catch (Exception e) {
                futurePrice.completeExceptionally(e);
            }
        }).start();
        return futurePrice;
    }

    @Test
    public void testAsync() throws Exception {
        Future<Double> future = getPriceAsync("abc");
        //do something else
        try {
            double price = future.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
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

    @Test
    public void testCompletableFutureStream() throws Exception {
        List<Shop> shops = ImmutableList.of(new Shop("ShopAAA"), new Shop("ShopBB"), new Shop("ShopC"));

        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                     .map(shop -> CompletableFuture.supplyAsync(
                             () -> String.format("%s price is %.2f", shop.getName(), shop.getPrice())))
                     .collect(toList());

        priceFutures.stream()
                    .map(CompletableFuture::join)
                    .collect(toList())
                    .forEach(System.out::println);
    }

    @Test
    public void testExecutor() throws Exception {
        List<Shop> shops = ImmutableList.of(new Shop("ShopAAA"), new Shop("ShopBB"), new Shop("ShopC"));

        Executor executor = Executors.newFixedThreadPool(
                Math.min(shops.size(), 100),
                new ThreadFactory() {
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread();
                        t.setDaemon(true);
                        return t;
                    }
                });


        shops.stream()
             .map(shop -> CompletableFuture.supplyAsync(() -> shop.getName() + " price is " + shop.getPrice(),
                                                        executor))
             .collect(toList());

    }
}

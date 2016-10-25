package mh.java8.goodPractice;

import org.junit.Test;

import java.util.function.Supplier;

public class LoggerTest {

    private  enum Level {DEBUG, INFO, ERROR};

    private static class Logger {
        private Level level = Level.DEBUG;

        public void log(Level level, Supplier<String> msgSupplier) {
            if(this.level.equals(level)) {
               msgSupplier.get();
            }
        }

        private void log(String message) {
            System.out.println(message);
        }
    }

    @Test
    public void testLogging() throws Exception {
        new Logger().log(Level.DEBUG, () -> "a" + "b");

    }
}

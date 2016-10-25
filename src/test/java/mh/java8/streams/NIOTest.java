package mh.java8.streams;

import org.junit.Test;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class NIOTest {

    @Test
    public void testNIO() throws Exception {
        long uniqueWords = 0;
        try(Stream<String> lines = Files.lines(
                Paths.get(Thread.currentThread().getContextClassLoader().getResource("data.txt").toURI()),
                Charset.defaultCharset())) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
        }
        System.out.println(uniqueWords);
    }
}

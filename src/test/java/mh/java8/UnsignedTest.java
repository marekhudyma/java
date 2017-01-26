package mh.java8;

import org.junit.Test;

import java.io.File;
import java.nio.file.Files;

public class UnsignedTest {

    @Test
    public void testUnsigned() throws Exception {
        long value = Long.parseUnsignedLong("18446744073709551615");
        System.out.println(value); // -1
        System.out.println(Long.toUnsignedString(value)); // 18446744073709551615
    }

    @Test
    public void testFiles() throws Exception {

        //Files.walk("/home", 10, FileVisitOption.FOLLOW_LINKS);


        Files.walk(new File(".").toPath())
             .filter(p -> !p.getFileName()
                            .toString().startsWith("."))
             .forEach(System.out::println);

    }
}

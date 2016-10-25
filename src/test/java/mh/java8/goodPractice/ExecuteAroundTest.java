package mh.java8.goodPractice;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExecuteAroundTest {


    @Test
    public void testExecuteAround() throws Exception {
        String oneLine = processFile((BufferedReader b) -> b.readLine());

        String twoLines = processFile((BufferedReader b) -> b.readLine() + b.readLine());

    }

    private static String processFile(BufferedReaderProcessor p) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return p.process(br);
        }
    }

    public interface BufferedReaderProcessor {
        String process(BufferedReader b) throws IOException;
    }
}

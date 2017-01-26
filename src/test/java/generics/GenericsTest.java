package generics;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GenericsTest {

    // the runtime representation of ArrayList<String> and ArrayList<Integer> are identical
    // this is called "erasure model of generic polymorphism"
    @Test(expected = ClassCastException.class)
    public void testGenericPolymorphism() throws Exception {
        List<Integer> integers = new ArrayList<Integer>();
        integers.add(1);
        integers.add(2);

        List<String> strings = new ArrayList<String>();
        strings.add("a");
        strings.add("b");

        integers = (List)strings; //OH NO !!!

        for (Object i : integers) {
            System.out.println(i);
        }
        //a,b

        for (Integer i : integers) { //java.lang.ClassCastException: java.lang.String cannot be cast to java.lang.Integer
            System.out.println(i);
        }
    }

    //TODO HUDYMA "extends ?"


}

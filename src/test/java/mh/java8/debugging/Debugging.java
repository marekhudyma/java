package mh.java8.debugging;

import java.util.Arrays;
import java.util.List;

public class Debugging {

//    public static void main(String[] args) {
//        List<Point> points = Arrays.asList(new Point(12, 2), null);
//        points.stream().map(p -> p.getX()).forEach(System.out::println);
//    }
//Exception in thread "main" java.lang.NullPointerException
//        at mh.java8.Debugging.lambda$main$0(Debugging.java:11)  <---- main$0
//        at mh.java8.Debugging$$Lambda$1/363771819.apply(Unknown Source)
//        at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:193)
//        12.0
//        at java.util.Spliterators$ArraySpliterator.forEachRemaining(Spliterators.java:948)
//        at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:512)
//        at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:502)
//        at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
//        at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
//        at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
//        at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:418)
//        at mh.java8.Debugging.main(Debugging.java:11)
//        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
//        at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
//        at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
//        at java.lang.reflect.Method.invoke(Method.java:497)
//        at com.intellij.rt.execution.application.AppMain.main(AppMain.java:140)


    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        numbers.stream().map(Debugging::divideByZero).forEach(System.out::println);
    }

    public static int divideByZero(int n) {
        return n / 0;
    }

//    Exception in thread "main" java.lang.ArithmeticException: / by zero
//    at mh.java8.Debugging.divideByZero(Debugging.java:38)  <------------------------------
//    at mh.java8.Debugging$$Lambda$1/1531448569.apply(Unknown Source)
//    at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:193)
//    at java.util.Spliterators$ArraySpliterator.forEachRemaining(Spliterators.java:948)
//    at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:512)
//    at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:502)
//    at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
//    at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
//    at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
//    at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:418)
//    at mh.java8.Debugging.main(Debugging.java:34)
//    at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
//    at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
//    at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
//    at java.lang.reflect.Method.invoke(Method.java:497)
//    at com.intellij.rt.execution.application.AppMain.main(AppMain.java:140)
}


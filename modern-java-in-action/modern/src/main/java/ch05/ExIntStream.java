package ch05;

import java.util.stream.IntStream;

public class ExIntStream {
    public static void main(String[] args) {
        IntStream evenNumberWithRangeClosed = IntStream.rangeClosed(1, 100).filter(n -> n % 2 == 0);
        System.out.println(evenNumberWithRangeClosed.count());
        IntStream evenNumbersWithRange = IntStream.range(1, 100).filter(n -> n % 2 == 0);
        System.out.println(evenNumbersWithRange.count());
    }
}

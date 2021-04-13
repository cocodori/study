package ch05;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MakeStream {
    public static void main(String[] args) {
        //스트림 만들기
        Stream<String> stream = Stream.of("Mordern", "Java", "In", "Action");
        stream.map(String::toUpperCase)
                .forEach(System.out::println);

        //null이 될 수 있는 객체로 스트림 만들기
        //1.기존 방식
        String homeValue = System.getProperty("home");
        Stream<String> homeValueStream =
                homeValue == null ? Stream.empty() : Stream.of(homeValue);
        //ofNullable을 사용하는 방식
        Stream<String> homeValueStream2 =
                Stream.ofNullable(System.getProperty("home"));

        //배열 합 스트림
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int sum = Arrays.stream(numbers).sum();
        System.out.println("sum : " + sum);

        //고유한 단어 찾기
        long uniqueWordrs = 0;
        try(Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())) {
            uniqueWordrs = lines.flatMap(line -> Arrays.stream(line.split("")))
                    .distinct()
                    .count();
        } catch (IOException e) {

        }

        System.out.println("--------------------iterate 사용-----------------------");
        //무한 스트림
        Stream.iterate(0, n -> n+2)
                .limit(10)
                .forEach(System.out::println);

        //피보나치 수열
        Stream.iterate(new int[]{0, 1},
                t -> new int[]{t[1], t[0] + t[1]})
                .limit(20)
                .forEach(t -> System.out.println("( " + t[0] + ", " + "t[1] : " + t[1] + ")"));

        //Predicate사용
        IntStream.iterate(0, n -> n <100, n -> n+4)
                .forEach(System.out::println);
        //takeWhile로 대체
        IntStream.iterate(0, n -> n + 4)
                .takeWhile(n -> n < 100)
                .forEach(System.out::println);

        System.out.println("--------------------generate사용-----------------------");
        //generate사용
        Stream.generate(Math::random)
                .limit(100)
                .forEach(System.out::println);

        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;
            @Override
            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };
        IntStream.generate(fib).limit(10).forEach(System.out::println);
    }
}

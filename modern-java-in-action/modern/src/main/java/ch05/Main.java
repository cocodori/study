package ch05;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
    /*TODO
        [5.6] 실전연습 요구사항
        1. 2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정리하시오.
        2. 거래자가 근무하는 모든 도시를 중복없이 나열하시오.
        3. 케임브리지에서 근무하는 모든 거래자를 찾아서 이름 순으로 정렬하시오.
        4. 모든 거래자의 이름을 알파벳 순으로 정렬해서 반환하시오.
        5. 밀라노에 거래자가 있는가?
        6. 케임브리지에 거주하는 거래자의 모든 트랜잭션 값을 출력하시오.
        7. 전체 트랜잭션 중 최댓값은 얼마인가?
        8. 전체 트랜잭션 중 최솟값은 얼마인가?
        */
public class Main {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(brian, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2011, 700),
                new Transaction(alan, 2011, 950));

        /*
        *   1. 2011년에 일어난 모든 트랜잭션을 찾아서 값을 오름차순으로 정렬하시오.
        * */
        List<Transaction> transactions2011 =
                transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());

        System.out.println("=========2011년에 일어난 모든 트랜잭션===========");
        System.out.println(transactions2011);

        /*
         *   2. 거래자가 근무하는 모든 도시를 중복없이 나열하시오.
         * */
        List<String> cities =
                transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());

        System.out.println("============거래자가 근무하는 모든 도시===============");
        System.out.println(cities);

        /*
        *   3.케임브리지에서 근무하는 모든 거래자를 찾아서 이름 순으로 정렬하시오.
        * */
        List<Trader> traders =
                transactions.stream()
                .map(Transaction :: getTrader)
                .filter(trader -> trader.getCity().equalsIgnoreCase("cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());

        System.out.println("============케임브리지에서 근무하는 트레이더===============");
        System.out.println(traders);

        /*
        *   4.모든 거래자의 이름을 알파벳 순으로 정렬
        * */
        String tradersName =
                transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted().reduce("", (n1, n2) -> n1 + n2);

        System.out.println("========정렬된 트레이더들의 이름==========");
        System.out.println(tradersName);

        /*
        *   5. 밀라노에 트레이더가 있는가?
        * */
        boolean milanBased =
                transactions.stream()
                .anyMatch(transaction ->
                        transaction.getTrader()
                                .getCity()
                                .equalsIgnoreCase("milan"));

        System.out.println("==================================");
        System.out.println("밀라노에 트레이더가 있는가? " + milanBased);


        /*
        *  6. 케임브리지에 거주하는 거래자의 모든 트랙잭션 값을 출력
        * */
        System.out.println("======케임브리지에 거주하는 거래자의 모든 트랜잭션=======");
        transactions.stream()
                .filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        /*
        *  7. 전체 트랜잭션 중 최댓값은 얼마인가?
        * */
        Optional<Integer> highestValue =
                transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);

        System.out.println("===============최댓값====================");
        System.out.println(highestValue);

        /*
        *   8. 전체 트랜잭션 중 최솟값
        * */
        Optional<Integer> lowestValue =
                transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::min);

        System.out.println("===============최솟값====================");
        System.out.println(lowestValue);


    }
}

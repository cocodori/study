package ch02;

import java.time.Duration;

public class Client {
    public static void main(String[] args) {
        Movie avatar = new Movie("아바타",
                        Duration.ofMinutes(20),
                        Money.wons(10000),
                        new AmountDiscountPolicy(Money.wons(800)));

    }
}

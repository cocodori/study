package ch02;

public interface DiscountPolicy {
    Money calculateDiscountAmount(Screening screening);
}

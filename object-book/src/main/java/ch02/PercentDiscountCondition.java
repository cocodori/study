package ch02;

public class PercentDiscountCondition extends DiscountPolicy {
    private double percent;

    public PercentDiscountCondition(double percent, DiscountCondition... conditions) {
        super(conditions);
        this.percent = percent;
    }

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return screening.getMovieFee().times(percent);
    }
}

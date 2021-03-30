package ch02;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public class Screening {
    private Movie movie;
    private int sequence; //순번
    private LocalDateTime whenScreened; //시작 시간

    public Screening(Movie moive, int sequence, LocalDateTime whenScreened) {
        this.movie = movie;
        this.sequence = sequence;
        this.whenScreened = whenScreened;
    }

    public LocalDateTime getWhenScreened() {
        return whenScreened;
    }

    public boolean isSequence(int sequence) {
        return this.sequence == sequence;
    }

    public Money getMovieFee() {
        return movie.getFee();
    }

    public Reservation reserve(Customer customer, int audienceCount) {
        return new Reservation(customer, this, calculateFee(audienceCount), audienceCount);
    }

    private Money calculateFee(int audienceCount) {
        return movie.calculateMovieFee(this).times(audienceCount);
    }

    //TODO : 책에 없는 구현부
    public OffsetDateTime getStartTime() {
        return null;
    }
}

package ch01;

public class Audience {
    private Bag bag;

    public Audience(Bag bag) {
        this.bag = bag;
    }

//    public Bag getBag() {
//        return bag;
//    }

    //외부에 공개할 인터페이스
    public Long buy(Ticket ticket) {
        return bag.hold(ticket);
    }
}
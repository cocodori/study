package ch01;

public class Bag {
    private Long amount;
    private Invitation invitation;
    private Ticket ticket;

    /*
     * Bag인스턴스는 초대장과 현금을 함께 보유하거나,
     * 초대장 없이 현금만 보관하는 두 가지 상태만 존재한다.
     * 따라서 생성자를 통해 이를 강제한다.
     **/
    public Bag(long amount) {
        this(null, amount);
    }

    public Bag(Invitation invitation, long amount) {
        this.invitation = invitation;
        this.amount = amount;
    }

    public boolean hasInvitation() {
        return invitation != null;
    }

    public boolean hasTicket() {
        return ticket != null;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public void minusAmount(Long amount) {
        this.amount -= amount;
    }

    public void plusAmount(Long amount) {
        this.amount += amount;
    }
}

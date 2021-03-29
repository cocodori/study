package ch01;

public class TicketSeller {
    private TicketOffice ticketOffice;

    public TicketSeller(TicketOffice ticketOffice) {
        this.ticketOffice = ticketOffice;
    }

//    자신이 TicketOffice를 포함하고 있다는 것을 숨겨야 한다.
//    public TicketOffice getTicketOffice() {
//        return ticketOffice;
//    }

    //인터페이스
    public void sellTo(Audience audience) {
        ticketOffice.sellTicketTo(audience);
    }
}
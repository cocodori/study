package ch01;

public class Theater {
    private TicketSeller ticketSeller;

    public Theater(TicketSeller ticketSeller) {
        this.ticketSeller = ticketSeller;
    }

    public void enter(Audience audience) {
        //관객의 가방을 열어서 초대장이 있는 지 살펴본다.
        if(audience.getBag().hasInvitation()) {
            //티켓 셀러가 티켓 오피스에서 티켓을 하나 꺼낸 뒤,
            //초대장을 받은 관객의 가방을 열고 티켓을 넣어준다.
            Ticket ticket = ticketSeller.getTicketOffice().getTicket();
            audience.getBag().setTicket(ticket);
        } else {
            //티켓 셀러가 티켓 오피스에서 티켓을 하나 꺼낸다.
            Ticket ticket = ticketSeller.getTicketOffice().getTicket();
            //관객의 가방을 열고 돈을 티켓 값을 빼온다.
            audience.getBag().minusAmount(ticket.getFee());
            //티켓셀러가 티켓오피스에 관객에게 받은 돈을 넣는다.
            ticketSeller.getTicketOffice().plusAmount(ticket.getFee());
            //다시 관객의 가방을 열고 티켓을 넣어준다/
            audience.getBag().setTicket(ticket);
        }
    }
}
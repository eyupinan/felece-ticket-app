package org.felecechallenge.ticket.facade.dto.ticket;

import java.util.List;

public class UserTicketTransferData {
    private List<UserTicketData> ticketData;
    private Integer pageCount;

    public List<UserTicketData> getTicketData() {
        return ticketData;
    }

    public void setTicketData(List<UserTicketData> ticketData) {
        this.ticketData = ticketData;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }
}

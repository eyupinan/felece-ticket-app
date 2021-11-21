package org.felecechallenge.ticket.facade.dto.ticket;

import java.util.List;

public class AdminTicketTransferData {
    private List<AdminTicketData> ticketData;
    private Integer pageCount;

    public List<AdminTicketData> getTicketData() {
        return ticketData;
    }

    public void setTicketData(List<AdminTicketData> ticketData) {
        this.ticketData = ticketData;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }
}

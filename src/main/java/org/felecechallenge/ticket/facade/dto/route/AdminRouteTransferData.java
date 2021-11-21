package org.felecechallenge.ticket.facade.dto.route;

import java.util.List;

public class AdminRouteTransferData {
    private List<AdminRouteData> routeData;
    private Integer pageCount;

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public List<AdminRouteData> getRouteData() {
        return routeData;
    }

    public void setRouteData(List<AdminRouteData> routeData) {
        this.routeData = routeData;
    }
}

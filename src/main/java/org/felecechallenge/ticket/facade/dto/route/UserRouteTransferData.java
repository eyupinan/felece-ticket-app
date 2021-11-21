package org.felecechallenge.ticket.facade.dto.route;

import java.util.List;

public class UserRouteTransferData {
    private List<UserRouteData> routeData;
    private Integer pageCount;

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public List<UserRouteData> getRouteData() {
        return routeData;
    }

    public void setRouteData(List<UserRouteData> routeData) {
        this.routeData = routeData;
    }
}

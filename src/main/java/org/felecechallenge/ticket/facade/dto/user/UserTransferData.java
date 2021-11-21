package org.felecechallenge.ticket.facade.dto.user;

import java.util.List;

public class UserTransferData {
    private List<UserData> userData;
    private Integer pageCount;

    public List<UserData> getUserData() {
        return userData;
    }

    public void setUserData(List<UserData> userData) {
        this.userData = userData;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }
}

package org.felecechallenge.ticket.facade.populator.user;

import org.felecechallenge.ticket.facade.dto.user.UserData;
import org.felecechallenge.ticket.facade.populator.Populator;
import org.felecechallenge.ticket.model.User;

public class UserAddressPopulator implements Populator<User, UserData> {
    @Override
    public void populate(User user, UserData userData) {
        userData.setAddress(user.getAddress());
    }
}

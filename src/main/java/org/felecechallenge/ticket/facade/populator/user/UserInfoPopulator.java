package org.felecechallenge.ticket.facade.populator.user;

import org.felecechallenge.ticket.facade.dto.user.UserData;
import org.felecechallenge.ticket.facade.populator.Populator;
import org.felecechallenge.ticket.model.User;

public class UserInfoPopulator implements Populator<User, UserData> {
    @Override
    public void populate(User user, UserData userData) {
        userData.setId(user.getId());
        userData.setEmail(user.getEmail());
        userData.setPhone(user.getPhone());
        userData.setRole(user.getRole());
        userData.setCreatedAt(user.getCreatedAt());
        userData.setUpdatedAt(user.getUpdatedAt());
    }
}

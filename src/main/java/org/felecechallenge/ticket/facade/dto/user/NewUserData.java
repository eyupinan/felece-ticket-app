package org.felecechallenge.ticket.facade.dto.user;

import org.felecechallenge.ticket.enums.Roles;

import javax.validation.constraints.NotNull;

public class NewUserData extends UserData{
    @NotNull
    private String password;

    @Override
    public Roles getRole() {
        return super.getRole();
    }

    @Override
    public void setRole(@NotNull Roles role) {
        super.setRole(role);
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(@NotNull Long id) {
        super.setId(id);
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public void setUsername(@NotNull String username) {
        super.setUsername(username);
    }

    @Override
    public Long getPhone() {
        return super.getPhone();
    }

    @Override
    public void setPhone(@NotNull Long phone) {
        super.setPhone(phone);
    }

    @Override
    public String getAddress() {
        return super.getAddress();
    }

    @Override
    public void setAddress(@NotNull String address) {
        super.setAddress(address);
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public void setEmail(@NotNull String email) {
        super.setEmail(email);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

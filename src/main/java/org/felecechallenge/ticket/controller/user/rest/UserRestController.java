package org.felecechallenge.ticket.controller.user.rest;

import org.felecechallenge.ticket.enums.Roles;
import org.felecechallenge.ticket.exception.ConflictException;
import org.felecechallenge.ticket.exception.ForbiddenException;
import org.felecechallenge.ticket.exception.NotFoundException;
import org.felecechallenge.ticket.facade.UserFacade;
import org.felecechallenge.ticket.facade.dto.ticket.TicketPurchaseData;
import org.felecechallenge.ticket.facade.dto.user.NewUserData;
import org.felecechallenge.ticket.facade.dto.user.UserData;
import org.felecechallenge.ticket.facade.dto.user.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping(value="/api/user")
public class UserRestController {
    @Autowired
    private UserFacade userFacade;
    @GetMapping(value="/username/{username}")
    public UserData perform(Authentication auth, @PathVariable(value="username") String username){
        if (auth==null){
            throw new ForbiddenException();
        }
        if (!username.equals(auth.getName())){
            throw new ForbiddenException();
        }
        try{
            return userFacade.getUserDataByUserName(username);
        }
        catch(Exception e){
            throw new NotFoundException();
        }

    }
    @PostMapping(value="/create",consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public RedirectView createUserJson(NewUserData data){
        data.setRole(Roles.ROLE_USER);
        try{
            userFacade.saveUser(data);
        }
        catch (ConflictException e){
            return  new RedirectView("/register?err=true");
        }

        return new RedirectView("/login");
    }
    @PutMapping(value="/update/{id}",consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public void updateUserJson(Authentication auth ,NewUserData data,@PathVariable Long id){
        UserPrincipal principal = (UserPrincipal) auth.getPrincipal();
        if (principal.getUser().getId()==id){
            data.setRole(Roles.ROLE_USER);
            userFacade.updateUser(id,data);
        }
        else{
            throw new ForbiddenException();
        }

    }
    @DeleteMapping(value="/delete/{id}")
    public void deleteUser(Authentication auth ,@PathVariable Long id){
        UserPrincipal principal = (UserPrincipal) auth.getPrincipal();
        if (principal.getUser().getId()==id){
            userFacade.disableUser(id);
        }
        else{
            throw new ForbiddenException();
        }

    }

}

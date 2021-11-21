package org.felecechallenge.ticket.controller.admin.rest;

import org.felecechallenge.ticket.exception.BadRequestException;
import org.felecechallenge.ticket.facade.UserFacade;
import org.felecechallenge.ticket.facade.dto.user.NewUserData;
import org.felecechallenge.ticket.facade.dto.user.UserData;
import org.felecechallenge.ticket.facade.dto.user.UserTransferData;
import org.felecechallenge.ticket.filter.UserFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/admin/api/user")
public class AdminUserRestController {
    @Autowired
    private UserFacade userFacade;

    @GetMapping
    public UserTransferData getByFilter(UserFilter filter){
        return userFacade.getUsersWithFilter(filter);
    }
    @GetMapping(value = "/{id}")
    public UserData getById(@PathVariable Long id){
        return userFacade.getUserDataById(id);
    }
    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public void createUserFormData(NewUserData data){
        userFacade.saveUser(data);
    }
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void createUserJson(@RequestBody NewUserData data){
            userFacade.saveUser(data);
    }

    @PutMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void updateUser(NewUserData data){
        if (data.getId()==null){
            throw new BadRequestException();
        }
        userFacade.updateUser(null,data);
    }
    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void updateUserById(NewUserData data,@PathVariable Long id){
        userFacade.updateUser(id,data);
    }
    @DeleteMapping(value = "/{username}")
    public void updateUserByPath(@PathVariable String username){
        userFacade.deleteUser(username);
    }
    @DeleteMapping
    public void updateUserByParam(@RequestParam(value = "username",required = true) String username){
        userFacade.deleteUser(username);
    }

}

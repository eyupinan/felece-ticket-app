package org.felecechallenge.ticket.controller;

import org.felecechallenge.ticket.enums.Roles;
import org.felecechallenge.ticket.facade.UserFacade;
import org.felecechallenge.ticket.facade.dto.user.UserData;
import org.felecechallenge.ticket.facade.dto.user.UserPrincipal;
import org.felecechallenge.ticket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserFacade userFacade;
    @GetMapping("/login")
    public ModelAndView login(Model model,@RequestParam(required = false) String err) {
        model.addAttribute("login_path","/perform_login");
        return new ModelAndView("login/login");
    }
    @GetMapping("/register")
    public ModelAndView register(Model model,@RequestParam(required = false) String err) {
        model.addAttribute("user_create_path","/api/user/create");
        return new ModelAndView("login/register");
    }
    @GetMapping("/profile")
    public ModelAndView redirectWithId(Authentication auth, Model model){
        UserPrincipal principal = (UserPrincipal) auth.getPrincipal();
        UserData data = this.userFacade.getUserDataById(principal.getUser().getId());
        model.addAttribute("data",data);
        if (data.getRole()== Roles.ROLE_ADMIN){
            model.addAttribute("update_path","/admin/api/user/"+data.getId());
            model.addAttribute("leftbar_path","/template/admin_leftbar.html");
            model.addAttribute("disable_path","/admin/api/user/"+data.getId());
            model.addAttribute("route_after_disable","/perform_logout");
        }
        else{
            model.addAttribute("leftbar_path","/template/user_leftbar.html");
            model.addAttribute("update_path","/api/user/update/"+data.getId());
            model.addAttribute("disable_path","/api/user/delete/"+data.getId());
            model.addAttribute("route_after_disable","/perform_logout");
        }

        return new ModelAndView("/general/user_details");
    }
}

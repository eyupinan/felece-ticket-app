package org.felecechallenge.ticket.controller.user.view;

import org.felecechallenge.ticket.enums.Roles;
import org.felecechallenge.ticket.facade.UserFacade;
import org.felecechallenge.ticket.facade.dto.user.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserControllers {
    @Autowired
    private UserFacade userFacade;
    @GetMapping("/routes")
    public ModelAndView getRoutesPage(Authentication auth, Model model){
        if (auth!=null){
            UserPrincipal principal = (UserPrincipal) auth.getPrincipal();
            if (principal.getUser().getRole()== Roles.ROLE_ADMIN){
                return new ModelAndView("redirect:/admin/routes");
            }
            model.addAttribute("userName",auth.getName());
            model.addAttribute("leftbar_path","/template/user_leftbar.html");
        }
        else{

            model.addAttribute("userName",null);
            model.addAttribute("leftbar_path","/template/anonymous_leftbar.html");
        }
        model.addAttribute("route_path","/api/route");
        model.addAttribute("reservation_path","/api/ticket/reservation");
        model.addAttribute("destination_path","/api/destination");
        model.addAttribute("table","../user/route/route_table.jsp");
        model.addAttribute("search_tab","../user/route/route_search_tab.jsp");
        model.addAttribute("external_js","user/user_route.js");
        model.addAttribute("action_button_label","reservation");

        return new ModelAndView("general/route");
    }
    @GetMapping("/tickets")
    public ModelAndView getTicketsPage(Authentication auth, Model model){
        if (auth!=null){
            UserPrincipal principal = (UserPrincipal) auth.getPrincipal();
            if (principal.getUser().getRole()== Roles.ROLE_ADMIN){
                return new ModelAndView("redirect:/admin/tickets");
            }
        }
        model.addAttribute("leftbar_path","/template/user_leftbar.html");
        model.addAttribute("ticket_path","/api/ticket");
        model.addAttribute("route_path","/api/route");
        model.addAttribute("destination_path","/api/destination");
        model.addAttribute("update_path","/api/ticket/update");

        model.addAttribute("table","../user/ticket/ticket_table.jsp");
        model.addAttribute("search_tab","../user/ticket/ticket_search_tab.jsp");
        return new ModelAndView("/general/ticket");
    }

}

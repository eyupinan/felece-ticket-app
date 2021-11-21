package org.felecechallenge.ticket.controller.user.view;

import org.felecechallenge.ticket.enums.Roles;
import org.felecechallenge.ticket.exception.ForbiddenException;
import org.felecechallenge.ticket.facade.UserFacade;
import org.felecechallenge.ticket.facade.dto.user.UserData;
import org.felecechallenge.ticket.facade.dto.user.UserPrincipal;
import org.felecechallenge.ticket.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserControllers {
    @Autowired
    private UrlService urlService;
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
            model.addAttribute("leftbar_url",this.urlService.getUrl("/template/user_leftbar.html"));
        }
        else{

            model.addAttribute("userName",null);
            model.addAttribute("leftbar_url",this.urlService.getUrl("/template/anonymous_leftbar.html"));
        }
        model.addAttribute("base_url",this.urlService.getUrl("/api/route"));
        model.addAttribute("reservation_url",this.urlService.getUrl("/api/ticket/reservation"));
        model.addAttribute("destination_url",this.urlService.getUrl("/api/destination"));
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
        model.addAttribute("leftbar_url",this.urlService.getUrl("/template/user_leftbar.html"));
        model.addAttribute("base_url",this.urlService.getUrl("/api/ticket"));
        model.addAttribute("route_url",this.urlService.getUrl("/api/route"));
        model.addAttribute("destination_url",this.urlService.getUrl("/api/destination"));
        model.addAttribute("update_url",this.urlService.getUrl("/api/ticket/update"));

        model.addAttribute("table","../user/ticket/ticket_table.jsp");
        model.addAttribute("search_tab","../user/ticket/ticket_search_tab.jsp");
        return new ModelAndView("/general/ticket");
    }

}

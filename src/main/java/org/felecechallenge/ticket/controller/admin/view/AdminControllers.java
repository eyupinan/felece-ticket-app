package org.felecechallenge.ticket.controller.admin.view;

import org.felecechallenge.ticket.exception.NotFoundException;
import org.felecechallenge.ticket.facade.RouteFacade;
import org.felecechallenge.ticket.facade.UserFacade;
import org.felecechallenge.ticket.facade.dto.route.AdminRouteData;
import org.felecechallenge.ticket.facade.dto.user.UserData;
import org.felecechallenge.ticket.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/admin")
public class AdminControllers {
    @Autowired
    private RouteFacade routeFacade;
    @Autowired
    private UserFacade userFacade;
    @Autowired
    private UrlService urlService;
    @GetMapping("/settings")
    public ModelAndView getSettingsPage(Model model){
        model.addAttribute("leftbar_url",this.urlService.getUrl("/template/admin_leftbar.html"));
        model.addAttribute("base_url",this.urlService.getUrl("/admin/api"));
        model.addAttribute("destination_url","/admin/api/destination");
        model.addAttribute("vehicle_url",this.urlService.getUrl("/admin/api/vehicle"));
        return new ModelAndView("general/settings");
    }
    @GetMapping("/routes")
    public ModelAndView getRoutesPage(Model model){
        model.addAttribute("leftbar_url",this.urlService.getUrl("/template/admin_leftbar.html"));
        model.addAttribute("base_url",this.urlService.getUrl("/admin/api/route"));
        model.addAttribute("create_route_url",this.urlService.getUrl("/admin/route/create"));
        model.addAttribute("userName",null);
        model.addAttribute("reservation_url",null);
        model.addAttribute("destination_url",this.urlService.getUrl("/admin/api/destination"));
        model.addAttribute("table","../admin/route/route_table.jsp");
        model.addAttribute("search_tab","../admin/route/route_search_tab.jsp");
        model.addAttribute("external_js","admin/admin_route.js");
        model.addAttribute("action_button_label","Details");
        return new ModelAndView("general/route");
    }
    @GetMapping("/route/create")
    public ModelAndView getRouteCreatePage(Model model){
        model.addAttribute("leftbar_url",this.urlService.getUrl("/template/admin_leftbar.html"));
        model.addAttribute("post_url",this.urlService.getUrl("/admin/api/route"));
        model.addAttribute("destination_url",this.urlService.getUrl("/admin/api/destination"));
        return new ModelAndView("admin/route/create_route");
    }
    @GetMapping("/route/{id}")
    public ModelAndView getRoute(Model model, @PathVariable Long id){
        AdminRouteData data = this.routeFacade.getByIdforAdmin(id);
        model.addAttribute("data",data);
        model.addAttribute("leftbar_url",this.urlService.getUrl("/template/admin_leftbar.html"));
        model.addAttribute("update_url",this.urlService.getUrl("/admin/api/route/"+id));
        model.addAttribute("destination_url",this.urlService.getUrl("/admin/api/destination"));
        return new ModelAndView("admin/route/route_details");
    }
    @GetMapping("/tickets")
    public ModelAndView getTicketsPage(Model model){
        model.addAttribute("leftbar_url",this.urlService.getUrl("/template/admin_leftbar.html"));
        model.addAttribute("base_url",this.urlService.getUrl("/admin/api/ticket"));
        model.addAttribute("route_url",this.urlService.getUrl("/admin/api/route"));
        model.addAttribute("destination_url",this.urlService.getUrl("/admin/api/destination"));
        model.addAttribute("update_url",this.urlService.getUrl("/admin/api/ticket"));
        model.addAttribute("table","../admin/ticket/ticket_table.jsp");
        model.addAttribute("search_tab","../admin/ticket/ticket_search_tab.jsp");
        return new ModelAndView("general/ticket");
    }
    @GetMapping("/users")
    public ModelAndView getUsersPage(Model model){
        model.addAttribute("leftbar_url",this.urlService.getUrl("/template/admin_leftbar.html"));
        model.addAttribute("base_url",this.urlService.getUrl("/admin/api/user"));
        return new ModelAndView("admin/user/user");
    }
    @GetMapping("/users/{id}")
    public ModelAndView getUserDetails(Model model,@PathVariable Long id){
        model.addAttribute("leftbar_url",this.urlService.getUrl("/template/admin_leftbar.html"));
        model.addAttribute("update_url",this.urlService.getUrl("/admin/api/user/"+id));
        try{
            UserData data = this.userFacade.getUserDataById(id);
            model.addAttribute("data",data);
            return new ModelAndView("general/user_details");
        }
        catch (Exception e){
            throw new NotFoundException();
        }
    }
    @GetMapping("/users/create")
    public ModelAndView getCreateUserPage(Model model){
        model.addAttribute("leftbar_url",this.urlService.getUrl("/template/admin_leftbar.html"));
        model.addAttribute("post_url",this.urlService.getUrl("/admin/api/user"));
        return new ModelAndView("admin/user/create_user");
    }
    @GetMapping("/tickets/create")
    public ModelAndView getCreateTicketPage(Model model){
        model.addAttribute("leftbar_url",this.urlService.getUrl("/template/admin_leftbar.html"));
        model.addAttribute("destination_url",this.urlService.getUrl("/admin/api/destination"));
        model.addAttribute("route_url",this.urlService.getUrl("/admin/api/route"));
        return new ModelAndView("admin/ticket/create_ticket");
    }
}

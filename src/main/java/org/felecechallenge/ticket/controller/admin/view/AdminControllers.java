package org.felecechallenge.ticket.controller.admin.view;

import org.felecechallenge.ticket.exception.NotFoundException;
import org.felecechallenge.ticket.facade.RouteFacade;
import org.felecechallenge.ticket.facade.UserFacade;
import org.felecechallenge.ticket.facade.dto.route.AdminRouteData;
import org.felecechallenge.ticket.facade.dto.user.UserData;
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
    @GetMapping("/settings")
    public ModelAndView getSettingsPage(Model model){
        model.addAttribute("leftbar_path","/template/admin_leftbar.html");
        model.addAttribute("destination_path","/admin/api/destination");
        model.addAttribute("vehicle_path","/admin/api/vehicle");
        return new ModelAndView("general/settings");
    }
    @GetMapping("/routes")
    public ModelAndView getRoutesPage(Model model){
        model.addAttribute("leftbar_path","/template/admin_leftbar.html");
        model.addAttribute("route_path","/admin/api/route");
        model.addAttribute("create_route_path","/admin/route/create");
        model.addAttribute("userName",null);
        model.addAttribute("reservation_path",null);
        model.addAttribute("destination_path","/admin/api/destination");
        model.addAttribute("table","../admin/route/route_table.jsp");
        model.addAttribute("search_tab","../admin/route/route_search_tab.jsp");
        model.addAttribute("external_js","admin/admin_route.js");
        model.addAttribute("action_button_label","Details");
        return new ModelAndView("general/route");
    }
    @GetMapping("/route/create")
    public ModelAndView getRouteCreatePage(Model model){
        model.addAttribute("leftbar_path","/template/admin_leftbar.html");
        model.addAttribute("post_path","/admin/api/route");
        model.addAttribute("destination_path","/admin/api/destination");
        model.addAttribute("vehicle_path","/admin/api/vehicle");
        return new ModelAndView("admin/route/route_create");
    }
    @GetMapping("/route/{id}")
    public ModelAndView getRoute(Model model, @PathVariable Long id){
        AdminRouteData data = this.routeFacade.getByIdforAdmin(id);
        model.addAttribute("data",data);
        model.addAttribute("leftbar_path","/template/admin_leftbar.html");
        model.addAttribute("update_path","/admin/api/route/"+id);
        model.addAttribute("destination_path","/admin/api/destination");
        model.addAttribute("route_after_disable","/admin/routes");
        return new ModelAndView("admin/route/route_details");
    }
    @GetMapping("/tickets")
    public ModelAndView getTicketsPage(Model model){
        model.addAttribute("ticket_create_path","/admin/tickets/create");
        model.addAttribute("leftbar_path","/template/admin_leftbar.html");
        model.addAttribute("ticket_path","/admin/api/ticket");
        model.addAttribute("route_path","/admin/api/route");
        model.addAttribute("destination_path","/admin/api/destination");
        model.addAttribute("update_path","/admin/api/ticket");
        model.addAttribute("table","../admin/ticket/ticket_table.jsp");
        model.addAttribute("search_tab","../admin/ticket/ticket_search_tab.jsp");
        return new ModelAndView("general/ticket");
    }
    @GetMapping("/users")
    public ModelAndView getUsersPage(Model model){
        model.addAttribute("user_create_path","/admin/users/create");
        model.addAttribute("leftbar_path","/template/admin_leftbar.html");
        model.addAttribute("user_path","/admin/api/user");
        return new ModelAndView("admin/user/user");
    }
    @GetMapping("/users/{id}")
    public ModelAndView getUserDetails(Model model,@PathVariable Long id){
        model.addAttribute("leftbar_path","/template/admin_leftbar.html");
        model.addAttribute("update_path","/admin/api/user/"+id);
        model.addAttribute("disable_path","/admin/api/user/"+id);
        model.addAttribute("route_after_disable","/admin/users");
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
        model.addAttribute("leftbar_path","/template/admin_leftbar.html");
        model.addAttribute("user_path","/admin/users");
        model.addAttribute("user_post_path","/admin/api/user");
        return new ModelAndView("admin/user/user_create");
    }
    @GetMapping("/tickets/create")
    public ModelAndView getCreateTicketPage(Model model){
        model.addAttribute("leftbar_path","/template/admin_leftbar.html");
        model.addAttribute("destination_path","/admin/api/destination");
        model.addAttribute("route_path","/admin/api/route");
        model.addAttribute("ticket_post_path","/admin/api/ticket");
        return new ModelAndView("admin/ticket/ticket_create");
    }
}

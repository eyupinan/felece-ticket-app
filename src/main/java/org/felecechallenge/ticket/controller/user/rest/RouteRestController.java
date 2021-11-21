package org.felecechallenge.ticket.controller.user.rest;

import org.felecechallenge.ticket.enums.Roles;
import org.felecechallenge.ticket.facade.RouteFacade;
import org.felecechallenge.ticket.facade.dto.route.UserRouteTransferData;
import org.felecechallenge.ticket.facade.dto.user.UserPrincipal;
import org.felecechallenge.ticket.filter.RouteFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class RouteRestController {
    @Autowired
    private RouteFacade routeFacade;

    @GetMapping("/api/route")
    public UserRouteTransferData getRoutes(RouteFilter filter){
        UserRouteTransferData data = routeFacade.getRouteDataforUser(filter);
        return data;
    }
    @GetMapping
    public RedirectView homeUrl(Authentication auth){
        if (auth==null){
            return new RedirectView("/routes");
        }
        UserPrincipal principal = (UserPrincipal) auth.getPrincipal();
        if(principal.getUser().getRole()== Roles.ROLE_ADMIN){
            return new RedirectView("/admin/routes");
        }
        else{
            return new RedirectView("/routes");
        }

    }


}

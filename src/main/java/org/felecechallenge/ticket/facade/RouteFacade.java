package org.felecechallenge.ticket.facade;

import org.felecechallenge.ticket.exception.BadRequestException;
import org.felecechallenge.ticket.exception.NotFoundException;
import org.felecechallenge.ticket.facade.converter.Converter;
import org.felecechallenge.ticket.facade.dto.route.AdminRouteData;
import org.felecechallenge.ticket.facade.dto.route.AdminRouteTransferData;
import org.felecechallenge.ticket.facade.dto.route.UserRouteData;
import org.felecechallenge.ticket.facade.dto.route.UserRouteTransferData;
import org.felecechallenge.ticket.facade.populator.route.RouteAdminDataPopulator;
import org.felecechallenge.ticket.facade.populator.route.RouteInfoDataPopulator;
import org.felecechallenge.ticket.filter.RouteFilter;
import org.felecechallenge.ticket.model.Destination;
import org.felecechallenge.ticket.model.Route;
import org.felecechallenge.ticket.model.Vehicle;
import org.felecechallenge.ticket.service.DestinationService;
import org.felecechallenge.ticket.service.RouteService;
import org.felecechallenge.ticket.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RouteFacade {
    @Autowired
    private RouteService routeService;
    @Autowired
    private DestinationService destinationService;
    @Autowired
    private VehicleService vehicleService;

    private Converter routeUserConverter = new Converter(UserRouteData.class);
    private Converter routeAdminConverter = new Converter(AdminRouteData.class);

    public RouteFacade(){
        this.routeUserConverter.addPopulator(new RouteInfoDataPopulator());
        this.routeAdminConverter.addPopulator(new RouteInfoDataPopulator());
        this.routeAdminConverter.addPopulator(new RouteAdminDataPopulator());
    }
    public AdminRouteData getByIdforAdmin(Long id){
        Route route = this.routeService.getRouteById(id);
        AdminRouteData data = (AdminRouteData) this.routeAdminConverter.convert(route);
        return data;
    }
    public UserRouteTransferData getRouteDataforUser(RouteFilter filter){
        Page<Route> routePage = this.routeService.getRoutesQuery(filter);
        List<Route> routeList = routePage.getContent();


        List<UserRouteData> routeDataList = this.routeUserConverter.convertAll(routeList);

        UserRouteTransferData transferData = new UserRouteTransferData();
        transferData.setRouteData(routeDataList);
        transferData.setPageCount(routePage.getTotalPages());

        return transferData;

    }
    public AdminRouteTransferData getRouteDataforAdmin(RouteFilter filter){
        Page<Route> routePage = this.routeService.getRoutesQuery(filter);
        List<Route> routeList = routePage.getContent();
        List<AdminRouteData> routeDataList = this.routeAdminConverter.convertAll(routeList);

        AdminRouteTransferData transferData = new AdminRouteTransferData();
        transferData.setRouteData(routeDataList);
        transferData.setPageCount(routePage.getTotalPages());

        return transferData;

    }
    public void saveRoute(AdminRouteData data){
        Route route = new Route();
        adminRouteDTOMapper(route,data);
        if (route.getVehicle()==null || route.getEndDestination()==null || route.getStartDestination()==null || route.getDate()==null){
            throw new BadRequestException();
        }
        routeService.saveRoute(route);
    }
    public void updateRoute(Long id, AdminRouteData data){

        Route route = routeService.getRouteById(id);
        adminRouteDTOMapper(route,data);
        routeService.saveRoute(route);
    }
    public void deleteRoute(Long id){
        routeService.disableRoute(id);
    }
    public void adminRouteDTOMapper(Route route,AdminRouteData data){

        if (data.getPrice()!=null){
            route.setPrice(data.getPrice());
        }
        if (data.getDate()!=null){
            route.setDate(data.getDate());
        }
        if (data.getEndDestinationName()!=null || data.getEndDestinationId()!=null){//id or DestinationName need to be given
            if (data.getEndDestinationId()==null){
                Destination endDestination = this.destinationService.getByName(data.getEndDestinationName());
                route.setEndDestination(endDestination);
            }
            else{
                route.setEndDestination(new Destination(data.getEndDestinationId()));
            }
        }
        if (data.getStartDestinationName()!=null || data.getStartDestinationId()!=null){
            if (data.getStartDestinationId()==null){
                Destination startDestination = this.destinationService.getByName(data.getStartDestinationName());
                route.setStartDestination(startDestination);
            }
            else{
                route.setStartDestination(new Destination(data.getStartDestinationId()));
            }
        }
        if (data.getVehiclePlate()!=null){
            Vehicle vehicle = this.vehicleService.getVehicleByPlate(data.getVehiclePlate());
            if (vehicle==null){
                throw new NotFoundException();
            }
            route.setVehicle(vehicle);
        }
        if (route.getStartDestination().getId()==route.getEndDestination().getId() && route.getEndDestination().getId()!=null){
            throw new BadRequestException();// start and end Destinations cannot be same
        }
    }
}

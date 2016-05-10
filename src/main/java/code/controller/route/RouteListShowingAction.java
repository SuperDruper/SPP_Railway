package code.controller.route;

import code.controller.GetAction;
import code.controller.shared.Authorize;
import code.model.Race;
import code.model.Route;
import code.service.GenericService;

import java.util.List;

/**
 * Created by dzmitry.antonenka on 24.04.2016.
 */
//@Authorize("admin")
public class RouteListShowingAction extends GetAction {
    private List<Route> routes;
    private List<Race> races;

    @Override
    public String view() {
        races = new GenericService<Race, Integer>(Race.class).findAll();
        routes = new GenericService<Route, Integer>(Route.class).findAll();

        return SUCCESS;
    }

    public List<Route> getRoutes() {
        return routes;
    }
    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }
    public List<Race> getRaces() {
        return races;
    }
    public void setRaces(List<Race> races) {
        this.races = races;
    }
}

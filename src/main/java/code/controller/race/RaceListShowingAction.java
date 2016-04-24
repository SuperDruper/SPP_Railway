package code.controller.race;

import code.controller.GetAction;
import code.model.Race;
import code.model.Role;
import code.model.Route;
import code.model.Train;
import code.service.GenericService;

import java.util.List;

/**
 * Created by dzmitry.antonenka on 23.04.2016.
 */
public class RaceListShowingAction  extends GetAction {
    class CucumberRace
    {
        private int id;
        private Route route;
        private Train train;
    }

    private List<Race> races;

    private List<Route> routes;
    private List<Train> trains;

    @Override
    public String view() {
        races = new GenericService<Race, Integer>(Race.class).findAll();

        routes =  new GenericService<Route, Integer>(Route.class).findAll();
        trains = new GenericService<Train, Integer>(Train.class).findAll();

        return SUCCESS;
    }

    /*
    private int id;
    private Route route;
    private Train train;
    private Collection<RaceStation> raceStations;
    private Collection<Ticket> tickets;
    * */
    public List<Race> getRoles() {
        return races;
    }
    public void setRoles(List<Race> roles) {
        this.races = roles;
    }
    public List<Route> getRoutes() {
        return routes;
    }
    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }
    public List<Train> getTrains() {
        return trains;
    }
    public void setTrains(List<Train> trains) {
        this.trains = trains;
    }
}

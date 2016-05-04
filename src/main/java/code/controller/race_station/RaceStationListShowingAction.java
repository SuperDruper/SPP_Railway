package code.controller.race_station;

import code.controller.GetAction;
import code.controller.shared.Authorize;
import code.model.*;
import code.service.GenericService;

import java.util.List;

/**
 * Created by dzmitry.antonenka on 26.04.2016.
 */
//@Authorize("admin")
public class RaceStationListShowingAction extends GetAction {
    private List<RaceStation> raceStations;
    private List<Race> races;
    private List<Station> stations;

    @Override
    public String view() {
        races = new GenericService<Race, Integer>(Race.class).findAll();
        raceStations =  new GenericService<RaceStation, Integer>(RaceStation.class).findAll();
        stations = new GenericService<Station, Integer>(Station.class).findAll();

        return SUCCESS;
    }

    public List<Station> getStations() {
        return stations;
    }
    public void setStations(List<Station> stations) {
        this.stations = stations;
    }
    public List<Race> getRaces() {
        return races;
    }
    public void setRaces(List<Race> races) {
        this.races = races;
    }
    public List<RaceStation> getRaceStations() {
        return raceStations;
    }
    public void setRaceStations(List<RaceStation> raceStations) {
        this.raceStations = raceStations;
    }
}

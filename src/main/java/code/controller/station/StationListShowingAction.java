package code.controller.station;

import code.controller.GetAction;
import code.model.Role;
import code.model.Station;
import code.service.GenericService;

import java.util.List;

/**
 * Created by dzmitry.antonenka on 26.04.2016.
 */
public class StationListShowingAction extends GetAction {
    public List<Station> getStations() {
        return stations;
    }
    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    private List<Station> stations;

    @Override
    public String view() {
        stations = new GenericService<Station, Integer>(Station.class).findAll();

        return SUCCESS;
    }
}

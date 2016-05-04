package code.controller.station_distance;

import code.controller.GetAction;
import code.controller.shared.Authorize;
import code.model.Distance;
import code.model.Station;
import code.service.GenericService;

import java.util.List;

/**
 * Created by dzmitry.antonenka on 26.04.2016.
 */
//@Authorize("admin")
public class StationDistanceListShowingAction extends GetAction {
    public List<Distance> getStationDistances() {
        return stationDistances;
    }

    public void setStationDistances(List<Distance> stationDistances) {
        this.stationDistances = stationDistances;
    }

    private List<Distance> stationDistances;

    @Override
    public String view() {
        stationDistances = new GenericService<Distance, Integer>(Distance.class).findAll();

        return SUCCESS;
    }
}

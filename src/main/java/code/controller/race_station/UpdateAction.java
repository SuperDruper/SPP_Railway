package code.controller.race_station;

import code.controller.PostAction;
import code.controller.race_station.model.RaceStationContainer;
import code.controller.shared.Authorize;
import code.model.CrudAction;
import code.model.Race;
import code.model.RaceStation;
import code.service.GenericService;

/**
 * Created by dzmitry.antonenka on 26.04.2016.
 */
public class UpdateAction extends PostAction {
    private CrudAction action;
    private RaceStation raceStation;

    RaceStationContainer raceStationContainer;


    @Override
    public String create() {
        switch (action.getId()) {
            case 0:
                saveActionExecute();
                break;
            case 1: // Update
                updateActionExecute();
                break;
            case 2: //Delete
                deleteActionExecute();
                break;

            default:break;
        }

        return SUCCESS;
    }
    void saveActionExecute() {
        new GenericService<RaceStation, Integer>(RaceStation.class).persist(raceStation);
    }

    void updateActionExecute()
    {
        new GenericService<RaceStation, Integer>(RaceStation.class).update(raceStation);
    }

    void deleteActionExecute() {
        new GenericService<RaceStation, Integer>(RaceStation.class).delete(raceStation);
    }

    public RaceStationContainer getRaceStationContainer() {
        return raceStationContainer;
    }

    public void setRaceStationContainer(RaceStationContainer raceStationContainer) {
        this.raceStationContainer = raceStationContainer;
        this.raceStation = raceStationContainer.getRaceStationObjectFromCurrentContainer();
    }

    public RaceStation getRaceStation() {
        return raceStation;
    }
    public CrudAction getAction() {
        return action;
    }
    public void setAction(CrudAction action) {
        this.action = action;
    }
}

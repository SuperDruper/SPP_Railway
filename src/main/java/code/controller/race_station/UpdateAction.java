package code.controller.race_station;

import code.controller.PostAction;
import code.controller.shared.Authorize;
import code.model.CrudAction;
import code.model.Race;
import code.model.RaceStation;
import code.service.GenericService;

/**
 * Created by dzmitry.antonenka on 26.04.2016.
 */
//@Authorize("admin")
public class UpdateAction extends PostAction {
    private CrudAction action;
    private RaceStation raceStation;

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


    public RaceStation getRaceStation() {
        return raceStation;
    }
    public void setRaceStation(RaceStation raceStation) {
        this.raceStation = raceStation;
    }
    public CrudAction getAction() {
        return action;
    }
    public void setAction(CrudAction action) {
        this.action = action;
    }
}

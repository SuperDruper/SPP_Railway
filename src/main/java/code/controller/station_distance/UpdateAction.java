package code.controller.station_distance;

import code.controller.PostAction;
import code.controller.shared.Authorize;
import code.model.CrudAction;
import code.model.Distance;
import code.model.Station;
import code.service.GenericService;

/**
 * Created by dzmitry.antonenka on 26.04.2016.
 */
//@Authorize("admin")
public class UpdateAction extends PostAction {
    private CrudAction action;
    private Distance stationDistance;

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
        new GenericService<Distance, Integer>(Distance.class).persist(stationDistance);
    }

    void updateActionExecute()
    {
        new GenericService<Distance, Integer>(Distance.class).update(stationDistance);
    }

    void deleteActionExecute() {
        new GenericService<Distance, Integer>(Distance.class).delete(stationDistance);
    }


    public Distance getStationDistance() {
        return stationDistance;
    }

    public void setStationDistance(Distance stationDistance) {
        this.stationDistance = stationDistance;
    }

    public CrudAction getAction() {
        return action;
    }

    public void setAction(CrudAction action) {
        this.action = action;
    }
}

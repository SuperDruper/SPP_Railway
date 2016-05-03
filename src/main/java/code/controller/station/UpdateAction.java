package code.controller.station;

import code.controller.PostAction;
import code.controller.shared.Authorize;
import code.model.CrudAction;
import code.model.Role;
import code.model.Station;
import code.service.GenericService;

/**
 * Created by dzmitry.antonenka on 26.04.2016.
 */
@Authorize("admin")
public class UpdateAction extends PostAction {
    private CrudAction action;
    private Station station;

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
        new GenericService<Station, Integer>(Station.class).persist(station);
    }

    void updateActionExecute()
    {
        new GenericService<Station, Integer>(Station.class).update(station);
    }

    void deleteActionExecute() {
        new GenericService<Station, Integer>(Station.class).delete(station);
    }


    public void setAction(CrudAction action) { this.action = action; }
    public CrudAction getAction() { return this.action; }
    public Station getStation() {
        return station;
    }
    public void setStation(Station station) {
        this.station = station;
    }
}

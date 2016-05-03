package code.controller.race;

import code.controller.PostAction;
import code.controller.shared.Authorize;
import code.model.CrudAction;
import code.model.Race;
import code.model.Role;
import code.service.GenericService;

/**
 * Created by dzmitry.antonenka on 24.04.2016.
 */
@Authorize("admin")
public class UpdateAction extends PostAction {
    private CrudAction action;
    private Race race;

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
        new GenericService<Race, Integer>(Race.class).persist(race);
    }

    void updateActionExecute()
    {
        new GenericService<Race, Integer>(Race.class).update(race);
    }

    void deleteActionExecute() {
        new GenericService<Race, Integer>(Race.class).delete(race);
    }

    public Race getRace() { return race; }
    public void setRace(Race race) { this.race = race; }

    public void setAction(CrudAction action) { this.action = action; }
    public CrudAction getAction() { return this.action; }
}

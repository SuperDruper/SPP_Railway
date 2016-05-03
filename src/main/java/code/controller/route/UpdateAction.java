package code.controller.route;

import code.controller.PostAction;
import code.controller.shared.Authorize;
import code.model.CrudAction;
import code.model.Role;
import code.model.Route;
import code.service.GenericService;

/**
 * Created by dzmitry.antonenka on 24.04.2016.
 */
@Authorize("admin")
public class UpdateAction extends PostAction {
    private CrudAction action;

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    private Route route;

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

        new GenericService<Route, Integer>(Route.class).persist(route);
    }

    void updateActionExecute()
    {
        new GenericService<Route, Integer>(Route.class).update(route);
    }

    void deleteActionExecute() {
        new GenericService<Route, Integer>(Route.class).deleteByPK(route.getId());
    }

    public void setAction(CrudAction action) { this.action = action; }
    public CrudAction getAction() { return this.action; }
}

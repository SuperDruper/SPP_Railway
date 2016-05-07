package code.controller.route;

import code.controller.PostAction;
import code.controller.shared.Authorize;
import code.helpers.StringHelper;
import code.infrastructure.ValidationUtils;
import code.model.CrudAction;
import code.model.Role;
import code.model.Route;
import code.service.GenericService;
import code.service.RoleService;
import code.service.RouteService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by dzmitry.antonenka on 24.04.2016.
 */
//@Authorize("admin")
public class UpdateAction extends PostAction {
    private CrudAction action;

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    private Route route;
    private List<String> errorList;

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
        errorList = RouteService.validate(route, true);
        if(!errorList.isEmpty()) return;

        route.setName(StringHelper.getUTF8EncodedStringFromString(route.getName()));
        new GenericService<Route, Integer>(Route.class).persist(route);
    }

    void updateActionExecute()
    {
        errorList = RouteService.validate(route, false);
        if(!errorList.isEmpty()) return;

        new GenericService<Route, Integer>(Route.class).update(route);
    }

    void deleteActionExecute() {
        errorList =  new ArrayList<>();
        try {
            new RouteService().deleteByPK(route.getId());
        } catch (Exception exc) {
            errorList.add("Cannot delete entity, 'cause it's already related with another object !");
        }
    }

    public List<String> getErrorList() {
        return errorList;
    }
    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }

    public void setAction(CrudAction action) { this.action = action; }
    public CrudAction getAction() { return this.action; }
}
